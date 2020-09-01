package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects;


import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.data.VertexArray;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs.ColorShaderProgram;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util.Geometry;

import java.util.List;

/**
 * Created by bbo on 12/22/15.
 */
public class SummaryGraph {
    private static final int POSITION_COMPONENT_COUNT = 3;

    private VertexArray mVertexArray;
    private List<ObjectBuilder.DrawCommand> mDrawList;
    private Configuration mConfiguration;

    public SummaryGraph() {
    }

    public void updateGraph(Configuration configuration) {
        float[] graphData = configuration.getGraphData();
        if (graphData != null && graphData.length > 0) {
            Geometry.Point spacing = new Geometry.Point(
                    2f / (graphData.length / configuration.getGraphPageSize()),
                    configuration.getGraphHeight(),
                    0f);
            ObjectBuilder.GeneratedData generatedData = ObjectBuilder.createSummaryGraph(
                    new Geometry.Graph(graphData, configuration.getGraphOrigin(), spacing),
                    graphData.length);

            mVertexArray = new VertexArray(generatedData.mVertexData);
            mDrawList = generatedData.mDrawList;
        }
        mConfiguration = configuration;
    }

    public void bindData(ColorShaderProgram colorProgram) {
        if (mVertexArray != null) {
            mVertexArray.setVertexAttribPointer(
                    0,
                    colorProgram.getPositionAttributeLocation(),
                    POSITION_COMPONENT_COUNT, 0);
        }
    }

    public void draw() {
        if (mDrawList != null) {
            for (ObjectBuilder.DrawCommand drawCommand : mDrawList) {
                drawCommand.draw();
            }
        }
    }

    public Configuration getConfiguration() {
        return mConfiguration;
    }

    public static class Configuration {
        private float[] mGraphData;
        private Geometry.Point mGraphOrigin;
        private float   mGraphPageSize;
        private float   mGraphHeight;

        public Configuration(Builder builder) {
            mGraphData     = builder.mGraphData;
            mGraphOrigin   = builder.mGraphOrigin;
            mGraphPageSize = builder.mGraphPageSize;
            mGraphHeight   = builder.mGraphHeight;
        }

        public float[] getGraphData() {
            return mGraphData;
        }

        public Geometry.Point getGraphOrigin() {
            return mGraphOrigin;
        }

        public float getGraphPageSize() {
            return mGraphPageSize;
        }

        public float getGraphHeight() {
            return mGraphHeight;
        }

        public static class Builder {
            float[] mGraphData;
            Geometry.Point mGraphOrigin;
            float   mGraphPageSize;
            float   mGraphHeight;

            public Builder() {
            }

            public Builder setGraphData(float[] graphData) {
                mGraphData = graphData;
                return this;
            }

            public Builder setGraphPageSize(float graphPageSize) {
                mGraphPageSize = graphPageSize;
                return this;
            }

            public Builder setGraphOrigin(Geometry.Point graphOrigin) {
                mGraphOrigin = graphOrigin;
                return this;
            }

            public Builder setGraphHeight(float graphHeight) {
                mGraphHeight = graphHeight;
                return this;
            }

            public Configuration build() {
                return new Configuration(this);
            }
        }
    }
}
