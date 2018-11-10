//
// Created by aserbao on 2018/11/8.
//

extern "C"
{
#include <stdio.h>
#include <jni.h>
#include "aserbaoffmpeg1.c"
#include "libavformat/avformat.h"
#include "libavcodec/avcodec.h"
#include "libavutil/avutil.h"
#include "libavfilter/avfilter.h"
}
extern "C"
/*
 * Class:     com_example_aserbaoffmpeg_FFmpegUtils
 * Method:    avformatInfo
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_aserbaoffmpeg_FFmpegUtils_avformatInfo
        (JNIEnv *env, jobject obj) {

    char info[40000] = {0};

    av_register_all();

    AVInputFormat *if_temp = av_iformat_next(NULL);
    AVOutputFormat *of_temp = av_oformat_next(NULL);
    //Input
    while (if_temp != NULL) {
        sprintf(info, "%s[In ][%10s]\n", info, if_temp->name);
        if_temp = if_temp->next;
    }
    //Output
    while (of_temp != NULL) {
        sprintf(info, "%s[Out][%10s]\n", info, of_temp->name);
        of_temp = of_temp->next;
    }
    //LOGE("%s", info);
    return (*env)->NewStringUTF(env, info);
}

/*
 * Class:     com_example_aserbaoffmpeg_FFmpegUtils
 * Method:    avcodecInfo
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_aserbaoffmpeg_FFmpegUtils_avcodecInfo
        (JNIEnv *env, jobject obj) {
    char info[40000] = {0};

    av_register_all();

    AVCodec *c_temp = av_codec_next(NULL);

    while (c_temp != NULL) {
        if (c_temp->decode != NULL) {
            sprintf(info, "%s[Dec]", info);
        } else {
            sprintf(info, "%s[Enc]", info);
        }
        switch (c_temp->type) {
            case AVMEDIA_TYPE_VIDEO:
                sprintf(info, "%s[Video]", info);
                break;
            case AVMEDIA_TYPE_AUDIO:
                sprintf(info, "%s[Audio]", info);
                break;
            default:
                sprintf(info, "%s[Other]", info);
                break;
        }
        sprintf(info, "%s[%10s]\n", info, c_temp->name);


        c_temp = c_temp->next;
    }
    //LOGE("%s", info);

    return (*env)->NewStringUTF(env, info);
}
/*
 * Class:     com_example_aserbaoffmpeg_FFmpegUtils
 * Method:    avfilterInfo
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_aserbaoffmpeg_FFmpegUtils_avfilterInfo
        (JNIEnv *env, jobject obj) {
    char info[40000] = {0};
    avfilter_register_all();
    AVFilter *f_temp = (AVFilter *) avfilter_next(NULL);
    int i = 0;
    while (f_temp != NULL) {
        sprintf(info, "%s[%10s]\n", info, f_temp->name);
        f_temp = f_temp->next;
    }

    return (*env)->NewStringUTF(env, info);
}

/*
 * Class:     com_example_aserbaoffmpeg_FFmpegUtils
 * Method:    configurationInfo
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_aserbaoffmpeg_FFmpegUtils_configurationInfo
        (JNIEnv *env, jobject obj) {
    char info[10000] = {0};
    av_register_all();

    sprintf(info, "%s\n", avcodec_configuration());

    //LOGE("%s", info);
    return (*env)->NewStringUTF(env, info);
}
