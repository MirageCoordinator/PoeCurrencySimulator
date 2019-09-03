package ru.dellirium.poecurrencysimulator.items.affixes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Affix {
    @PrimaryKey
    public long id;

    public String modId;
    public String name;
    public String group;
    public String modType;
    public int reqLevel;
    public String effect;
    public String tag;
    public String statId;
    public int statMinValue;
    public int statMaxValue;
}
