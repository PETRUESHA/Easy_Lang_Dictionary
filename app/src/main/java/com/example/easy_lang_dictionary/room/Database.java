package com.example.easy_lang_dictionary.room;

import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {User.class}, version = 2)
public abstract class Database extends RoomDatabase {
    public abstract UserDao userDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE User ADD COLUMN profile_name String");
        }
    };
}
