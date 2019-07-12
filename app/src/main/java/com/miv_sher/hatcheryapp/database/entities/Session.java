package com.miv_sher.hatcheryapp.database.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "sessions")
public class Session {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private Date startDate;
    private Date endDate;
    private String eggKey;
    private boolean wasResultShown;
    private boolean isHatcherySucceed;
    private String beastType;

    @Ignore
    public Session() {
    }

    public Session(long id, Date startDate, Date endDate, String eggKey, boolean wasResultShown, boolean isHatcherySucceed, String beastType) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eggKey = eggKey;
        this.wasResultShown = wasResultShown;
        this.isHatcherySucceed = isHatcherySucceed;
        this.beastType = beastType;
    }

    @Ignore
    public Session(Date startDate, Date endDate, String eggKey) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.eggKey = eggKey;
        wasResultShown = false;
        isHatcherySucceed = false;
        beastType = null;
    }

    public void setWasResultShown(boolean wasResultShown) {
        this.wasResultShown = wasResultShown;
    }

    public void setHatcherySucceed(boolean hatcherySucceed) {
        isHatcherySucceed = hatcherySucceed;
    }

    public void setBeastType(String beastType) {
        this.beastType = beastType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getEggKey() {
        return eggKey;
    }

    public boolean isWasResultShown() {
        return wasResultShown;
    }

    public boolean isHatcherySucceed() {
        return isHatcherySucceed;
    }

    public String getBeastType() {
        return beastType;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", startdate=" + startDate +
                ", endDate=" + endDate + ", eggKey='" + eggKey + '\'' +
                '}';
    }
}
