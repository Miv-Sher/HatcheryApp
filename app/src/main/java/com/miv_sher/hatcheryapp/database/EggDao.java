package com.miv_sher.hatcheryapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EggDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEgg(EggEntity eggEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EggEntity> eggEntityList);

    @Delete
    void deleteEgg(EggEntity eggEntity);

    @Query("SELECT * FROM eggs WHERE `key` = :eggKey")
    EggEntity getEggByKey(String eggKey);

    @Query("SELECT * FROM eggs WHERE `boughtCount` > 0")
    LiveData<List<EggEntity>> getBoughtEggs();

    @Query("SELECT * FROM eggs ORDER BY `key` DESC")
    LiveData<List<EggEntity>> getAll();

    @Query("DELETE FROM eggs")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM eggs")
    int getCount();
}

