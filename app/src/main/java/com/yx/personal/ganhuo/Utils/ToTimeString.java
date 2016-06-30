package com.yx.personal.ganhuo.Utils;

/**
 * Created by YX on 16/6/30.
 * 将时间长转化为 03' 45" 格式
 */
public class ToTimeString {
    public static String toTimeString(int time){
        int minutes = time / 60;
        int seconds = time % 60;
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(addZeroPrefix(minutes));
        sBuffer.append("' ");
        sBuffer.append(addZeroPrefix(seconds));
        sBuffer.append("''");

        return sBuffer.toString();
    }
    public static String addZeroPrefix(int number){
        if(number < 10){
            return "0"+number;
        }else{
            return ""+number;
        }

    }

}
