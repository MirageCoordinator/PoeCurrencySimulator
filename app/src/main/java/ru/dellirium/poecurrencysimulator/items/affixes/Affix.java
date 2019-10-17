package ru.dellirium.poecurrencysimulator.items.affixes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Affix {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String domain;
    public String modId;
    public String name;
    public String group;
    public String modType;
    public String generationType;
    public int reqLevel;
    public String effect;
    public String tag;
    public String statId;
    int weight;
    public int statMinValue;
    public int statMaxValue;

    public Affix(String domain, String modId, String name, String group, String modType,
                 String generationType, int reqLevel, String effect, String tag, String statId,
                 int weight, int statMinValue, int statMaxValue) {
        this.domain = domain;
        this.modId = modId;
        this.name = name;
        this.group = group;
        this.modType = modType;
        this.generationType = generationType;
        this.reqLevel = reqLevel;
        this.effect = effect;
        this.tag = tag;
        this.statId = statId;
        this.weight = weight;
        this.statMinValue = statMinValue;
        this.statMaxValue = statMaxValue;
    }
}
