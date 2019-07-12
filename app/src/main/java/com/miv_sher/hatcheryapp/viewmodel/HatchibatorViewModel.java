package com.miv_sher.hatcheryapp.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.miv_sher.hatcheryapp.database.AppRepository;
import com.miv_sher.hatcheryapp.database.entities.Egg;
import com.miv_sher.hatcheryapp.database.entities.Profile;
import com.miv_sher.hatcheryapp.database.entities.Session;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HatchibatorViewModel extends AndroidViewModel {
    private final String TAG = "HatchibatorViewModel";
    public MutableLiveData<Session> mLiveSession = new MutableLiveData<>();
    public Profile mLiveProfile;
    public Egg currentEgg;
    Handler handler;
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public HatchibatorViewModel(@NonNull Application application) {
        super(application);
//        Looper.prepare();
        handler = new Handler();
        mRepository = AppRepository.getInstance();
        loadData();
    }

    public void loadData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mLiveProfile = mRepository.getProfile();
                Session currentSession = mRepository.getSessionById(mLiveProfile.currentSessionID);
                loadNewEgg(currentSession);
                mLiveSession.postValue(currentSession);
                Log.d(TAG, "loadData: " + mLiveProfile.toString());
            }
        });
    }

    public void saveCurrentSession(Session session) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "saveSession: " + session);
                mRepository.insertSession(session);

                //TODO: из-за ассинхронности запроса в базу на вставку сессии, на не всегда успевает вернуться id. Перевести на листенеры либо совместное сохранение!
                loadNewEgg(session);
                if (session != null) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mLiveProfile != null) {
                                mLiveProfile.setCurrentSession(session.getId());
                                mRepository.insertProfile(mLiveProfile);
                                Log.d(TAG, "savedSessionToProfile: " + session);

                            }
                        }
                    }, 1000);

                } else {
                    if (mLiveProfile != null) {
                        mLiveProfile.setCurrentSession(-1);
                        mRepository.insertProfile(mLiveProfile);
                    }
                }
                mLiveSession.postValue(session);
            }
        });

    }

    private void loadNewEgg(Session session) {
        if (session != null) {
            currentEgg = mRepository.getEggByKey(session.getEggKey());
        } else {
            currentEgg = null;
        }
    }

}
