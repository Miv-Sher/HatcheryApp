package com.miv_sher.hatcheryapp.database;

import androidx.room.Entity;

@Entity(tableName = "eggs")
public class EggEntity {
    private String key;
    private int resId;
    private int boughtCount;

    public EggEntity(String key, int resId, int boughtCount) {
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
        return "EggEntity{" +
                "key=" + key +
                ", resId=" + resId + ", boughtCount=" + boughtCount + '\'' +
                '}';
    }
}
