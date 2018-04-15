package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;


import com.aserbao.aserbaosandroid.BuildConfig;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MediaMuxerWrapper {
	private static final boolean DEBUG = BuildConfig.DEBUG;
	private static final String TAG = "MediaMuxerWrapper";

	private final MediaMuxer mMediaMuxer;
	private int mEncoderCount = 0;
	private int mStartedCount = 0;
	private boolean mIsStarted = false;

	public MediaMuxerWrapper(String outputPath) throws IOException {
		mMediaMuxer = new MediaMuxer(outputPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
		mEncoderCount = 0;
		mStartedCount = 0;
		mIsStarted = false;
	}
	public void setTrackCount(int count) {
		mEncoderCount = count;
	}

//**********************************************************************
//**********************************************************************

	/**
	 * request start recording from encoder
	 * @return true when muxer is ready to write
	 */
	public synchronized boolean start() {
		if (DEBUG) {
			Log.v(TAG,  "start:");
		}
		mStartedCount++;
		if ((mEncoderCount > 0) && (mStartedCount == mEncoderCount)) {
			mMediaMuxer.start();
			mIsStarted = true;
			notifyAll();
			if (DEBUG) {
				Log.v(TAG,  "MediaMuxer started:");
			}
		}
		return mIsStarted;
	}

	/**
	 * request stop recording from encoder when encoder received EOS
	*/
	public synchronized void stop() {
		if (DEBUG) {
			Log.v(TAG,  "stop:mStartedCount=" + mStartedCount);
		}
		if (!mIsStarted) {
			Log.d("","not started");
			return ;
		}
		mStartedCount--;
		if ((mEncoderCount > 0) && (mStartedCount <= 0)) {
			mMediaMuxer.stop();
			mMediaMuxer.release();
			mIsStarted = false;
			if (DEBUG) {
				Log.v(TAG,  "MediaMuxer stopped:");
			}
		}
	}

	/**
	 * assign encoder to muxer
	 * @param format
	 * @return minus value indicate error
	 */
	public synchronized int addTrack(final MediaFormat format) {
		if (mIsStarted)
			throw new IllegalStateException("muxer already started");
		final int trackIx = mMediaMuxer.addTrack(format);
		if (DEBUG) {
			Log.i(TAG, "addTrack:trackNum=" + mEncoderCount + ",trackIx=" + trackIx + ",format=" + format);
		}
		return trackIx;
	}

	/**
	 * write encoded data to muxer
	 * @param trackIndex
	 * @param byteBuf
	 * @param bufferInfo
	 */
	public synchronized void writeSampleData(final int trackIndex, final ByteBuffer byteBuf, final MediaCodec.BufferInfo bufferInfo) {
		if (!mIsStarted) {
			return ;
		}
		if (mStartedCount > 0) {
			mMediaMuxer.writeSampleData(trackIndex, byteBuf, bufferInfo);
		}
	}

}
