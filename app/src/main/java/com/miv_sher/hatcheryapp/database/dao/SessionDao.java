package com.miv_sher.hatcheryapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.miv_sher.hatcheryapp.database.entities.Session;

import java.util.List;

@Dao
public interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSession(Session session);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Session> sessionList);

    @Delete
    void deleteSession(Session session);

    @Query("SELECT * FROM sessions WHERE id = :id")
    Session getSessionByID(int id);

    @Query("SELECT * FROM sessions ORDER BY startDate DESC")
    LiveData<List<Session>> getAll();

    @Query("DELETE FROM sessions")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM sessions")
    int getCount();
}
