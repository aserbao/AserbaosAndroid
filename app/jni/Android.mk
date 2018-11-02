LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
# 导出的so库名字
LOCAL_MODULE := use_ndk_build
# 对应的c代码
LOCAL_SRC_FILES := use_ndk_build.c use_ndk_build2.c
include $(BUILD_SHARED_LIBRARY)

