package com.example.camera.camerax

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import java.nio.ByteBuffer
import java.util.*
import kotlin.collections.ArrayList
/** Helper type alias used for analysis use case callbacks */
typealias LumaListener = (luma: Double) -> Unit
/**
 * Our custom image analysis class.
 *
 * <p>All we need to do is override the function `analyze` with our desired operations. Here,
 * we compute the average luminosity of the image by looking at the Y plane of the YUV frame.
 */
class LuminosityAnalyzer(var listener: LumaListener? = null) : ImageAnalysis.Analyzer {
     private val frameRateWindow = 8
     private val frameTimestamps = ArrayDeque<Long>(5)
     private val listeners = ArrayList<LumaListener>().apply { listener?.let { add(it) } }
     private var lastAnalyzedTimestamp = 0L
     var framesPerSecond: Double = -1.0
         private set

     /**
      * Used to add listeners that will be called with each luma computed
      */
     fun onFrameAnalyzed(listener: LumaListener) = listeners.add(listener)

     /**
      * Helper extension function used to extract a byte array from an image plane buffer
      */
     private fun ByteBuffer.toByteArray(): ByteArray {
         rewind()    // Rewind the buffer to zero
         val data = ByteArray(remaining())
         get(data)   // Copy the buffer into a byte array
         return data // Return the byte array
     }

     /**
      * Analyzes an image to produce a result.
      *
      * <p>The caller is responsible for ensuring this analysis method can be executed quickly
      * enough to prevent stalls in the image acquisition pipeline. Otherwise, newly available
      * images will not be acquired and analyzed.
      *
      * <p>The image passed to this method becomes invalid after this method returns. The caller
      * should not store external references to this image, as these references will become
      * invalid.
      *
      * @param image image being analyzed VERY IMPORTANT: Analyzer method implementation must
      * call image.close() on received images when finished using them. Otherwise, new images
      * may not be received or the camera may stall, depending on back pressure setting.
      *
      */
      override fun analyze(image: ImageProxy) {
        // If there are no listeners attached, we don't need to perform analysis
        if (listeners.isEmpty()) {
            image.close()
            return
        }

        // Keep track of frames analyzed
        val currentTime = System.currentTimeMillis()
        frameTimestamps.push(currentTime)

        // Compute the FPS using a moving average
        while (frameTimestamps.size >= frameRateWindow) frameTimestamps.removeLast()
        val timestampFirst = frameTimestamps.peekFirst() ?: currentTime
        val timestampLast = frameTimestamps.peekLast() ?: currentTime
        framesPerSecond = 1.0 / ((timestampFirst - timestampLast) /
            frameTimestamps.size.coerceAtLeast(1).toDouble()) * 1000.0

        // Analysis could take an arbitrarily long amount of time
        // Since we are running in a different thread, it won't stall other use cases

        lastAnalyzedTimestamp = frameTimestamps.first

        // Since format in ImageAnalysis is YUV, image.planes[0] contains the luminance plane
        val buffer = image.planes[0].buffer

        // Extract image data from callback object
        val data = buffer.toByteArray()

        // Convert the data into an array of pixel values ranging 0-255
        val pixels = data.map { it.toInt() and 0xFF }

        // Compute average luminance for the image
        val luma = pixels.average()

        // Call all listeners with new value
        listeners.forEach { it(luma) }

        image.close()
    }
}