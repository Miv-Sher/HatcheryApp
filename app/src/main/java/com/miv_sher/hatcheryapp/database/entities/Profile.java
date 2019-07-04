package com.miv_sher.hatcheryapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "profiles")
public class Profile {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String key;
    public int currentSessionID;
    public int coinsCount;

    public Profile(int id, String key, int currentSessionID, int coinsCount) {
        this.id = id;
        this.key = key;
        this.currentSessionID = currentSessionID;
        this.coinsCount = coinsCount;
    }

    @Ignore
    public Profile(String key, int currentSessionID, int coinsCount) {
        this.key = key;
        this.currentSessionID = currentSessionID;
        this.coinsCount = coinsCount;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public int getCurrentSessionID() {
        return currentSessionID;
    }

    public int getCoinsCount() {
        return coinsCount;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public void setCurrentSession(int currentSessionID) {
        this.currentSessionID = currentSessionID;
    }

    public void setCoinsCount(int coinsCount) {
        this.coinsCount = coinsCount;
    }

    @Override
    public String toString() {
        return "Profile{" + "id=" + id +
                " key=" + key +
                ", currentSession=" + currentSessionID + ", coinsCount=" + coinsCount + '\'' +
                '}';
    }
}
