package com.miv_sher.hatcheryapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BeastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBeast(BeastEntity beastEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<BeastEntity> beastEntityList);

    @Delete
    void deleteBeast(BeastEntity beastEntity);

    @Query("SELECT * FROM beasts WHERE `key` = :beastKey")
    BeastEntity getBeastByKey(String beastKey);

    @Query("SELECT * FROM beasts ORDER BY `key` DESC")
    LiveData<List<BeastEntity>> getAll();

    @Query("DELETE FROM beasts")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM beasts")
    int getCount();
}
