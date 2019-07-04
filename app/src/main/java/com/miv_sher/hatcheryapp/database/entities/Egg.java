package com.miv_sher.hatcheryapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "eggs")
public class Egg {
    @PrimaryKey @NonNull
    private String key;
    private int resId;
    private int boughtCount;

    public Egg(String key, int resId, int boughtCount) {
        this.key = key;
        this.resId = resId;
        this.boughtCount = boughtCount;
    }

    public String getKey() {
        return key;
    }

    public int getResId() {
        return resId;
    }

    public int getBoughtCount() {
        return boughtCount;
    }

    public void setBoughtCount(int boughtCount) {
        this.boughtCount = boughtCount;
    }

    @Override
    public String toString() {
        return "Egg{" +
                "key=" + key +
                ", resId=" + resId + ", boughtCount=" + boughtCount + '\'' +
                '}';
    }
}
