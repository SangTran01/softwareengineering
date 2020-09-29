package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.DataEntry;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface DataEntryDAO {
    @Query("SELECT * FROM DataEntry ORDER BY dateEntered ASC")
    List<DataEntry> getAll();

    @Insert
    long[] insertAll(DataEntry... dataEntries);

    @Query("SELECT * FROM DATAENTRY WHERE id = :id LIMIT 1")
    DataEntry getById(int id);

    @Query("SELECT * FROM DATAENTRY WHERE dateEntered = :dateEntered")
    List<DataEntry> getByDate(String dateEntered);

    @Query("SELECT COUNT(*) FROM DATAENTRY WHERE eventId = :eventId")
    int getEntriesPerEvent(int eventId);

    @Query("SELECT * FROM DATAENTRY WHERE patientId = :patientId AND dateEntered = :dateEntered ORDER BY id DESC")
    List<DataEntry> getByPatientAndDate(int patientId, String dateEntered);

    @Update
    void update(DataEntry dataEntry);

    @Delete
    void delete(DataEntry dataEntry);
}
