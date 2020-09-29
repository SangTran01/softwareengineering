package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.JournalEntry;

/**
 * Created by Maurice on 12/6/2017.
 */

@Dao
public interface JournalEntryDAO {
    @Query("SELECT * FROM JournalEntry")
    List<JournalEntry> getAll();

    @Query("SELECT * FROM JournalEntry WHERE id IN(:events)")
    List<JournalEntry> getUsersJournals(List<Integer> events);

    @Query("SELECT * FROM JournalEntry WHERE id IN (:journalIDs) AND milestoneName = :milestoneName")
    List<JournalEntry> getUsersJournalByMilestoneName(List<Integer> journalIDs, String milestoneName);

    @Query("SELECT milestoneId FROM JournalEntry")
    List<Integer> getExsistingMilestones();

    @Insert
    long[] insertAll(JournalEntry... journalEntries);

    @Update
    void update(JournalEntry journalEntry);

    @Delete
    void delete(JournalEntry journalEntry);
}
