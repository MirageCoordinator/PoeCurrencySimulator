package ru.dellirium.poecurrencysimulator;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ru.dellirium.poecurrencysimulator.items.DefaultSocketRolls;
import ru.dellirium.poecurrencysimulator.items.PoeItem;
import ru.dellirium.poecurrencysimulator.items.PoeItemBuilder;

public class MainViewModel extends ViewModel {
    private PoeItem vaalRegalia;
    private DefaultSocketRolls socketRoller;
    private int counterFusings;
    private int counterSockets = -1;

    private MutableLiveData<PoeItem> item;

    public LiveData<PoeItem> getItem() {
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

    public void rollSockets() {
        if (socketRoller.rollSockets()) {
            counterSockets++;
        }
        item.setValue(vaalRegalia);
    }

    public void rollLinks() {
        socketRoller.rollLinks();
        if (!vaalRegalia.isAlreadySixLinked()) {
            counterFusings++;
        }
        item.setValue(vaalRegalia);
    }

    public void rollColors() {
        socketRoller.rollColors();
        item.setValue(vaalRegalia);
    }

    public PoeItem getVaalRegalia() {
        return vaalRegalia;
    }

    public int getCounterFusings() {
        return counterFusings;
    }

    public int getCounterSockets() {
        return counterSockets;
    }
}
