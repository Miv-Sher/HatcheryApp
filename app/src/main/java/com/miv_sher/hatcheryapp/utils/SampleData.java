package com.miv_sher.hatcheryapp.utils;

import com.miv_sher.hatcheryapp.database.BeastEntity;
import com.miv_sher.hatcheryapp.database.EggEntity;
import com.miv_sher.hatcheryapp.database.SessionEntity;

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


    public static List<SessionEntity> getSessions() {
        List<SessionEntity> sessionEntityList = new ArrayList<>();
        Date nowDate = new Date();
        sessionEntityList.add(new SessionEntity(nowDate, getDatePlusMinutes(nowDate, 20), EGG_KEY_ONE));
        sessionEntityList.add(new SessionEntity(getDatePlusMinutes(nowDate, 30), getDatePlusMinutes(nowDate, 50), EGG_KEY_TWO));
        sessionEntityList.add(new SessionEntity(getDatePlusMinutes(nowDate, 90), getDatePlusMinutes(nowDate, 15), EGG_KEY_THREE));
        return sessionEntityList;
    }

    public static List<BeastEntity> getBeasts() {
        List<BeastEntity> beastEntityList = new ArrayList<>();
        beastEntityList.add(new BeastEntity(BEAST_KEY_ONE, 0, BEAST_KEY_ONE));
        beastEntityList.add(new BeastEntity(BEAST_KEY_TWO, 0, BEAST_KEY_TWO));
        beastEntityList.add(new BeastEntity(BEAST_KEY_THREE, 0, BEAST_KEY_THREE));
        return beastEntityList;
    }

    public static List<EggEntity> getEggs() {
        List<EggEntity> eggsEntityList = new ArrayList<>();
        eggsEntityList.add(new EggEntity(BEAST_KEY_ONE, 0, 3));
        eggsEntityList.add(new EggEntity(BEAST_KEY_TWO, 0, 0));
        eggsEntityList.add(new EggEntity(BEAST_KEY_THREE, 0, 1));
        return eggsEntityList;
    }

}
