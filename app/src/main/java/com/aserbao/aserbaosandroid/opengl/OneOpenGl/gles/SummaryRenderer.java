package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles;

import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;


import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects.Axis;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects.AxisArrow;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects.SummaryGraph;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects.Table;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects.Text;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs.ColorShaderProgram;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs.TextShaderProgram;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs.TextureShaderProgram;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util.Geometry;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util.TextureHelper;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_TEST;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDisable;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glViewport;

/**
 * Almost all methods of this class is called by the GLThread
 */
public class SummaryRenderer implements GLSurfaceView.Renderer {


    private final static float DEFAULT_AXIS_X = -0.85f;
    private final static float DEFAULT_AXIS_Y = 0.8f;

    private final Context mContext;

    private final float[] mProjectionMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mViewProjectionMatrix = new float[16];
    private final float[] mInvertedViewProjectionMatrix = new float[16];
    private final float[] mModelViewProjectionMatrix = new float[16];

    private List<String> mTimeText = new ArrayList<>();

    private Table mTable;
    private Axis mAxis;
    private AxisArrow mAxisArrow;
    private SummaryGraph mSummaryGraph;
    private Text mText;

    private ColorShaderProgram mColorProgram;
    private TextureShaderProgram mTextureProgram;
    private TextShaderProgram mTextProgram;
    private int mTexture;

    private Geometry.Point mEyePos;
    private float mGraphTranslationX;

    public SummaryRenderer(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        mTextureProgram = new TextureShaderProgram(mContext);
        mColorProgram = new ColorShaderProgram(mContext);
        mTextProgram = new TextShaderProgram(mContext);

        mTable = new Table();
        mAxis = new Axis(0.005f, DEFAULT_AXIS_Y, 32);
        mAxisArrow = new AxisArrow(0.015f, 0.05f, 48);
        mText = new Text(mContext, mTextProgram);
        mText.load("Roboto-Regular.ttf", 24, 3, 3, true);

        mTexture = TextureHelper.loadTexture(mContext, R.drawable.mm_1);

        mEyePos = new Geometry.Point(0f, 0.8f, 1.4f);
        mGraphTranslationX = DEFAULT_AXIS_X;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);

        mText.updateNormalizedUnit(width, height);

        Matrix.perspectiveM(mProjectionMatrix, 0, 50, (float) width / (float) height, 1f, 10f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear the rendering surface.
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glDisable(GL_BLEND);
        glEnable(GL_DEPTH_TEST);

        // Update the view matrix
        Matrix.setLookAtM(mViewMatrix, 0, mEyePos.x, mEyePos.y, mEyePos.z, 0f, 0.53f, 0f, 0f, 1f, 0f);

        // Update the viewProjection matrix, and create an inverted matrix for touch picking.
        Matrix.multiplyMM(mViewProjectionMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        Matrix.invertM(mInvertedViewProjectionMatrix, 0, mViewProjectionMatrix, 0);

        // Draw the table.
        positionTableInScene();
        mTextureProgram.useProgram();
        mTextureProgram.setUniforms(mModelViewProjectionMatrix, mTexture);
        mTable.bindData(mTextureProgram);
        mTable.draw();

        glEnable(GL_BLEND);
        glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        // Draw the axis.
        positionObjectInScene(DEFAULT_AXIS_X, 0f, 0f);
        mColorProgram.useProgram();
        mColorProgram.setUniforms(mModelViewProjectionMatrix, 0.8f, 0.8f, 0.8f, 0.7f);
        mAxis.bindData(mColorProgram);
        mAxis.draw();

        // Draw the axis arrow.
        positionObjectInScene(DEFAULT_AXIS_X, mAxis.mHeight, 0f);
        mColorProgram.setUniforms(mModelViewProjectionMatrix, 0.8f, 0.8f, 0.8f, 0.8f);
        mAxisArrow.bindData(mColorProgram);
        mAxisArrow.draw();

        // Draw the summary graph.
        if (mSummaryGraph != null) {
            positionObjectInScene(mGraphTranslationX, 0.01f, 0f);
            mColorProgram.setUniforms(mModelViewProjectionMatrix, 1f, 1f, 0f, 0.75f);
            mSummaryGraph.bindData(mColorProgram);
            mSummaryGraph.draw();

            glDisable(GL_DEPTH_TEST); // Allow text padding overlapping

            // Prepare the time text.
            int graphPageSize = Math.round(mSummaryGraph.getConfiguration().getGraphPageSize());
            if (mTimeText.size() == 0) {
                for (int i = 0; i <= graphPageSize; ++i) {
                    mTimeText.add(String.format("%02d:00", i));
                    for (int j = 1; j <= 5; ++j) {  // each for 10 minutes
                        mTimeText.add(String.format("%02d:%d", i, j * 10));
                    }
                }
            }
            float offsetX = -mText.getLength("00:00", true) / 2f;

            // Draw the time text.
            mTextProgram.useProgram();
            for (int i = 0; i <= graphPageSize; ++i) {
                int textIndex = i * 6;
                positionTextInScene(mGraphTranslationX + i * 2 + offsetX, -0.015f, 0.05f, 0f, 0f, 0f);
                mTextProgram.setUniforms(mModelViewProjectionMatrix, mText.getFontTexture(), 1f, 1f, 1f, 0.75f);
                mText.draw(mTimeText.get(textIndex), Text.DRAW_ALIGN_CENTER_VERTICAL);
                for (int j = 1; j <= 5 && i < graphPageSize; ++j) { // 10, 20, 30, 40, 50
                    positionTextInScene(mGraphTranslationX + i * 2 + 2 / 6f * j + offsetX, -0.015f, 0.05f, 0f, 0f, 0f);
                    mTextProgram.setUniforms(mModelViewProjectionMatrix, mText.getFontTexture(), 1f, 1f, 1f, 0.75f);
                    mText.draw(mTimeText.get(textIndex + j), Text.DRAW_ALIGN_CENTER_VERTICAL);
                }
            }
            glEnable(GL_DEPTH_TEST);
        }
    }

    private void positionTableInScene() {
        // The table is defined in terms of X & Y coordinates, so we rotate it
        // 90 degrees to lie flat on the XZ plane.
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.rotateM(mModelMatrix, 0, -70f, 1f, 0f, 0f);
        Matrix.multiplyMM(mModelViewProjectionMatrix, 0, mViewProjectionMatrix, 0, mModelMatrix, 0);
    }

    private void positionObjectInScene(float x, float y, float z) {
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, x, y, z);
        Matrix.multiplyMM(mModelViewProjectionMatrix, 0, mViewProjectionMatrix, 0, mModelMatrix, 0);
    }

    private void positionTextInScene(float x, float y, float z, float angleX, float angleY, float angleZ) {
        PointF offset = mText.getTextStartOffset();
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, x + offset.x, y + offset.y, z);
        Matrix.rotateM(mModelMatrix, 0, angleZ, 0, 0, 1);
        Matrix.rotateM(mModelMatrix, 0, angleX, 1, 0, 0);
        Matrix.rotateM(mModelMatrix, 0, angleY, 0, 1, 0);
        Matrix.multiplyMM(mModelViewProjectionMatrix, 0, mViewProjectionMatrix, 0, mModelMatrix, 0);
    }

    public void updateMotionGraph(float[] graphData, Geometry.Point graphOrigin, float graphPageSize, float graphHeight) {
        if (mSummaryGraph == null) {
            mSummaryGraph = new SummaryGraph();
        }
        SummaryGraph.Configuration configuration = new SummaryGraph.Configuration.Builder()
                .setGraphData(graphData)
                .setGraphOrigin(graphOrigin)
                .setGraphPageSize(graphPageSize)
                .setGraphHeight(graphHeight)
                .build();
        mSummaryGraph.updateGraph(configuration);
    }

    public void touchDown(float normalizedX, float normalizedY) {
        Geometry.Ray ray = convertNormalized2DPointToRay(normalizedX, normalizedY);

        // Now test if this ray intersects with the mallet by creating a
        // bounding sphere that wraps the mallet.
//        Geometry.Sphere malletBoundingSphere = new Geometry.Sphere(new Geometry.Point(
//                blueMalletPosition.x,
//                blueMalletPosition.y,
//                blueMalletPosition.z),
//                mallet.height / 2f);
//
//        // If the ray intersects (if the user touched a part of the screen that
//        // intersects the mallet's bounding sphere), then set malletPressed =
//        // true.
//        malletPressed = Geometry.intersects(malletBoundingSphere, ray);
    }

    private Geometry.Ray convertNormalized2DPointToRay(float normalizedX, float normalizedY) {
        // We'll convert these normalized device coordinates into world-space
        // coordinates. We'll pick a point on the near and far planes, and draw a
        // line between them. To do this transform, we need to first multiply by
        // the inverse matrix, and then we need to undo the perspective divide.
        final float[] nearPointNdc = {normalizedX, normalizedY, -1, 1};
        final float[] farPointNdc =  {normalizedX, normalizedY,  1, 1};

        final float[] nearPointWorld = new float[4];
        final float[] farPointWorld = new float[4];

        Matrix.multiplyMV(nearPointWorld, 0, mInvertedViewProjectionMatrix, 0, nearPointNdc, 0);
        Matrix.multiplyMV(farPointWorld, 0, mInvertedViewProjectionMatrix, 0, farPointNdc, 0);

        // Why are we dividing by W? We multiplied our vector by an inverse
        // matrix, so the W value that we end up is actually the *inverse* of
        // what the projection matrix would create. By dividing all 3 components
        // by W, we effectively undo the hardware perspective divide.
        divideByW(nearPointWorld);
        divideByW(farPointWorld);

        // We don't care about the W value anymore, because our points are now
        // in world coordinates.
        Geometry.Point nearPointRay = new Geometry.Point(nearPointWorld[0], nearPointWorld[1], nearPointWorld[2]);
        Geometry.Point farPointRay = new Geometry.Point(farPointWorld[0], farPointWorld[1], farPointWorld[2]);

        return new Geometry.Ray(nearPointRay, Geometry.vectorBetween(nearPointRay, farPointRay));
    }

    private void divideByW(float[] vector) {
        vector[0] /= vector[3];
        vector[1] /= vector[3];
        vector[2] /= vector[3];
    }

    public void scrollBy(float normalizedDistanceX, float normalizedDistanceY) {
        if (mSummaryGraph != null) {
            float min = DEFAULT_AXIS_X - mSummaryGraph.getConfiguration().getGraphPageSize() * 2;
            min += Math.abs(DEFAULT_AXIS_X) * 2; // add the offset or the graph will end at the origin
            mGraphTranslationX = clamp(mGraphTranslationX + normalizedDistanceX, min, DEFAULT_AXIS_X);
            mEyePos.y = clamp(mEyePos.y + normalizedDistanceY * 0.1f, 0.7f, 0.95f);
//            LOGD(TAG, "mGraphTranslationX: " + mGraphTranslationX);
        }
    }

    private float clamp(float value, float min, float max) {
        return Math.min(max, Math.max(value, min));
    }
}
