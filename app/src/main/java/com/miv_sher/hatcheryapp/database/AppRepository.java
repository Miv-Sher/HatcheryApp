package com.miv_sher.hatcheryapp.database;

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
    public LiveData<List<Session>> mSessions;
    public LiveData<List<Egg>> mEggs;
    public LiveData<List<Beast>> mBeasts;

    private static AppRepository ourInstance;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new AppRepository();
        }
        return ourInstance;
    }

    private AppRepository() {
        mDb = AppDatabase.getInstance();
        //mSessions = getAllSessions();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (AppRepository.getInstance().getProfile() == null)
                    AppRepository.getInstance().insertProfile(new Profile("phone_imei", -1, 100));
                if (AppRepository.getInstance().getAllSessions().size() == 0)
                    mDb.sessionDao().insertAll(SampleData.getSessions());
                //if (AppRepository.getInstance().getAllEggs().size() == 0)
                    mDb.eggDao().insertAll(SampleData.getEggs());
               // if (AppRepository.getInstance().getAllBeasts().size() == 0)
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
                if(session != null) {
                    long id = mDb.sessionDao().insertSession(session);
                    session.setId(id);
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
