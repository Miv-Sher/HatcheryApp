package com.miv_sher.hatcheryapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.miv_sher.hatcheryapp.database.entities.Egg;

import java.util.List;

@Dao
public interface EggDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEgg(Egg egg);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Egg> eggList);

    @Delete
    void deleteEgg(Egg egg);

    @Query("SELECT * FROM eggs WHERE `key` = :eggKey")
    Egg getEggByKey(String eggKey);

    @Query("SELECT * FROM eggs WHERE `boughtCount` > 0")
    LiveData<List<Egg>> getBoughtEggs();

    @Query("SELECT * FROM eggs ORDER BY `key` DESC")
    List<Egg> getAll();

    @Query("DELETE FROM eggs")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM eggs")
    int getCount();
}

