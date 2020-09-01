package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects;


import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.data.VertexArray;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs.ColorShaderProgram;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util.Geometry;

import java.util.List;

/**
 * Created by bigbug on 12/21/15.
 */
public class Axis {
    private static final int POSITION_COMPONENT_COUNT = 3;

    public final float mRadius;
    public final float mHeight;

    private final VertexArray mVertexArray;
    private final List<ObjectBuilder.DrawCommand> mDrawList;

    public Axis(float radius, float height, int numOfPoints) {
        ObjectBuilder.GeneratedData generatedData = ObjectBuilder.createAxis(
                new Geometry.Cylinder(new Geometry.Point(0f, 0f, 0f), radius, height),
                numOfPoints);

        mRadius = radius;
        mHeight = height;

        mVertexArray = new VertexArray(generatedData.mVertexData);
        mDrawList = generatedData.mDrawList;
    }

    public void bindData(ColorShaderProgram colorProgram) {
        mVertexArray.setVertexAttribPointer(
                0,
                colorProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT, 0);
    }

    public void draw() {
        for (ObjectBuilder.DrawCommand drawCommand : mDrawList) {
            drawCommand.draw();
        }
    }
}
