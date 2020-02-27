package com.aserbao.aserbaosandroid.aaThird.rxJava3.api;

import com.aserbao.aserbaosandroid.AUtils.utils.log.ALogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class RetryWithDelay implements
    Function<Observable<? extends Throwable>, Observable<?>> {

        private final int maxRetries;
        private final int retryDelayMillis;
        private int retryCount;

        public RetryWithDelay(int maxRetries, int retryDelayMillis) {
            this.maxRetries = maxRetries;
            this.retryDelayMillis = retryDelayMillis;
        }

        @Override
        public Observable<?> apply(Observable<? extends Throwable> observable) throws Throwable {
            return observable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                        if (++retryCount <= maxRetries) {
                            ALogUtils.e("RetryWithDelay","error, it will try again after "+ retryDelayMillis + "  millisecond, retry count "+ retryCount + " maxRetries is "+ maxRetries);
                            return Observable.timer(retryDelayMillis,
                                TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(throwable);
                    }
                });
        }
}
