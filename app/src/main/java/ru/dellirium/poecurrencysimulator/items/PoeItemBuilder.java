package ru.dellirium.poecurrencysimulator.items;

public class PoeItemBuilder {
    private final PoeItem item;

    public PoeItemBuilder(int MaxNumberOfSockets) {
        item = new PoeItem(MaxNumberOfSockets);
    }

    public PoeItemBuilder setRequirements(int[] requirements) {
        item.setStrRequirement(requirements[0]);
        item.setDexRequirement(requirements[1]);
        item.setIntRequirement(requirements[2]);
        return this;
    }

    public PoeItemBuilder setTags(String[] tags) {
        item.setTags(tags);
        return this;
    }

    public PoeItem build() {
        return item;
    }
}
