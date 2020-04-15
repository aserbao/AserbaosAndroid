package com.getremark.base.kotlin_ext

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers.computation
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class Throttle {

    fun testFlows() {

        testFlow { debounce(1000) } // 3 4 5 7 9

        testFlow { throttleFirstJava(1000) } // 1 4 5 6 8

        testFlow { sample(1000) } // 3 4 5 7

        testFlow { throttleLatestKotlin(1000) } // 1 3 4 5 6 7

        testFlow { throttleLatestJava(1000) } // 1 3 4 5 6 7
    }

    fun testObservables() {

        testObservable { debounce(1000, TimeUnit.MILLISECONDS) } // 3 4 5 7 9

        testObservable { throttleFirst(1000, TimeUnit.MILLISECONDS) } // 1 4 5 6 8

        testObservable { throttleLast(1000, TimeUnit.MILLISECONDS) } // 3 4 5 7

        testObservable { throttleLatest(1000, TimeUnit.MILLISECONDS) } // 1 3 4 5 6 7
    }

    private fun testFlow(operator: Flow<Int>.() -> Flow<Int>) {

        val latch = CountDownLatch(1)
        val result = StringBuffer()

        CoroutineScope(Job() + Dispatchers.Default).launch {
            myFlow()
                .operator()
                .onCompletion { latch.countDown() }
                .collect { result.append(it).append(" ") }
        }

        latch.await()
        println("$result")
    }

    private fun testObservable(operator: Observable<Int>.() -> Observable<Int>) {

        val latch = CountDownLatch(1)
        val result = StringBuffer()

        observable()
            .operator()
            .doOnComplete { latch.countDown() }
            .subscribeOn(computation())
            .subscribe { result.append(it).append(" ") }

        latch.await()
        println("$result")
    }

    private fun myFlow(): Flow<Int> {
        return flow {
            emit(1)
            delay(90)
            emit(2)
            delay(90)
            emit(3)
            delay(1010)
            emit(4)
            delay(1010)
            emit(5)
            delay(2000)
            emit(6)
            delay(90)
            emit(7)
            delay(1010)
            emit(8)
            delay(80)
            emit(9)
        }
    }

    private fun observable(): Observable<Int> {
        return Observable.create { emitter ->
            emitter.onNext(1)
            Thread.sleep(90)
            emitter.onNext(2)
            Thread.sleep(90)
            emitter.onNext(3)
            Thread.sleep(1010)
            emitter.onNext(4)
            Thread.sleep(1010)
            emitter.onNext(5)
            Thread.sleep(2000)
            emitter.onNext(6)
            Thread.sleep(90)
            emitter.onNext(7)
            Thread.sleep(1010)
            emitter.onNext(8)
            Thread.sleep(80)
            emitter.onNext(9)
            emitter.onComplete()
        }
    }
}

fun <T> Flow<T>.throttleFirstJava(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }
    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}

fun <T> Flow<T>.throttleLatestJava(periodMillis: Long): Flow<T> {
    return channelFlow {
        var lastValue: T?
        var timer: Timer? = null
        onCompletion { timer?.cancel() }
        collect { value ->
            lastValue = value

            if (timer == null) {
                timer = Timer()
                timer?.scheduleAtFixedRate(
                    object : TimerTask() {
                        override fun run() {
                            val value = lastValue
                            lastValue = null
                            if (value != null) {
                                launch {
                                    send(value as T)
                                }
                            } else {
                                timer?.cancel()
                                timer = null
                            }
                        }
                    },
                    0,
                    periodMillis
                )
            }
        }
    }
}

@ExperimentalCoroutinesApi
fun <T> Flow<T>.throttleLatestKotlin(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }

    return channelFlow {
        val done = Any()
        val values = produce(capacity = Channel.CONFLATED) {
            collect { value -> send(value) }
        }

        var lastValue: Any? = null
        val ticker = Ticker(periodMillis)
        while (lastValue !== done) {
            select<Unit> {
                values.onReceiveOrNull {
                    if (it == null) {
                        ticker.cancel()
                        lastValue = done
                    } else {
                        lastValue = it
                        if (!ticker.isStarted) {
                            ticker.start(this@channelFlow)
                        }
                    }

                }

                ticker.getTicker().onReceive {
                    if (lastValue !== null) {
                        val value = lastValue
                        lastValue = null
                        send(value as T)
                    } else {
                        ticker.stop()
                    }
                }
            }
        }
    }
}

class Ticker(private val delay: Long) {

    private var channel: ReceiveChannel<Unit> = Channel()

    var isStarted: Boolean = false
        private set

    fun getTicker(): ReceiveChannel<Unit> {
        return channel
    }

    fun start(scope: CoroutineScope) {
        isStarted = true
        channel.cancel()
        channel = scope.produce(capacity = 0) {
            while (true) {
                channel.send(Unit)
                delay(delay)
            }
        }
    }

    fun stop() {
        isStarted = false
        channel.cancel()
        channel = Channel()
    }

    fun cancel() {
        isStarted = false
        channel.cancel()
    }
}