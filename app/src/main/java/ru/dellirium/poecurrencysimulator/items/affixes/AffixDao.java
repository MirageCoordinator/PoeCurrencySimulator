package ru.dellirium.poecurrencysimulator.items.affixes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AffixDao {

    @Query("SELECT * FROM affix")
    List<Affix> getAllMods();

    @Query("SELECT * FROM affix WHERE modId = :id")
    Affix getByModId(String id);

    @Query("DELETE FROM affix")
    void nukeTable();

    @Insert
    void insert(Affix affix);

    @Update
    void update(Affix affix);

    @Delete
    void delete(Affix affix);
}
