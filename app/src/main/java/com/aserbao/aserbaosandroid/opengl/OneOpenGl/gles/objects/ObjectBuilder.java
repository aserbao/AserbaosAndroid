package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects;


import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util.Geometry;

import java.util.ArrayList;
import java.util.List;

import static android.opengl.GLES20.GL_LINE_STRIP;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;

class ObjectBuilder {
    private static final int FLOATS_PER_VERTEX = 3;

    public interface DrawCommand {
        void draw();
    }

    public static class GeneratedData {
        final float[] mVertexData;
        final List<DrawCommand> mDrawList;

        GeneratedData(float[] vertexData, List<DrawCommand> drawList) {
            mVertexData = vertexData;
            mDrawList = drawList;
        }
    }

    static GeneratedData createAxis(Geometry.Cylinder line, int numOfPoints) {
        int size = sizeOfOpenCylinderInVertices(numOfPoints);
        ObjectBuilder builder = new ObjectBuilder(size);

        builder.appendOpenCylinder(line, numOfPoints);

        return builder.build();
    }

    static GeneratedData createAxisArrow(Geometry.Cone arrow, int numOfPoints) {
        int size = sizeOfConeInVertices(numOfPoints);
        ObjectBuilder builder = new ObjectBuilder(size);

        builder.appendCone(arrow, numOfPoints);

        return builder.build();
    }

    static GeneratedData createSummaryGraph(Geometry.Graph graph, int numOfPoints) {
        int size = sizeOfSummaryGraphInVertices(numOfPoints);
        ObjectBuilder builder = new ObjectBuilder(size);

        builder.appendGraph(graph, numOfPoints);

        return builder.build();
    }

    private static int sizeOfCircleInVertices(int numOfPoints) {
        return 1 + (numOfPoints + 1);
    }

    private static int sizeOfOpenCylinderInVertices(int numOfPoints) {
        return (numOfPoints + 1) * 2;
    }

    private static int sizeOfConeInVertices(int numOfPoints) {
        return 1 + (numOfPoints + 1);
    }

    private static int sizeOfSummaryGraphInVertices(int numOfPoints) {
        return numOfPoints;
    }

    private final float[] mVertexData;
    private final List<DrawCommand> mDrawList = new ArrayList<>();
    private int mOffset = 0;

    private ObjectBuilder(int sizeInVertices) {
        mVertexData = new float[sizeInVertices * FLOATS_PER_VERTEX];
    }

    private void appendCircle(Geometry.Circle circle, int numOfPoints) {
        final int startVertex = mOffset / FLOATS_PER_VERTEX;
        final int numVertices = sizeOfCircleInVertices(numOfPoints);

        // Center point of fan
        mVertexData[mOffset++] = circle.center.x;
        mVertexData[mOffset++] = circle.center.y;
        mVertexData[mOffset++] = circle.center.z;

        // Fan around center point. <= is used because we want to generate
        // the point at the starting angle twice to complete the fan.
        for (int i = 0; i <= numOfPoints; i++) {
            double angleInRadians = (i / (double) numOfPoints) * Math.PI * 2;

            mVertexData[mOffset++] = circle.center.x + circle.radius * (float) Math.cos(angleInRadians);
            mVertexData[mOffset++] = circle.center.y;
            mVertexData[mOffset++] = circle.center.z + circle.radius * (float) Math.sin(angleInRadians);
        }
        mDrawList.add(new DrawCommand() {
            @Override
            public void draw() {
                glDrawArrays(GL_TRIANGLE_FAN, startVertex, numVertices);
            }
        });
    }

    private void appendCone(Geometry.Cone cone, int numOfPoints) {
        final int startVertex = mOffset / FLOATS_PER_VERTEX;
        final int numVertices = sizeOfConeInVertices(numOfPoints);

        // Center point of fan
        mVertexData[mOffset++] = cone.center.x;
        mVertexData[mOffset++] = cone.center.y;
        mVertexData[mOffset++] = cone.center.z;

        // Fan around center point. <= is used because we want to generate
        // the point at the starting angle twice to complete the fan.
        for (int i = 0; i <= numOfPoints; i++) {
            double angleInRadians = (i / (double) numOfPoints) * Math.PI * 2;

            mVertexData[mOffset++] = cone.center.x + cone.radius * (float) Math.cos(angleInRadians);
            mVertexData[mOffset++] = cone.center.y - cone.height;
            mVertexData[mOffset++] = cone.center.z + cone.radius * (float) Math.sin(angleInRadians);
        }
        mDrawList.add(new DrawCommand() {
            @Override
            public void draw() {
                glDrawArrays(GL_TRIANGLE_FAN, startVertex, numVertices);
            }
        });
    }

    private void appendOpenCylinder(Geometry.Cylinder cylinder, int numOfPoints) {
        final int startVertex = mOffset / FLOATS_PER_VERTEX;
        final int numVertices = sizeOfOpenCylinderInVertices(numOfPoints);
        final float yStart = cylinder.center.y;
        final float yEnd = cylinder.center.y + cylinder.height;

        // Generate strip around center point. <= is used because we want to
        // generate the points at the starting angle twice, to complete the strip.
        for (int i = 0; i <= numOfPoints; i++) {
            double angleInRadians = (i / (double) numOfPoints) * Math.PI * 2;

            float xPosition = cylinder.center.x + cylinder.radius * (float) Math.cos(angleInRadians);
            float zPosition = cylinder.center.z + cylinder.radius * (float) Math.sin(angleInRadians);

            mVertexData[mOffset++] = xPosition;
            mVertexData[mOffset++] = yStart;
            mVertexData[mOffset++] = zPosition;

            mVertexData[mOffset++] = xPosition;
            mVertexData[mOffset++] = yEnd;
            mVertexData[mOffset++] = zPosition;
        }
        mDrawList.add(new DrawCommand() {
            @Override
            public void draw() {
                glDrawArrays(GL_TRIANGLE_STRIP, startVertex, numVertices);
            }
        });
    }

    private void appendGraph(Geometry.Graph graph, int numOfPoints) {
        final int startVertex = mOffset / FLOATS_PER_VERTEX;
        final int numVertices = numOfPoints;

//        float maxValue = (float) Config.ACCELEROMETER_DATA_MAX_MAGNITUDE;
        float maxValue = (float) 50 * 20;

        // Generate strip around center point. <= is used because we want to
        // generate the points at the starting angle twice, to complete the strip.
        for (int i = 0; i < numVertices; i++) {
            mVertexData[mOffset++] = graph.origin.x + graph.spacing.x * i;
            mVertexData[mOffset++] = graph.origin.y + Math.min(graph.data[i], maxValue) / maxValue * graph.spacing.y;
            mVertexData[mOffset++] = graph.origin.z + graph.spacing.z;
        }
        mDrawList.add(new DrawCommand() {
            @Override
            public void draw() {
                glDrawArrays(GL_LINE_STRIP, startVertex, numVertices);
            }
        });
    }

    private GeneratedData build() {
        return new GeneratedData(mVertexData, mDrawList);
    }
}