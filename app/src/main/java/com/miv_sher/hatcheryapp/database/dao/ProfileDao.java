package com.miv_sher.hatcheryapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.miv_sher.hatcheryapp.database.entities.Profile;

@Dao
public interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(Profile profile);

    @Delete
    void deleteProfile(Profile profile);

    @Query("SELECT * FROM profiles")
    Profile getProfile();

    @Query("SELECT COUNT(*) FROM profiles")
    int getCount();

}
