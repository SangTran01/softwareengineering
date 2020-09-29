package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.Patient;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface PatientDAO {
    @Query("SELECT * FROM Patient")
    List<Patient> getAll();

    @Insert
    void insertAll(Patient... patients);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("SELECT * FROM patient where id = :id LIMIT 1")
    Patient getById(int id);

    @Query("SELECT * FROM patient where userId = :id")
    List<Patient> getByUserId(int id);
}
