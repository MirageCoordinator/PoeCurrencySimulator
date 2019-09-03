package ru.dellirium.poecurrencysimulator.items.affixes;

import android.app.Application;

import androidx.room.Room;

public class AffixDbSingleton extends Application {
    public static AffixDbSingleton instance;
    private AffixDB database;

    public static AffixDbSingleton getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AffixDB.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public AffixDB getDatabase() {
        return database;
    }
}
