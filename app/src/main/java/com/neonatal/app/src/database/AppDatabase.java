package com.neonatal.app.src.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.neonatal.app.src.classes.Converters;
import com.neonatal.app.src.dao.*;
import com.neonatal.app.src.entity.*;

/**
 * Created by Maurice on 11/30/2017.
 */

@Database(entities = {
        CalendarEvent.class,
        CalendarNote.class,
        DataEntry.class,
        DataField.class,
        Event.class,
        GeneralEvent.class,
        GeneralEventParticipant.class,
        JournalEntry.class,
        Milestone.class,
        Notification.class,
        Patient.class,
        Person.class,
        User.class,
        Equipment.class
}, version = 12)
@TypeConverters({Converters.class})

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CalendarEventDAO calendarEventDAO();
    public abstract CalendarNoteDAO calendarNoteDAO();
    public abstract DataEntryDAO dataEntryDAO();
    public abstract DataFieldDAO dataFieldDAO();
    public abstract EventDAO eventDAO();
    public abstract GeneralEventDAO generalEventDAO();
    public abstract GeneralEventParticipantDAO generalEventParticipantDAO();
    public abstract JournalEntryDAO journalEntryDAO();
    public abstract MilestoneDAO milestoneDAO();
    public abstract NotificationDAO notificationDAO();
    public abstract PatientDAO patientDAO();
    public abstract PersonDAO personDAO();
    public abstract UserDAO userDAO();
    public abstract EquipmentDAO equipmentDAO();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "neonatal-database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
