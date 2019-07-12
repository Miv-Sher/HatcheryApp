package com.miv_sher.hatcheryapp.utils;

import com.miv_sher.hatcheryapp.R;
import com.miv_sher.hatcheryapp.database.entities.Beast;
import com.miv_sher.hatcheryapp.database.entities.Egg;
import com.miv_sher.hatcheryapp.database.entities.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.miv_sher.hatcheryapp.Constants.RARITY_COMMON;
import static com.miv_sher.hatcheryapp.Constants.RARITY_LEGENDARY;
import static com.miv_sher.hatcheryapp.Constants.RARITY_RARE;
import static com.miv_sher.hatcheryapp.Constants.RARITY_UNCOMMON;
import static com.miv_sher.hatcheryapp.utils.Utils.getDatePlusMinutes;

public class SampleData {
    public static String EGG_KEY_ONE = "egg_key_one_";
    private static String EGG_KEY_TWO = "egg_key_two_";
    private static String EGG_KEY_THREE = "egg_key_three_";
    private static String EGG_KEY_FOUR = "egg_key_four";
    private static String EGG_KEY_FIVE = "egg_key_five";

    private static String BEAST_KEY_TIGER = "beast_key_tiger";
    private static String BEAST_KEY_OWL = "beast_key_owl";
    private static String BEAST_KEY_OTTER = "beast_key_otter";
    private static String BEAST_KEY_DRAGON = "beast_key_dragon";
    private static String BEAST_KEY_DEER = "beast_key_deer";
    private static String BEAST_KEY_FOX = "beast_key_fox";
    private static String BEAST_KEY_GRIFFON = "beast_key_griffon";
    private static String BEAST_KEY_IRBIS = "beast_key_irbis";
    private static String BEAST_KEY_UNICORN = "beast_key_unicorn";
    private static String BEAST_KEY_WOLF = "beast_key_wolf";


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
        beastList.add(new Beast(BEAST_KEY_TIGER, R.drawable.tiger, BEAST_KEY_TIGER));
        beastList.add(new Beast(BEAST_KEY_OWL, R.drawable.owl, BEAST_KEY_OWL));
        beastList.add(new Beast(BEAST_KEY_OTTER, R.drawable.otter, BEAST_KEY_OTTER));
        beastList.add(new Beast(BEAST_KEY_DRAGON, R.drawable.dragon, BEAST_KEY_DRAGON));
        beastList.add(new Beast(BEAST_KEY_DEER, R.drawable.deer, BEAST_KEY_DEER));
        beastList.add(new Beast(BEAST_KEY_FOX, R.drawable.fox, BEAST_KEY_FOX));
        beastList.add(new Beast(BEAST_KEY_GRIFFON, R.drawable.griffon, BEAST_KEY_GRIFFON));
        beastList.add(new Beast(BEAST_KEY_IRBIS, R.drawable.irbis, BEAST_KEY_IRBIS));
        beastList.add(new Beast(BEAST_KEY_UNICORN, R.drawable.unicorn, BEAST_KEY_UNICORN));
        beastList.add(new Beast(BEAST_KEY_WOLF, R.drawable.wolf, BEAST_KEY_WOLF));
        return beastList;
    }

    public static List<Egg> getEggs() {
        List<Egg> eggsEntityList = new ArrayList<>();
        eggsEntityList.add(new Egg(EGG_KEY_ONE, new ArrayList(), RARITY_COMMON, 0, R.drawable.egg, 5, "Обычное яйцо"));
        eggsEntityList.add(new Egg(EGG_KEY_TWO, new ArrayList(), RARITY_UNCOMMON, 0, R.drawable.base_egg, 3, "Необычное яйцо"));
        eggsEntityList.add(new Egg(EGG_KEY_THREE, new ArrayList(), RARITY_RARE, 0, R.drawable.kitten_egg, 2, "Редкое яйцо"));
        eggsEntityList.add(new Egg(EGG_KEY_FOUR, new ArrayList(), RARITY_LEGENDARY, 0, R.drawable.magic_egg, 1, "Ле-ген-дар-ное яйцо"));
        //eggsEntityList.add(new Egg(EGG_KEY_FIVE, new ArrayList(), RARITY_EPIC, 0, R.drawable.egg_five, 0, "Мифическое яйцо"));
        return eggsEntityList;
    }

}
