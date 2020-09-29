package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by Maurice on 11/30/2017.
 */

@Entity(tableName = "JournalEntry")
public class JournalEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="imageString")
    private String imageString;

    @ColumnInfo(name="bodyText")
    private String bodyText;

    @ColumnInfo(name="milestoneId")
    private int milestoneId;

    @ColumnInfo(name="milestoneName")
    private String milestoneName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }
}