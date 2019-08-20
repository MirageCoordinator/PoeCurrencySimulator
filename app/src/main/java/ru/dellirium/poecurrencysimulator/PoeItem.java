package ru.dellirium.poecurrencysimulator;

import java.util.Arrays;

import static ru.dellirium.poecurrencysimulator.ItemSocket.BLUE;
import static ru.dellirium.poecurrencysimulator.ItemSocket.GREEN;
import static ru.dellirium.poecurrencysimulator.ItemSocket.RED;
import static ru.dellirium.poecurrencysimulator.KoreanRandomUtil.linkChances;
import static ru.dellirium.poecurrencysimulator.KoreanRandomUtil.socketChances;

class PoeItem {
    private int strRequirement = 0;
    private int dexRequirement = 0;
    private int intRequirement = 0;
    private String[] tags;
    private final int CHROMATIC_VALUE = 16;
    private final int maxNumberOfSockets;
    private ItemSocket[] itemSockets = new ItemSocket[1];
    private final boolean[] itemLinks = {false, false, false, false, false};
    boolean isAlreadySixLinked;

    PoeItem(int maxNumberOfSockets) {
        this.maxNumberOfSockets = maxNumberOfSockets;
    }

    ItemSocket[] getItemSockets() {
        return itemSockets;
    }

    int getMaxNumberOfSockets() {
        return maxNumberOfSockets;
    }

    boolean[] getItemLinks() {
        return itemLinks;
    }

    boolean rollSockets() {
        if (itemSockets.length != maxNumberOfSockets) {
            int n = KoreanRandomUtil.getRandom(socketChances);
            itemSockets = new ItemSocket[n];
            rollColors();
            rollLinks();
            return true;
        } else {
            return false;
        }
 }

    void rollColors() {
        int[] colorList = {calculateChromaticValue(strRequirement),
                            calculateChromaticValue(dexRequirement),
                            calculateChromaticValue(intRequirement)};
        for (int i = 0; i < itemSockets.length; i++) {
            int color = KoreanRandomUtil.getRandom(colorList);
            switch (color) {
                case 0:
                    itemSockets[i] = new ItemSocket(RED);
                    break;
                case 1:
                    itemSockets[i] = new ItemSocket(GREEN);
                    break;
                case 2:
                    itemSockets[i] = new ItemSocket(BLUE);
                    break;
            }
        }
    }

    private int calculateChromaticValue (int mainAttribute) {
        double doubleMainAttribute = (double) mainAttribute;
        return (int) ((doubleMainAttribute + CHROMATIC_VALUE) / (strRequirement + dexRequirement + intRequirement + 3 * CHROMATIC_VALUE) * 1000000);
    }

    boolean rollLinks() {
        if (isAlreadySixLinked) {
            return false;
        }
        int i = 0;
        for (; i < itemSockets.length - 1; i++) {
            int[] linksChancesSmallerCopy = Arrays.copyOf(linkChances, itemSockets.length - i);
            int linksRoll = KoreanRandomUtil.getRandom(linksChancesSmallerCopy);

            if (sixLinkFlag(linksRoll)) return true;

            for (int j = 0; j < linksRoll; j++) {
                itemLinks[i] = true;
                i++;
            }
            if(i < itemLinks.length) {
                itemLinks[i] = false;
            }
        }
        for (; i < itemLinks.length - 1; i++) {
            itemLinks[i] = false;
        }
        return true;
    }

    private boolean sixLinkFlag(int linksRoll) {
        if (linksRoll == itemLinks.length) {
            for (int i = 0; i < itemLinks.length; i++) {
                itemLinks[i] = true;
            }
            isAlreadySixLinked = true;
            return true;
        }
        return false;
    }

    void setStrRequirement(int strRequirement) {
        this.strRequirement = strRequirement;
    }

    void setDexRequirement(int dexRequirement) {
        this.dexRequirement = dexRequirement;
    }

    void setIntRequirement(int intRequirement) {
        this.intRequirement = intRequirement;
    }

    void setTags(String[] tags) {
        this.tags = tags;
    }
}
