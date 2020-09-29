package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.GeneralEvent;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface GeneralEventDAO {
    @Query("SELECT * FROM GeneralEvent")
    List<GeneralEvent> getAll();

    @Insert
    void insertAll(GeneralEvent... generalEvents);

    @Update
    void update(GeneralEvent generalEvent);

    @Delete
    void delete(GeneralEvent generalEvent);
}
