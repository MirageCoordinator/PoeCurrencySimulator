package ru.dellirium.poecurrencysimulator;

import android.arch.lifecycle.ViewModel;

import ru.dellirium.poecurrencysimulator.items.affixes.Affix;
import ru.dellirium.poecurrencysimulator.items.affixes.AffixDB;
import ru.dellirium.poecurrencysimulator.items.affixes.AffixDao;
import ru.dellirium.poecurrencysimulator.items.affixes.AffixDbSingleton;

public class FlasksViewModel extends ViewModel {
    // TODO
    private Affix flaskExtraCharges1;
    private Affix flaskCurseImmunity1;

    public void createSomeMods() {
        AffixDB db = AffixDbSingleton.getInstance().getDatabase();
        AffixDao affixDao = db.affixDao();
        affixDao.nukeTable();

        flaskExtraCharges1 = new Affix("flask", "FlaskExtraCharges1", "Ample",
                "FlaskNumCharges", "FlaskExtraMaxCharges", "Prefix",
                2, "+17 to Maximum Charges", "", "local_extra_max_charges", 1000,
                10, 20);
        affixDao.insert(flaskExtraCharges1);

        flaskCurseImmunity1 = new Affix("flask", "FlaskCurseImmunity1", "of Warding",
                "FlaskCurseImmunity", "FlaskCurseImmunity", "Suffix",
                18, "Immune to Curses during Flask effect\nRemoves Curses on use",
                "default", "local_flask_curse_immunity_while_healing", 500,
                10, 20);
        affixDao.insert(flaskCurseImmunity1);
    }

    public Affix getMod(String id) {
        AffixDB db = AffixDbSingleton.getInstance().getDatabase();
        AffixDao affixDao = db.affixDao();

        return affixDao.getByModId(id);
    }
}
