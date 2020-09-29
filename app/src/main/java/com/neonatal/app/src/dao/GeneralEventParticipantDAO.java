package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.GeneralEventParticipant;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface GeneralEventParticipantDAO {
    @Query("SELECT * FROM GeneralEventParticipant")
    List<GeneralEventParticipant> getAll();

    @Insert
    void insertAll(GeneralEventParticipant... generalEventParticipants);

    @Update
    void update(GeneralEventParticipant generalEventParticipant);

    @Delete
    void delete(GeneralEventParticipant generalEventParticipant);
}
