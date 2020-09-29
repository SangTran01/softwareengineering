package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.Event;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface EventDAO {
    @Query("SELECT * FROM Event")
    List<Event> getAll();

    @Query("SELECT * FROM Event WHERE eventChildId = :eventChildId ORDER BY eventDateTime DESC")
    List<Event> getPatientsEvents(int eventChildId);

    @Query("SELECT eventChildId FROM Event WHERE personId = :personId AND eventType LIKE :eventType ORDER BY eventDateTime DESC")
    List<Integer> getPatientsJournalEvents(int personId, String eventType);

    //This is ordered by ASC for graphs
    @Query("SELECT * FROM Event WHERE eventChildId = :eventChildId AND eventType  LIKE :eventType ORDER BY eventDateTime ASC")
    List<Event> getEventByChildIdASC(int eventChildId, String eventType);

    @Query("SELECT * FROM Event WHERE eventChildId = :eventChildId AND eventType  LIKE :eventType ORDER BY eventDateTime DESC")
    List<Event> getEventByChildId(int eventChildId, String eventType);

    @Query("SELECT * FROM Event WHERE eventChildId = :eventChildId AND personId = :personId AND eventType  LIKE :eventType ORDER BY eventDateTime ASC")
    Event getEventByChildIdAndUserId(int eventChildId, int personId, String eventType);

    @Query("SELECT * FROM Event WHERE id = :eventId")
    Event getEventById(int eventId);

    @Query("SELECT * FROM Event WHERE eventDateTime = :date AND eventChildId = :eventChildId")
    Event getEventByDate(String date, int eventChildId);

    @Insert
    void insertAll(Event... events);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM event where personId = :id AND eventDateTime = :dateTime AND eventType = :type")
    List<Event> getByPersonIdAndTimeAndType(int id, String dateTime, String type);
}
