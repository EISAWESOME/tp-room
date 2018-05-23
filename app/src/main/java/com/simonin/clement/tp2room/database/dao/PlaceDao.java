package com.simonin.clement.tp2room.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.simonin.clement.tp2room.database.entity.Place;

import java.util.List;

@Dao
public interface PlaceDao {

    @Query("SELECT * FROM place")
    LiveData<List<Place>> subscribeAll();

    @Query("SELECT * FROM place")
    List<Place> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);

    @Delete
    void delete(Place place);

}
