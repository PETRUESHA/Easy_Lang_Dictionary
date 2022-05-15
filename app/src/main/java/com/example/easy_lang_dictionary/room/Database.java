package com.example.easy_lang_dictionary.room;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {User.class}, version = 5)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    public abstract UserDao userDao();
}
