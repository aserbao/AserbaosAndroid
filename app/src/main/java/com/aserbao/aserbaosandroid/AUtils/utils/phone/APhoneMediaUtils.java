package com.aserbao.aserbaosandroid.AUtils.utils.phone;

import android.content.Context;
import android.media.AudioManager;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/13 3:22 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.AUtils.utils.phone
 */
public class APhoneMediaUtils {


    public static String getPhoneMediaInfo(Context context){
        StringBuffer sb = new StringBuffer();
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int streamMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //通话音量
        int max = mAudioManager.getStreamMaxVolume( AudioManager.STREAM_VOICE_CALL );
        int current = mAudioManager.getStreamVolume( AudioManager.STREAM_VOICE_CALL );

        //系统音量
        int sysMax = mAudioManager.getStreamMaxVolume( AudioManager.STREAM_SYSTEM );
        int sysCurrent = mAudioManager.getStreamVolume( AudioManager.STREAM_SYSTEM );


        //铃声音量
        int ringMax = mAudioManager.getStreamMaxVolume( AudioManager.STREAM_RING );
        int ringCurrent = mAudioManager.getStreamVolume( AudioManager.STREAM_RING );


        //音乐音量
        int musicMax = mAudioManager.getStreamMaxVolume( AudioManager.STREAM_MUSIC );
        int musicCurrent = mAudioManager.getStreamVolume( AudioManager.STREAM_MUSIC );


        //提示声音音量
        int alarmMax = mAudioManager.getStreamMaxVolume( AudioManager.STREAM_ALARM );
        int alarmCurrent = mAudioManager.getStreamVolume( AudioManager.STREAM_ALARM );


        sb.append("最大通话音量： ").append(String.valueOf(max)).append("当前通话音量：").append(String.valueOf(current)).append("\n")
            .append("最大系统音量： ").append(String.valueOf(sysMax)).append("当前系统音量：").append(String.valueOf(sysCurrent)).append("\n")
            .append("最大铃声音量： ").append(String.valueOf(ringMax)).append("当前铃声音量：").append(String.valueOf(ringCurrent)).append("\n")
            .append("最大音乐音量： ").append(String.valueOf(musicMax)).append("当前音乐音量：").append(String.valueOf(musicCurrent)).append("\n")
            .append("最大alarmMax： ").append(String.valueOf(alarmMax)).append("当前alarmMax：").append(String.valueOf(alarmCurrent));

        return sb.toString();
    }
}
