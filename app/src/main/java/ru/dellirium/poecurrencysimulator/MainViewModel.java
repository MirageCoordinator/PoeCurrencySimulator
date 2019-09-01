package ru.dellirium.poecurrencysimulator;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private PoeItem vaalRegalia;
    private DefaultSocketRolls socketRoller;
    private int counterFusings;
    private int counterSockets = -1;

    private MutableLiveData<PoeItem> item;

    LiveData<PoeItem> getItem() {
        if (item == null) {
            item = new MutableLiveData<>();
            createItem();
        }
        return item;
    }

    private void createItem() {
        vaalRegalia = (new PoeItemBuilder(6))
                .setRequirements(new int[]{0, 0, 190})
                .build();
        socketRoller = new DefaultSocketRolls(vaalRegalia);
        rollSockets();
    }

    void rollSockets() {
        if (socketRoller.rollSockets()) {
            counterSockets++;
        }
        item.setValue(vaalRegalia);
    }

    void rollLinks() {
        socketRoller.rollLinks();
        if (!vaalRegalia.isAlreadySixLinked()) {
            counterFusings++;
        }
        item.setValue(vaalRegalia);
    }

    void rollColors() {
        socketRoller.rollColors();
        item.setValue(vaalRegalia);
    }

    PoeItem getVaalRegalia() {
        return vaalRegalia;
    }

    int getCounterFusings() {
        return counterFusings;
    }

    int getCounterSockets() {
        return counterSockets;
    }
}
