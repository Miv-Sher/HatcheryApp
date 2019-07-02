package com.miv_sher.hatcheryapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSession(SessionEntity sessionEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SessionEntity> sessionEntityList);

    @Delete
    void deleteSession(SessionEntity sessionEntity);

    @Query("SELECT * FROM sessions WHERE id = :id")
    SessionEntity getSessionByID(int id);

    @Query("SELECT * FROM sessions ORDER BY startDate DESC")
    LiveData<List<SessionEntity>> getAll();

    @Query("DELETE FROM sessions")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM sessions")
    int getCount();
}
