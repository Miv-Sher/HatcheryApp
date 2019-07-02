package com.miv_sher.hatcheryapp.database;

import androidx.room.Entity;

@Entity(tableName = "beasts")
public class BeastEntity {
    private String key;
    private int resId;
    private String description;

    public BeastEntity(String key, int resId, String description) {
        this.key = key;
        this.resId = resId;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public int getResId() {
        return resId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "BeastEntity{" +
                "key=" + key +
                ", resId=" + resId + ", descroption=" + description + '\'' +
                '}';
    }
}
