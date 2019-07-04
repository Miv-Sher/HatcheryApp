package com.miv_sher.hatcheryapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.miv_sher.hatcheryapp.database.entities.Beast;

import java.util.List;

@Dao
public interface BeastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBeast(Beast beast);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Beast> beastList);

    @Delete
    void deleteBeast(Beast beast);

    @Query("SELECT * FROM beasts WHERE `key` = :beastKey")
    Beast getBeastByKey(String beastKey);

    @Query("SELECT * FROM beasts ORDER BY `key` DESC")
    LiveData<List<Beast>> getAll();

    @Query("DELETE FROM beasts")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM beasts")
    int getCount();
}
