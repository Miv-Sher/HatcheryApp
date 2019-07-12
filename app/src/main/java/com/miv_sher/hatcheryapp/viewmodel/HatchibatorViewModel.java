package com.miv_sher.hatcheryapp.viewmodel;

import android.app.Application;

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
    public MutableLiveData<Session> mLiveSession = new MutableLiveData<>();
    public Profile mLiveProfile;
    private AppRepository mRepository;
    public Egg currentEgg;
    private Executor executor = Executors.newSingleThreadExecutor();

    public HatchibatorViewModel(@NonNull Application application) {
        super(application);
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
            }
        });
    }

    public void saveCurrentSession(Session session) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mRepository.insertSession(session);
               loadNewEgg(session);
                if (session != null) {
                    if (mLiveProfile != null) {
                        mLiveProfile.setCurrentSession(session.getId());
                        mRepository.insertProfile(mLiveProfile);
                    }
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
