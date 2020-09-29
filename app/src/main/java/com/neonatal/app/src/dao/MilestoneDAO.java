package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.Milestone;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface MilestoneDAO {
    @Query("SELECT * FROM Milestone")
    List<Milestone> getAll();

    @Query("SELECT * FROM Milestone WHERE id = :id")
    List<Milestone> getById(int id);

    @Query("SELECT description FROM Milestone WHERE id = :id")
    String getMilestoneName(int id);

    @Query("SELECT DISTINCT category FROM Milestone")
    List<String> getAllMilestoneCatagories();

    @Query("SELECT * FROM Milestone WHERE category = :category")
    List<Milestone> getAllMilestonesInCatagory(String category);

    @Insert
    void insertAll(Milestone... milestones);

    @Update
    void update(Milestone milestone);

    @Delete
    void delete(Milestone milestone);
}
