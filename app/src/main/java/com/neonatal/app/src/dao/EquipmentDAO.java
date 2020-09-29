package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.neonatal.app.src.entity.Equipment;
import com.neonatal.app.src.entity.Event;

import java.util.List;

/**
 * Created by Nenad on 2018-01-06.
 */

@Dao
public interface EquipmentDAO {
    @Query("SELECT * FROM Equipment")
    List<Equipment> getAll();

    @Insert
    void insertAll(Event... events);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);
}
