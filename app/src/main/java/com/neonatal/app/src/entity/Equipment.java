package com.neonatal.app.src.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Nenad on 2018-01-06.
 */

@Entity(tableName = "Equipment")
public class Equipment {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="equipName")
    private String equipName;

    @ColumnInfo(name="equipDesc")
    private String equipDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getEquipDesc() {
        return equipDesc;
    }

    public void setEquipDesc(String equipDesc) {
        this.equipDesc = equipDesc;
    }
}
