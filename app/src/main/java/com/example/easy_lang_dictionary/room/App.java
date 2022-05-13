package com.example.easy_lang_dictionary.room;


import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class App{
    private Context ctx;
    private static App instance;

    private Database database;

    private App(Context ctx) {
        database = Room.databaseBuilder(ctx, Database.class, "database").addMigrations(Database.MIGRATION_1_2).addMigrations(Database.MIGRATION_2_3).build();
    }

    public static App getInstance(Context ctx) {
        if (instance == null) {
            instance = new App(ctx);
        }
        return instance;
    }

    public Database getDatabase() {
        return database;
    }
}
