package com.miv_sher.hatcheryapp.database;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.miv_sher.hatcheryapp.utils.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppRepository {
    public LiveData<List<SessionEntity>> mSessions;

    private static AppRepository ourInstance;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mSessions = getAllSessions();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.sessionDao().insertAll(SampleData.getSessions());
            }
        });
    }

    private LiveData<List<SessionEntity>> getAllSessions() {
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

    public SessionEntity getSessionById(int sessionId) {
        return mDb.sessionDao().getSessionByID(sessionId);
    }

    public void insertNote(final SessionEntity sessionEntity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.sessionDao().insertSession(sessionEntity);
            }
        });
    }

    public void deleteNote(final SessionEntity sessionEntity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.sessionDao().deleteSession(sessionEntity);
            }
        });
    }
}
