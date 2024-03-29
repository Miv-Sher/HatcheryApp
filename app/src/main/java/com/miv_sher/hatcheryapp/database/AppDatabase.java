package com.miv_sher.hatcheryapp.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.miv_sher.hatcheryapp.ApplicationLoader;
import com.miv_sher.hatcheryapp.database.converters.DateConverter;
import com.miv_sher.hatcheryapp.database.dao.BeastDao;
import com.miv_sher.hatcheryapp.database.dao.EggDao;
import com.miv_sher.hatcheryapp.database.dao.ProfileDao;
import com.miv_sher.hatcheryapp.database.dao.SessionDao;
import com.miv_sher.hatcheryapp.database.entities.Beast;
import com.miv_sher.hatcheryapp.database.entities.Egg;
import com.miv_sher.hatcheryapp.database.entities.Profile;
import com.miv_sher.hatcheryapp.database.entities.Session;

@Database(entities = {Profile.class, Session.class, Egg.class, Beast.class}, version = 5)
@TypeConverters(DateConverter.class)

public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "HatcheryAppDatabase.db";
    private static final Object LOCK = new Object();
    private static volatile AppDatabase instance;

    public static AppDatabase getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    //TODO убрать distractiveMigration когда устоится схема бд
                    instance = Room.databaseBuilder(ApplicationLoader.getContext(), AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }

    public abstract SessionDao sessionDao();

    public abstract EggDao eggDao();

    public abstract BeastDao beastDao();

    public abstract ProfileDao profileDao();


}
