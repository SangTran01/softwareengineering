package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "GeneralEventParticipant")
public class GeneralEventParticipant {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="generalEventId")
    private int generalEventId;

    @ColumnInfo(name="personId")
    private int personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneralEventId() {
        return generalEventId;
    }

    public void setGeneralEventId(int generalEventId) {
        this.generalEventId = generalEventId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}