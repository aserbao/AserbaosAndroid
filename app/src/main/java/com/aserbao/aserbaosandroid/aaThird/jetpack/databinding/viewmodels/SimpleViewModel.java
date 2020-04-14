package com.aserbao.aserbaosandroid.aaThird.jetpack.databinding.viewmodels;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.base.utils.random.RandomValue;

import java.util.Random;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/26
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaThird.jetpack.databinding.viewmodels
 */
public class SimpleViewModel extends ViewModel {
    public static final String TEMP_NAME = "名字： ";
    public static final String TEMP_AGE = "成绩： ";
    public MutableLiveData<String> name = new MutableLiveData<>(TEMP_NAME + "aserbao");
    public MutableLiveData<Integer> score = new MutableLiveData<>( 18);

    public void changeName(){
        name.setValue(TEMP_NAME + RandomValue.getChineseName());
    }

    public void changeScore(){
        score.setValue( new Random().nextInt(100) );
    }

    public LiveData<HeadType> mood = Transformations.map(score, new Function<Integer, HeadType>() {
        @Override
        public HeadType apply(Integer input) {
            HeadType headType = HeadType.HAPPY;
            if (input < 60){
                headType = HeadType.SAD;
            }else if(input < 80){
                headType = HeadType.SMILE;
            }
            return headType;
        }
    });

    public enum HeadType{
        SAD,SMILE,HAPPY
    }
}
