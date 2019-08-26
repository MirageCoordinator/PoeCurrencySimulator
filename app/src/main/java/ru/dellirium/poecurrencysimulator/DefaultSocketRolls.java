package ru.dellirium.poecurrencysimulator;

import java.util.Arrays;

import static ru.dellirium.poecurrencysimulator.ItemSocket.BLUE;
import static ru.dellirium.poecurrencysimulator.ItemSocket.GREEN;
import static ru.dellirium.poecurrencysimulator.ItemSocket.RED;
import static ru.dellirium.poecurrencysimulator.KoreanRandomUtil.linkChances;
import static ru.dellirium.poecurrencysimulator.KoreanRandomUtil.socketChances;

public class DefaultSocketRolls implements SocketRollableItem {
    private final int CHROMATIC_VALUE = 16;
    private PoeItem item;

    DefaultSocketRolls(PoeItem item) {
        this.item = item;
    }

    @Override
    public boolean rollSockets() {
        if (item.getItemSockets().length != item.getMaxNumberOfSockets()) {
            int n = KoreanRandomUtil.getRandom(socketChances);
            item.setItemSockets(new ItemSocket[n]);
            rollColors();
            rollLinks();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void rollColors() {
        int[] colorList = {calculateChromaticValue(item.getStrRequirement()),
                calculateChromaticValue(item.getDexRequirement()),
                calculateChromaticValue(item.getIntRequirement())};
        for (int i = 0; i < item.getItemSockets().length; i++) {
            int color = KoreanRandomUtil.getRandom(colorList);
            switch (color) {
                case 0:
                    item.getItemSockets()[i] = new ItemSocket(RED);
                    break;
                case 1:
                    item.getItemSockets()[i] = new ItemSocket(GREEN);
                    break;
                case 2:
                    item.getItemSockets()[i] = new ItemSocket(BLUE);
                    break;
            }
        }
    }

    @Override
    public boolean rollLinks() {
        if (item.isAlreadySixLinked()) {
            return false;
        }
        int i = 0;
        for (; i < item.getItemSockets().length - 1; i++) {
            int[] linksChancesSmallerCopy = Arrays.copyOf(linkChances, item.getItemSockets().length - i);
            int linksRoll = KoreanRandomUtil.getRandom(linksChancesSmallerCopy);

            if (sixLinkFlag(linksRoll)) return true;

            for (int j = 0; j < linksRoll; j++) {
                item.getItemLinks()[i] = true;
                i++;
            }
            if (i < item.getItemLinks().length) {
                item.getItemLinks()[i] = false;
            }
        }
        for (; i < item.getItemLinks().length - 1; i++) {
            item.getItemLinks()[i] = false;
        }
        return true;
    }

    private boolean sixLinkFlag(int linksRoll) {
        if (linksRoll == item.getItemLinks().length) {
            for (int i = 0; i < item.getItemLinks().length; i++) {
                item.getItemLinks()[i] = true;
            }
            item.setAlreadySixLinked(true);
            return true;
        }
        return false;
    }

    private int calculateChromaticValue(int mainAttribute) {
        double doubleMainAttribute = (double) mainAttribute;
        return (int) ((doubleMainAttribute + CHROMATIC_VALUE) / (item.getStrRequirement() + item.getDexRequirement() + item.getIntRequirement() + 3 * CHROMATIC_VALUE) * 1000000);
    }

}
