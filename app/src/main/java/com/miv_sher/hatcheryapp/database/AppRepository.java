package com.miv_sher.hatcheryapp.database;

import android.content.Context;
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
    public LiveData<Profile> mProfile;

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
        mSessions = getAllSessions();
        mProfile = getProfile();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.sessionDao().insertAll(SampleData.getSessions());
            }
        });
    }

    private LiveData<List<Session>> getAllSessions() {
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

    public Session getSessionById(int sessionId) {
        return mDb.sessionDao().getSessionByID(sessionId);
    }

    public void insertNote(final Session session) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.sessionDao().insertSession(session);
            }
        });
    }

    public void deleteNote(final Session session) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.sessionDao().deleteSession(session);
            }
        });
    }

    private LiveData<Profile> getProfile(){
        return mDb.profileDao().getProfile();
    }

    public void insertProfile(final Profile profile){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.profileDao().insertProfile(profile);
            }
        });
    }
}
