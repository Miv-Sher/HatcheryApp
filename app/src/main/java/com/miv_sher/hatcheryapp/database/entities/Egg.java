package com.miv_sher.hatcheryapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.miv_sher.hatcheryapp.database.converters.StringListConverter;

import java.util.List;

@Entity(tableName = "eggs")
public class Egg {
    @PrimaryKey
    @NonNull
    private String key;
    @TypeConverters({StringListConverter.class})
    private List<String> tags;
    private int rarity;
    private int revision;
    private int resId;
    private int boughtCount;
    private String description;

    public Egg(@NonNull String key, List<String> tags, int rarity, int revision, int resId, int boughtCount, String description) {
        this.key = key;
        this.tags = tags;
        this.rarity = rarity;
        this.revision = revision;
        this.resId = resId;
        this.boughtCount = boughtCount;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getRarity() {
        return rarity;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
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
                ", tags=" + tags.toString() +
                ", rarity=" + rarity +
                ", revision=" + revision +
                ", resId=" + resId + ", boughtCount=" + boughtCount +
                ", description=" + description + '\'' +
                '}';
    }
}
