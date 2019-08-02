package ru.dellirium.poecurrencysimulator;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class ItemSocket {
    @SocketColor
    private final int color;

    @SocketColor
    int getColor() {
        return color;
    }

    ItemSocket(@SocketColor int color) {
        this.color = color;
    }

    static final int WHITE = R.drawable.socket_white;
    static final int RED = R.drawable.socket_red;
    static final int GREEN = R.drawable.socket_green;
    static final int BLUE = R.drawable.socket_blue;
    static final int ABYSSAL = R.drawable.socket_abyssal;
    @IntDef({RED, GREEN, BLUE, WHITE, ABYSSAL})
    @Retention(RetentionPolicy.SOURCE)
    @interface SocketColor {}
}

