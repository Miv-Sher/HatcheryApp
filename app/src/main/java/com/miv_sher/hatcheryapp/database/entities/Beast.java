package com.miv_sher.hatcheryapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beasts")
public class Beast {
    @PrimaryKey @NonNull
    private String key;
    private int resId;
    private String description;

    public Beast(String key, int resId, String description) {
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
        return "Beast{" +
                "key=" + key +
                ", resId=" + resId + ", descroption=" + description + '\'' +
                '}';
    }
}
