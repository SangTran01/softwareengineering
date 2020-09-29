package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.CalendarEvent;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface CalendarEventDAO {
    @Query("SELECT * FROM CalendarEvent")
    List<CalendarEvent> getAll();

    @Insert
    void insertAll(CalendarEvent... calendarEvents);

    @Update
    void update(CalendarEvent calendarEvent);

    @Delete
    void delete(CalendarEvent calendarEvent);
}
