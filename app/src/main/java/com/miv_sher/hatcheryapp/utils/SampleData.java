package com.miv_sher.hatcheryapp.utils;

import com.miv_sher.hatcheryapp.database.entities.Beast;
import com.miv_sher.hatcheryapp.database.entities.Egg;
import com.miv_sher.hatcheryapp.database.entities.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.miv_sher.hatcheryapp.utils.Utils.getDatePlusMinutes;

public class SampleData {
    private static String EGG_KEY_ONE = "egg_key_one";
    private static String EGG_KEY_TWO = "egg_key_two";
    private static String EGG_KEY_THREE = "egg_key_three";

    private static String BEAST_KEY_ONE = "beast_key_one";
    private static String BEAST_KEY_TWO = "beast_key_two";
    private static String BEAST_KEY_THREE = "beast_key_three";


    public static List<Session> getSessions() {
        List<Session> sessionList = new ArrayList<>();
        Date nowDate = new Date();
        sessionList.add(new Session(nowDate, getDatePlusMinutes(nowDate, 20), EGG_KEY_ONE));
        sessionList.add(new Session(getDatePlusMinutes(nowDate, 30), getDatePlusMinutes(nowDate, 50), EGG_KEY_TWO));
        sessionList.add(new Session(getDatePlusMinutes(nowDate, 90), getDatePlusMinutes(nowDate, 15), EGG_KEY_THREE));
        return sessionList;
    }

    public static List<Beast> getBeasts() {
        List<Beast> beastList = new ArrayList<>();
        beastList.add(new Beast(BEAST_KEY_ONE, 0, BEAST_KEY_ONE));
        beastList.add(new Beast(BEAST_KEY_TWO, 0, BEAST_KEY_TWO));
        beastList.add(new Beast(BEAST_KEY_THREE, 0, BEAST_KEY_THREE));
        return beastList;
    }

    public static List<Egg> getEggs() {
        List<Egg> eggsEntityList = new ArrayList<>();
        eggsEntityList.add(new Egg(BEAST_KEY_ONE, 0, 3));
        eggsEntityList.add(new Egg(BEAST_KEY_TWO, 0, 0));
        eggsEntityList.add(new Egg(BEAST_KEY_THREE, 0, 1));
        return eggsEntityList;
    }

}
