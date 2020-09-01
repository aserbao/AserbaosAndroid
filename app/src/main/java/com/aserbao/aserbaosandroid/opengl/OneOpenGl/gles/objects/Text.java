package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.opengl.GLES20;


import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs.TextShaderProgram;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util.TextureHelper;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util.TextureRegion;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;


public class Text {
    private static final String TAG = "Text";

    public final static int CHAR_START = 32;           // First Character (ASCII Code)
    public final static int CHAR_END = 126;            // Last Character (ASCII Code)
    public final static int CHAR_CNT = (CHAR_END - CHAR_START + 1) + 1;  // Character Count (Including Character to use for Unknown)

    public final static int CHAR_NONE = 32;            // Character to Use for Unknown (ASCII Code)
    public final static int CHAR_UNKNOWN = CHAR_CNT - 1;  // Index of the Unknown Character

    public final static int FONT_SIZE_MIN = 6;         // Minimum Font Size (Pixels)
    public final static int FONT_SIZE_MAX = 180;       // Maximum Font Size (Pixels)

    public final static int CHAR_BATCH_SIZE = 24;      // Maximum Number of Characters to Render Per Batch

    public final static int DRAW_ALIGN_DEFAULT = 0;
    public final static int DRAW_ALIGN_CENTER_HORIZONTAL = 1;
    public final static int DRAW_ALIGN_CENTER_VERTICAL = 1 << 1;

    AssetManager mAssets;
    SpriteBatch mBatch;

    int mFontPadX, mFontPadY;

    float mFontHeight;                                 // Font Height (Actual; Pixels)
    float mFontAscent;                                 // Font Ascent (Above Baseline; Pixels)
    float mFontDescent;                                // Font Descent (Below Baseline; Pixels)

    int mFontTexture;
    int mTextureSize;
    TextureRegion mTextureRgn;                         // Full Texture Region

    TextShaderProgram mProgram;

    float mCharWidthMax;                               // Character Width (Maximum; Pixels)
    float mCharHeight;                                 // Character Height (Maximum; Pixels)
    final float[] mCharWidths;                         // Width of Each Character (Actual; Pixels)
    TextureRegion[] mCharRgn;                          // Region of Each Character (Texture Coordinates)
    int mCellWidth, mCellHeight;                       // Character Cell Width/Height
    int mRows, mCols;                                  // Number of Rows/Columns

    float mScaleX, mScaleY;
    float mSpaceX;

    float mNormalizedUnitX;
    float mNormalizedUnitY;


    public Text(Context context, TextShaderProgram program) {
        mAssets = context.getAssets();
        mBatch = new SpriteBatch(CHAR_BATCH_SIZE);
        mProgram = program;

        mCharWidths = new float[CHAR_CNT];               // Create the Array of Character Widths
        mCharRgn = new TextureRegion[CHAR_CNT];          // Create the Array of Character Regions

        mFontPadX = 0;
        mFontPadY = 0;

        mFontHeight = 0.0f;
        mFontAscent = 0.0f;
        mFontDescent = 0.0f;

        mFontTexture = -1;
        mTextureSize = 0;

        mCharWidthMax = 0;
        mCharHeight = 0;

        mCellWidth = 0;
        mCellHeight = 0;
        mRows = 0;
        mCols = 0;

        mScaleX = 1.0f;
        mScaleY = 1.0f;
        mSpaceX = 0.0f;
    }

    public boolean load(String file, int size, int padX, int padY, boolean fakeBoldText) {
        // setup requested values
        mFontPadX = padX;
        mFontPadY = padY;

        // load the font and setup paint instance for drawing
//        Typeface tf = Typeface.createFromAsset(mAssets, file);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(size);
        paint.setColor(Color.WHITE);
//        paint.setTypeface(tf);
//        paint.setFakeBoldText(fakeBoldText);

        // get font metrics
        Paint.FontMetrics fm = paint.getFontMetrics();  // Get Font Metrics
        mFontHeight  = (float)Math.ceil(Math.abs(fm.bottom) + Math.abs(fm.top));  // Calculate Font Height
        mFontAscent  = (float)Math.ceil(Math.abs(fm.ascent));   // Save Font Ascent
        mFontDescent = (float)Math.ceil(Math.abs(fm.descent));  // Save Font Descent

        // determine the width of each character (including unknown character)
        // also determine the maximum character width
        char[] s = new char[2];                          // Create Character Array
        mCharWidthMax = mCharHeight = 0;                 // Reset Character Width/Height Maximums
        float[] w = new float[2];                        // Working Width Value
        int cnt = 0;                                     // Array Counter
        for (char c = CHAR_START; c <= CHAR_END; c++) {  // FOR Each Character
            s[0] = c;                                    // Set Character
            paint.getTextWidths(s, 0, 1, w);             // Get Character Bounds
            mCharWidths[cnt] = w[0];                     // Get Width
            if (mCharWidths[cnt] > mCharWidthMax)
                mCharWidthMax = mCharWidths[cnt];        // Save New Max Width
            cnt++;                                       // Advance Array Counter
        }
        s[0] = CHAR_NONE;                                // Set Unknown Character
        paint.getTextWidths(s, 0, 1, w);                 // Get Character Bounds
        mCharWidths[cnt] = w[0];                         // Get Width
        if (mCharWidths[cnt] > mCharWidthMax)
            mCharWidthMax = mCharWidths[cnt];            // Save New Max Width
        cnt++;                                           // Advance Array Counter

        // set character height to font height
        mCharHeight = mFontHeight;

        // find the maximum size, validate, and setup cell sizes
        mCellWidth = (int) mCharWidthMax + 2 * mFontPadX;
        mCellHeight = (int) mCharHeight + 2 * mFontPadY;
        int maxSize = mCellWidth > mCellHeight ? mCellWidth : mCellHeight;
        if (maxSize < FONT_SIZE_MIN || maxSize > FONT_SIZE_MAX) { // IF Maximum Size Outside Valid Bounds
            return false;
        }

        // set texture size based on max font size (width or height)
        // NOTE: these values are fixed, based on the defined characters. when
        // changing start/end characters (CHAR_START/CHAR_END) this will need adjustment too!
        if (maxSize <= 24) {
            mTextureSize = 256;
        } else if (maxSize <= 40) {
            mTextureSize = 512;
        } else if (maxSize <= 80) {
            mTextureSize = 1024;
        } else {
            mTextureSize = 2048;
        }

        // create an empty bitmap (alpha only)
        Bitmap bitmap = Bitmap.createBitmap(mTextureSize, mTextureSize, Bitmap.Config.ALPHA_8);  // Create Bitmap
        Canvas canvas = new Canvas(bitmap);             // Create Canvas for Rendering to Bitmap
        bitmap.eraseColor(0x00000000);                  // Set Transparent Background (ARGB)

        // calculate rows/columns
        // NOTE: while not required for anything, these may be useful to have :)
        mCols = mTextureSize / mCellWidth;                  // Calculate Number of Columns
        mRows = (int) Math.ceil((float) CHAR_CNT / mCols);  // Calculate Number of Rows

        // render each of the characters to the canvas (ie. build the font map)
        float x = mFontPadX;
        float y = (mCellHeight - 1) - mFontDescent - mFontPadY;
        for (char c = CHAR_START; c <= CHAR_END; c++) {
            s[0] = c;
            canvas.drawText(s, 0, 1, x, y, paint);
            x += mCellWidth;
            if (x + mCellWidth - mFontPadX > mTextureSize) {
                x = mFontPadX;
                y += mCellHeight;
            }
        }
        s[0] = CHAR_NONE;  // Set Character to Use for NONE
        canvas.drawText(s, 0, 1, x, y, paint);

        // save the bitmap in a texture
        mFontTexture = TextureHelper.loadTexture(bitmap);

        // setup the array of character texture regions
        x = y = 0;
        for (int c = 0; c < CHAR_CNT; c++) {
            mCharRgn[c] = new TextureRegion(mTextureSize, mTextureSize, x, y, mCellWidth - 1, mCellHeight - 1);  // Create Region for Character
            x += mCellWidth;
            if (x + mCellWidth > mTextureSize)  {
                x = 0;
                y += mCellHeight;
            }
        }

        // create full texture region
        mTextureRgn = new TextureRegion(mTextureSize, mTextureSize, 0, 0, mTextureSize, mTextureSize);

        return true;
    }

    public void draw(String text, int align)  {
        mBatch.beginBatch();

        float chrWidth  = mCellWidth * mScaleX;
        float chrHeight = mCellHeight * mScaleY;
        int length = text.length();

        float letterX = 0, letterY = 0;
        if ((align & DRAW_ALIGN_CENTER_HORIZONTAL) > 0) {
            letterX -= getLength(text) / 2f;
        }
        if ((align & DRAW_ALIGN_CENTER_VERTICAL) > 0) {
            letterY -= mCellWidth / 2f;
        }

        for (int i = 0; i < length; i++) {
            int c = (int) text.charAt(i) - CHAR_START;
            if (c < 0 || c >= CHAR_CNT) {
                c = CHAR_UNKNOWN;
            }
            mBatch.drawSprite(letterX, letterY, chrWidth, chrHeight, mCharRgn[c]);
            letterX += (mCharWidths[c] + mSpaceX) * mScaleX;
        }

        mBatch.endBatch();
    }

    public int getFontTexture() {
        return mFontTexture;
    }

    public PointF getTextStartOffset() {
        float chrWidth  = mCellWidth * mScaleX;       // Calculate Scaled Character Width
        float chrHeight = mCellHeight * mScaleY;      // Calculate Scaled Character Height
        float offsetX = chrWidth / 2.0f - mFontPadX * mScaleX;   // Adjust Start X
        float offsetY = chrHeight / 2.0f - mFontPadY * mScaleY;  // Adjust Start Y
        return new PointF(offsetX * mNormalizedUnitX, offsetY * mNormalizedUnitY);
    }

    public void updateNormalizedUnit(int width, int height) {
        mNormalizedUnitX = 2f / width;
        mNormalizedUnitY = 2f / height;
    }

    public void setScale(float sx, float sy) {
        mScaleX = sx;
        mScaleY = sy;
    }

    public float getScaleX() {
        return mScaleX;
    }
    public float getScaleY() {
        return mScaleY;
    }

    public void setSpace(float space) {
        mSpaceX = space;
    }

    public float getSpace() {
        return mSpaceX;
    }

    //--Get Length of a String--//
    // D: return the length of the specified string if rendered using current settings
    // A: text - the string to get length for
    // R: the length of the specified string (pixels)
    public float getLength(String text) {
        return getLength(text, false);
    }

    public float getLength(String text, boolean normalized) {
        float result = 0.0f;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            int c = (int) text.charAt(i) - CHAR_START;
            result += (mCharWidths[c] * mScaleX);
        }
        result += length > 1 ? (length - 1) * mSpaceX * mScaleX : 0;  // Add Space Length
        return result * (normalized ? mNormalizedUnitX : 1);
    }

    public float getCharWidth(char chr) {
        int c = chr - CHAR_START;
        return mCharWidths[c] * mScaleX;
    }
    public float getCharWidthMax() {
        return mCharWidthMax * mScaleX;
    }
    public float getCharHeight() {
        return mCharHeight * mScaleY;
    }

    public float getAscent() {
        return mFontAscent * mScaleY;
    }
    public float getDescent() {
        return mFontDescent * mScaleY;
    }
    public float getHeight() {
        return mFontHeight * mScaleY;
    }

//    public void drawTexture(int width, int height, float[] vpMatrix) {
//        initDraw(1.0f, 1.0f, 1.0f, 1.0f);
//
//        mBatch.beginBatch(vpMatrix);                  // Begin Batch (Bind Texture)
//        float[] idMatrix = new float[16];
//        Matrix.setIdentityM(idMatrix, 0);
//        mBatch.drawSprite(width - mTextureSize / 2f, height - mTextureSize / 2f,
//                mTextureSize, mTextureSize, mTextureRgn, idMatrix);  // Draw
//        mBatch.endBatch();                               // End Batch
//    }

    private class SpriteBatch {
        final static int VERTEX_SIZE = 4;                  // Vertex Size (in Components) ie. (X,Y,U,V)
        final static int VERTICES_PER_SPRITE = 4;          // Vertices Per Sprite
        final static int INDICES_PER_SPRITE = 6;           // Indices Per Sprite

        Vertices mVertices;                                 // Vertices Instance Used for Rendering
        float[] mVertexBuffer;                              // Vertex Buffer
        int mBufferIndex;                                   // Vertex Buffer Start Index
        int mMaxSprites;                                    // Maximum Sprites Allowed in Buffer
        int mNumSprites;                                    // Number of Sprites Currently in Buffer

        //--Constructor--//
        // D: prepare the sprite batcher for specified maximum number of sprites
        // A: maxSprites - the maximum allowed sprites per batch
        //    program - program to use when drawing
        public SpriteBatch(int maxSprites) {
            this.mVertexBuffer = new float[maxSprites * VERTICES_PER_SPRITE * VERTEX_SIZE];
            this.mVertices = new Vertices(maxSprites * VERTICES_PER_SPRITE, maxSprites * INDICES_PER_SPRITE);  // Create Rendering Vertices
            this.mBufferIndex = 0;
            this.mMaxSprites = maxSprites;
            this.mNumSprites = 0;

            short[] indices = new short[maxSprites * INDICES_PER_SPRITE];  // Create Temp Index Buffer
            for (int i = 0, j = 0; i < indices.length; i += INDICES_PER_SPRITE, j += VERTICES_PER_SPRITE) {  // FOR Each Index Set (Per Sprite)
                indices[i + 0] = (short) (j + 0);
                indices[i + 1] = (short) (j + 1);
                indices[i + 2] = (short) (j + 2);
                indices[i + 3] = (short) (j + 2);
                indices[i + 4] = (short) (j + 3);
                indices[i + 5] = (short) (j + 0);
            }
            mVertices.setIndices(indices, 0, indices.length);  // Set Index Buffer for Rendering
        }

        public void beginBatch() {
            mNumSprites = 0;                                 // Empty Sprite Counter
            mBufferIndex = 0;                                // Reset Buffer Index (Empty)
        }

        public void endBatch() {
            if (mNumSprites > 0) {
                mVertices.setVertices(mVertexBuffer, 0, mBufferIndex);
                mVertices.bind(mProgram);
                mVertices.draw(GLES20.GL_TRIANGLES, 0, mNumSprites * INDICES_PER_SPRITE);
                mVertices.unbind();
            }
        }

        public void drawSprite(float x, float y, float width, float height, TextureRegion region) {
            if (mNumSprites == mMaxSprites) {               // IF Sprite Buffer is Full
                endBatch();                                 // End Batch
                beginBatch();
            }

            float halfWidth  = width / 2.0f;                // Calculate Half Width
            float halfHeight = height / 2.0f;               // Calculate Half Height
            float x1 = x - halfWidth;                       // Calculate Left X
            float y1 = y - halfHeight;                      // Calculate Bottom Y
            float x2 = x + halfWidth;                       // Calculate Right X
            float y2 = y + halfHeight;                      // Calculate Top Y

            x1 *= mNormalizedUnitX;
            y1 *= mNormalizedUnitY;
            x2 *= mNormalizedUnitX;
            y2 *= mNormalizedUnitY;

            mVertexBuffer[mBufferIndex++] = x1;
            mVertexBuffer[mBufferIndex++] = y1;
            mVertexBuffer[mBufferIndex++] = region.u1;
            mVertexBuffer[mBufferIndex++] = region.v2;

            mVertexBuffer[mBufferIndex++] = x2;
            mVertexBuffer[mBufferIndex++] = y1;
            mVertexBuffer[mBufferIndex++] = region.u2;
            mVertexBuffer[mBufferIndex++] = region.v2;

            mVertexBuffer[mBufferIndex++] = x2;
            mVertexBuffer[mBufferIndex++] = y2;
            mVertexBuffer[mBufferIndex++] = region.u2;
            mVertexBuffer[mBufferIndex++] = region.v1;

            mVertexBuffer[mBufferIndex++] = x1;
            mVertexBuffer[mBufferIndex++] = y2;
            mVertexBuffer[mBufferIndex++] = region.u1;
            mVertexBuffer[mBufferIndex++] = region.v1;

            mNumSprites++;
        }
    }

    private class Vertices {
        final static int POSITION_CNT_2D = 2;              // Number of Components in Vertex Position for 2D
        final static int POSITION_CNT_3D = 3;              // Number of Components in Vertex Position for 3D
        final static int TEXCOORD_CNT = 2;                 // Number of Components in Vertex Texture Coordinates

        final static int INDEX_SIZE = Short.SIZE / 8;      // Index Byte Size (Short.SIZE = bits)

        public final int mPositionCnt;                     // Number of Position Components (2=2D, 3=3D)
        public final int mVertexStride;                    // Vertex Stride (Element Size of a Single Vertex)
        public final int mVertexSize;                      // Byte size of a Single Vertex
        final IntBuffer mVertices;                         // Vertex Buffer
        final ShortBuffer mIndices;                        // Index Buffer
        public int mNumVertices;                           // Number of Vertices in Buffer
        public int mNumIndices;                            // Number of Indices in Buffer
        final int[] mTempBuffer;                           // Temp Buffer for Vertex Conversion

        public Vertices(int maxVertices, int maxIndices) {
            mPositionCnt = POSITION_CNT_2D;  // Set Position Component Count
            mVertexStride = mPositionCnt + TEXCOORD_CNT;
            mVertexSize = mVertexStride * 4;

            ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices * mVertexSize);  // Allocate Buffer for Vertices (Max)
            buffer.order(ByteOrder.nativeOrder());          // Set Native Byte Order
            mVertices = buffer.asIntBuffer();           // Save Vertex Buffer

            if (maxIndices > 0) {                          // IF Indices Required
                buffer = ByteBuffer.allocateDirect(maxIndices * INDEX_SIZE);  // Allocate Buffer for Indices (MAX)
                buffer.order(ByteOrder.nativeOrder());      // Set Native Byte Order
                mIndices = buffer.asShortBuffer();      // Save Index Buffer
            } else {                                        // ELSE Indices Not Required
                mIndices = null;                             // No Index Buffer
            }

            mNumVertices = 0;                                // Zero Vertices in Buffer
            mNumIndices = 0;                                 // Zero Indices in Buffer

            mTempBuffer = new int[maxVertices * mVertexSize / 4];  // Create Temp Buffer
        }

        //--Set Vertices--//
        // D: set the specified mVertices in the vertex buffer
        //    NOTE: optimized to use integer buffer!
        // A: mVertices - array of mVertices (floats) to set
        //    offset - offset to first vertex in array
        //    length - number of floats in the vertex array (total)
        //             for easy setting use: vtx_cnt * (this.mVertexSize / 4)
        // R: [none]
        public void setVertices(float[] vertices, int offset, int length) {
            mVertices.clear();                              // Remove Existing Vertices
            int last = offset + length;                     // Calculate Last Element
            for (int i = offset, j = 0; i < last; i++, j++) // FOR Each Specified Vertex
                mTempBuffer[j] = Float.floatToRawIntBits(vertices[i]);  // Set Vertex as Raw Integer Bits in Buffer
            mVertices.put(mTempBuffer, 0, length);          // Set New Vertices
            mVertices.flip();                               // Flip Vertex Buffer
            mNumVertices = length / mVertexStride;          // Save Number of Vertices
        }

        public void setIndices(short[] indices, int offset, int length) {
            mIndices.clear();                           // Clear Existing Indices
            mIndices.put(indices, offset, length);      // Set New Indices
            mIndices.flip();                            // Flip Index Buffer
            mNumIndices = length;                       // Save Number of Indices
        }

        public void bind(TextShaderProgram program) {
            // bind vertex position pointer
            mVertices.position(0);                      // Set Vertex Buffer to Position
            int positionLocation = program.getPositionAttributeLocation();
            GLES20.glVertexAttribPointer(positionLocation, mPositionCnt, GLES20.GL_FLOAT, false, mVertexSize, mVertices);
            GLES20.glEnableVertexAttribArray(positionLocation);

            // bind texture position pointer
            mVertices.position(mPositionCnt);  // Set Vertex Buffer to Texture Coords (NOTE: position based on whether color is also specified)
            int textureCoordinatesLocation = program.getTextureCoordinatesAttributeLocation();
            GLES20.glVertexAttribPointer(textureCoordinatesLocation, TEXCOORD_CNT, GLES20.GL_FLOAT, false, mVertexSize, mVertices);
            GLES20.glEnableVertexAttribArray(textureCoordinatesLocation);
        }

        //--Draw--//
        // D: draw the currently bound mVertices in the vertex/index buffers
        //    USAGE: can only be called after calling bind() for this buffer.
        // A: primitiveType - the type of primitive to draw
        //    offset - the offset in the vertex/index buffer to start at
        //    mNumVertices - the number of mVertices (mIndices) to draw
        // R: [none]
        public void draw(int primitiveType, int offset, int numVertices)  {
            if (mIndices != null)  {                          // IF Indices Exist
                mIndices.position(offset);                    // Set Index Buffer to Specified Offset
                // draw indexed
                GLES20.glDrawElements(primitiveType, numVertices, GLES20.GL_UNSIGNED_SHORT, mIndices);
            } else {                                         // ELSE No Indices Exist
                // draw direct
                GLES20.glDrawArrays(primitiveType, offset, numVertices);
            }
        }

        public void unbind()  {
            GLES20.glDisableVertexAttribArray(mProgram.getTextureCoordinatesAttributeLocation());
        }
    }
}
