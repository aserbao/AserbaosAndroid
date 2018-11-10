LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE :=  libavutil
LOCAL_SRC_FILES := libavutil.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE :=  libswresample
LOCAL_SRC_FILES := libswresample.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE :=  libswscale
LOCAL_SRC_FILES := libswscale.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libavcodec
LOCAL_SRC_FILES := libavcodec.so
include $(PREBUILT_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE := libavformat
LOCAL_SRC_FILES := libavformat.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libavfilter
LOCAL_SRC_FILES := libavfilter.so
include $(PREBUILT_SHARED_LIBRARY)


# Program
include $(CLEAR_VARS)
LOCAL_MODULE := ffmpegdemo
LOCAL_SRC_FILES := aserbaoffmpeg1.c
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
LOCAL_LDLIBS := -llog -lz
include $(BUILD_SHARED_LIBRARY)