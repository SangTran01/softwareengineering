package com.neonatal.app.src.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.neonatal.app.src.entity.Person;

/**
 * Created by Maurice on 11/30/2017.
 */

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM Person")
    List<Person> getAll();

   /* @Insert
    long[] insertAll(List<Person> people);*/

    @Insert
    long[] insertAll(Person... people);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("SELECT * FROM Person WHERE id = :id LIMIT 1")
    Person getById(int id);
}
