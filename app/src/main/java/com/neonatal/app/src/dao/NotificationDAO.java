package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.Notification;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface NotificationDAO {
    @Query("SELECT * FROM Notification")
    List<Notification> getAll();

    @Insert
    void insertAll(Notification... notifications);

    @Update
    void update(Notification notification);

    @Delete
    void delete(Notification notification);
}
