package com.aserbao.aserbaosandroid.audioAndVideo.media.image2video.jcodec;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aserbao.aserbaosandroid.R;

import java.io.File;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * https://github.com/olivergeith/android_jcodec
 */
public class JCodecImage2VideoActivity extends AppCompatActivity {

    @BindView(R.id.jcodec_jz_video_play)
    JZVideoPlayerStandard mJcodecJzVideoPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcodec_image2_video);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.jcodec_start)
    public void onViewClicked() {
        performJcodec();
    }
    private Resources mResource;


    private long mStartTime;
    private void performJcodec() {
        mResource =  getResources();
        try {
            mStartTime = System.currentTimeMillis();
            Log.e("performJcodec: ", "执行开始");
            SequenceEncoderMp4 se   = null;
            File directory = Environment.getExternalStorageDirectory();
            File out = new File(directory, "aserbao.mp4");
            se = new SequenceEncoderMp4(out);
            for (int i = 0; i < 1000; i++) {
                Bitmap frame = BitmapFactory.decodeResource(mResource,R.drawable.watercolor);
                se.encodeImage(frame);
                Log.e("performJcodec: ", "执行到的图片是 " + i);
            }
            se.finish();
            long l = (System.currentTimeMillis() - mStartTime) / 1000;
            Log.e("performJcodec: ", "执行完成 一共花费时长为 " + l + "s");
        } catch (IOException e) {
            Log.e("performJcodec: ", "执行异常 " + e.toString());
        }
    }

    /*private void image2Video(){
        SeekableByteChannel out = null;
        try {
            out = NIOUtils.writableFileChannel("/tmp/output.mp4");
            // for Android use: AndroidSequenceEncoder
            AWTSequenceEncoder encoder = new AWTSequenceEncoder(out, Rational.R(25, 1));
            for (...) {
                // Generate the image, for Android use Bitmap
                BufferedImage image = ...;
                // Encode the image
                encoder.encodeImage(image);
            }
            // Finalize the encoding, i.e. clear the buffers, write the header, etc.
            encoder.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            NIOUtils.closeQuietly(out);
        }
    }*/
}
