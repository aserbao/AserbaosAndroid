package com.aserbao.aserbaosandroid.audioAndVideo.media.mediaMuxer;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;

import com.aserbao.aserbaosandroid.R;

import java.io.IOException;
import java.nio.ByteBuffer;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MediaMuxerActivity extends AppCompatActivity {

    private static final int BUFFER_SIZE_IN_BYTES = 8820;
    private ByteBuffer[] mAudioInputBuffers,mAudioOutputBuffers;
    private ByteBuffer mInputBuffer,mOutBuffer;
    private byte[] bytes;
    private MediaCodec.BufferInfo mOutBufferInfo;
    private Surface mInputSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_muxer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_media_muxer)
    public void onViewClicked() {

       /* MediaMuxer muxer = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                muxer = new MediaMuxer("temp.mp4", MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
                // More often, the MediaFormat will be retrieved from MediaCodec.getOutputFormat()
                // or MediaExtractor.getTrackFormat().
                MediaFormat audioFormat = new MediaFormat();
                MediaFormat videoFormat = new MediaFormat();
                int audioTrackIndex = muxer.addTrack(audioFormat);
                int videoTrackIndex = muxer.addTrack(videoFormat);
                ByteBuffer inputBuffer = ByteBuffer.allocate(bufferSize);
                boolean finished = false;
                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();

                muxer.start();
                while(!finished) {
                    // getInputBuffer() will fill the inputBuffer with one frame of encoded
                    // sample from either MediaCodec or MediaExtractor, set isAudioSample to
                    // true when the sample is audio data, set up all the fields of bufferInfo,
                    // and return true if there are no more samples.
                    finished = getInputBuffer(inputBuffer, isAudioSample, bufferInfo);
                    if (!finished) {
                        int currentTrackIndex = isAudioSample ? audioTrackIndex : videoTrackIndex;
                        muxer.writeSampleData(currentTrackIndex, inputBuffer, bufferInfo);
                    }
                };
                muxer.stop();
                muxer.release();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    }

    public void initMediacodec(){
        try {
            String AUDIO_MIME = "audio/mp4a-latm";
            MediaCodec mAudioEncoder = MediaCodec.createEncoderByType(AUDIO_MIME);
            MediaFormat mAudioMediaFormat = new MediaFormat();
            mAudioMediaFormat.setString(MediaFormat.KEY_MIME, AUDIO_MIME);
            mAudioMediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, 32000);
            mAudioMediaFormat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, 2);
            mAudioMediaFormat.setInteger(MediaFormat.KEY_SAMPLE_RATE, 48000);
            mAudioMediaFormat.setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, BUFFER_SIZE_IN_BYTES);
            mAudioMediaFormat.setInteger(MediaFormat.KEY_AAC_PROFILE,
                    MediaCodecInfo.CodecProfileLevel.AACObjectLC);
            mAudioEncoder.configure(mAudioMediaFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            mAudioEncoder.start();

            mInputSurface = mAudioEncoder.createInputSurface();


            mAudioInputBuffers = mAudioEncoder.getInputBuffers();
            mAudioOutputBuffers = mAudioEncoder.getOutputBuffers();
            int inputBufIndex = mAudioEncoder.dequeueInputBuffer(1000);
            mInputBuffer = mAudioInputBuffers[inputBufIndex];
            mInputBuffer.clear();
            mInputBuffer.put(bytes, 0, BUFFER_SIZE_IN_BYTES);
            mAudioEncoder.queueInputBuffer(inputBufIndex, 0, BUFFER_SIZE_IN_BYTES, (SystemClock.uptimeMillis() * 1000), 0);
            int outputBufIndex = mAudioEncoder.dequeueOutputBuffer(mOutBufferInfo, 1000);
            mOutBuffer = mAudioOutputBuffers[outputBufIndex];
            if ((mOutBufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                // Codec-specific Data, 这里可以从ByteBuffer中获取csd参数
                // audioFormat.setByteBuffer("csd-0", mOutBuffer);
            } else {
                // 处理数据
            }
            mAudioEncoder.releaseOutputBuffer(outputBufIndex, false);
            mAudioEncoder.stop();
            mAudioEncoder.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
