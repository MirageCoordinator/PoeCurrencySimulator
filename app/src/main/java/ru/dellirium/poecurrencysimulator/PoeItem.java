package ru.dellirium.poecurrencysimulator;

class PoeItem {
    private int strRequirement = 0;
    private int dexRequirement = 0;
    private int intRequirement = 0;
    private String[] tags;

    private final int maxNumberOfSockets;
    private ItemSocket[] itemSockets = new ItemSocket[1];
    private final boolean[] itemLinks = {false, false, false, false, false};
    boolean isAlreadySixLinked;

    PoeItem(int maxNumberOfSockets) {
        this.maxNumberOfSockets = maxNumberOfSockets;
    }

    //region Getters and Setters
    ItemSocket[] getItemSockets() {
        return itemSockets;
    }

    void setItemSockets(ItemSocket[] itemSockets) {
        this.itemSockets = itemSockets;
    }

    int getMaxNumberOfSockets() {
        return maxNumberOfSockets;
    }

    boolean[] getItemLinks() {
        return itemLinks;
    }

    int getStrRequirement() {
        return strRequirement;
    }

    int getDexRequirement() {
        return dexRequirement;
    }

    int getIntRequirement() {
        return intRequirement;
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

    boolean isAlreadySixLinked() {
        return isAlreadySixLinked;
    }

    void setAlreadySixLinked(boolean alreadySixLinked) {
        isAlreadySixLinked = alreadySixLinked;
    }

    //endregion
}
