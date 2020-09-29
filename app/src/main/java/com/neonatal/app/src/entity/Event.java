package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "Event")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="eventDateTime")
    private String eventDateTime;

    @ColumnInfo(name="eventType")
    private String eventType;

    @ColumnInfo(name="eventChildId")
    private int eventChildId;

    //TODO:remove personId?
    @ColumnInfo(name="personId")
    private int personId;

    @ColumnInfo(name="userId")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventChildId() {
        return eventChildId;
    }

    public void setEventChildId(int eventChildId) {
        this.eventChildId = eventChildId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}