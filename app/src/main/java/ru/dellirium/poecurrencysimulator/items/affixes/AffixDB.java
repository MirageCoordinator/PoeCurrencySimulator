package ru.dellirium.poecurrencysimulator.items.affixes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Affix.class}, version = 1, exportSchema = false)
public abstract class AffixDB extends RoomDatabase {
    public abstract AffixDao affixDao();
}
