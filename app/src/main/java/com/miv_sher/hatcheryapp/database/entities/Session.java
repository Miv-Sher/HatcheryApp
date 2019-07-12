package com.miv_sher.hatcheryapp.database.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.miv_sher.hatcheryapp.Constants.SESSION_PHASE_ON_GOING;

@Entity(tableName = "sessions")
public class Session {
    String beastKey;
    @PrimaryKey(autoGenerate = true)
    private long id;
    private Date startDate;
    private Date endDate;
    private String eggKey;
    private String phase;

    @Ignore
    public Session() {
    }

    public Session(long id, Date startDate, Date endDate, String eggKey, String phase, String beastKey) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eggKey = eggKey;
        this.phase = phase;
        this.beastKey = beastKey;
    }

    @Ignore
    public Session(Date startDate, Date endDate, String eggKey) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.eggKey = eggKey;
        phase = SESSION_PHASE_ON_GOING;
        beastKey = null;
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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getBeastKey() {
        return beastKey;
    }

    public void setBeastKey(String beastKey) {
        this.beastKey = beastKey;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", startdate=" + startDate +
                ", endDate=" + endDate + ", eggKey='" + eggKey + ", phase='" + phase + ", beastKey='" + beastKey + '\'' +
                '}';
    }
}
