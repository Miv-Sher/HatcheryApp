package com.miv_sher.hatcheryapp.database;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.miv_sher.hatcheryapp.database.entities.Beast;
import com.miv_sher.hatcheryapp.database.entities.Egg;
import com.miv_sher.hatcheryapp.database.entities.Profile;
import com.miv_sher.hatcheryapp.database.entities.Session;
import com.miv_sher.hatcheryapp.utils.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppRepository {
    private static AppRepository ourInstance;
    private final String TAG = "AppRepository";
    public LiveData<List<Session>> mSessions;
    public LiveData<List<Egg>> mEggs;
    public LiveData<List<Beast>> mBeasts;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();


    private AppRepository() {
        mDb = AppDatabase.getInstance();
        //mSessions = getAllSessions();
    }

    public static AppRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new AppRepository();
        }
        return ourInstance;
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (getProfile() == null)
                    insertProfile(new Profile("phone_imei", -1, 100));
                if (getAllSessions().size() == 0)
                    mDb.sessionDao().insertAll(SampleData.getSessions());
                mDb.eggDao().insertAll(SampleData.getEggs());
                mDb.beastDao().insertAll(SampleData.getBeasts());
            }
        });
    }

    private List<Session> getAllSessions() {
        return mDb.sessionDao().getAll();
    }

    public void deleteAllSessions() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.sessionDao().deleteAll();
            }
        });
    }

    public Session getSessionById(long sessionId) {
        return mDb.sessionDao().getSessionByID(sessionId);
    }

    /*public Session getCurrentSessionById(int sessionId) {
        return mDb.sessionDao().getSessionByID(sessionId);
    }*/


    public void insertSession(final Session session) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (session != null) {
                    long id = mDb.sessionDao().insertSession(session);
                    session.setId(id);
                    Log.d(TAG, "insertSession: " + session + " " + id);

                }
            }
        });
    }

    public void deleteSession(final Session session) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.sessionDao().deleteSession(session);
            }
        });
    }

    public Egg getEggByKey(String key) {
        return mDb.eggDao().getEggByKey(key);
    }

    public List<Egg> getAllEggs() {
        return mDb.eggDao().getAll();
    }

    public List<Beast> getAllBeasts() {
        return mDb.beastDao().getAll();
    }


    public Profile getProfile() {
        return mDb.profileDao().getProfile();
    }

    public void insertProfile(final Profile profile) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.profileDao().insertProfile(profile);
            }
        });
    }
}
