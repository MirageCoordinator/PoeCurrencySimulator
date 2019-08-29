package ru.dellirium.poecurrencysimulator;

class PoeItemBuilder {
    private final PoeItem item;

    PoeItemBuilder(int MaxNumberOfSockets) {
        item = new PoeItem(MaxNumberOfSockets);
    }

    PoeItemBuilder setRequirements(int[] requirements) {
        item.setStrRequirement(requirements[0]);
        item.setDexRequirement(requirements[1]);
        item.setIntRequirement(requirements[2]);
        return this;
    }

    PoeItemBuilder setTags(String[] tags) {
        item.setTags(tags);
        return this;
    }

    PoeItem build() {
        return item;
    }
}
