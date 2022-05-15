package com.example.easy_lang_dictionary.room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.easy_lang_dictionary.Word_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;

@androidx.room.Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll(); 

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
