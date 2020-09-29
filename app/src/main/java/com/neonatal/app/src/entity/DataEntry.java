package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "DataEntry")
public class DataEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "dataFieldId")
    private int dataFieldId;

    @ColumnInfo(name = "patientId")
    private int patientId;

    @ColumnInfo(name = "value")
    private String value;

    @ColumnInfo(name = "eventId")
    private int eventId;

    @ColumnInfo(name = "dateEntered")
    private String dateEntered;

    @ColumnInfo(name = "values")
    private String values;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataFieldId() {
        return dataFieldId;
    }

    public void setDataFieldId(int dataFieldId) {
        this.dataFieldId = dataFieldId;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}