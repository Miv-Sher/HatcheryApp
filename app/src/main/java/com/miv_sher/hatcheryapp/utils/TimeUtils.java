package com.miv_sher.hatcheryapp.utils;

public class TimeUtils {
    public static final int SECONDS_IN_MINUTE = 60;

    public static String getTimerTextFromSecons(int timeInSeconds) {
        int minutes = timeInSeconds / SECONDS_IN_MINUTE;
        int seconds = timeInSeconds % SECONDS_IN_MINUTE;
        return getStringWithZeros(minutes) + ":" + getStringWithZeros(seconds);

    }

    private static String getStringWithZeros(int time) {
        if (time == 0)
            return "00";
        String result = time > 9 ? String.valueOf(time) : ("0" + time);
        return result;
    }

}
