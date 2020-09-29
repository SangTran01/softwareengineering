package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "Patient")
public class Patient {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="personId")
    private int personId;

    @ColumnInfo(name="userId")
    private int userId;

    @ColumnInfo(name="gestationalStartDate")
    private String gestationalStartDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGestationalStartDate() {
        return gestationalStartDate;
    }

    public void setGestationalStartDate(String gestationalStartDate) {
        this.gestationalStartDate = gestationalStartDate;
    }
}
