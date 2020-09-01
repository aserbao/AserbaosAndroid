package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects;


import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.data.VertexArray;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs.ColorShaderProgram;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util.Geometry;

import java.util.List;

/**
 * Created by bbo on 12/22/15.
 */
public class AxisArrow {
    private static final int POSITION_COMPONENT_COUNT = 3;

    public final float mRadius;
    public final float mHeight;

    private final VertexArray mVertexArray;
    private final List<ObjectBuilder.DrawCommand> mDrawList;

    public AxisArrow(float radius, float height, int numOfPoints) {
        ObjectBuilder.GeneratedData generatedData = ObjectBuilder.createAxisArrow(
                new Geometry.Cone(new Geometry.Point(0f, height, 0f), radius, height),
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
