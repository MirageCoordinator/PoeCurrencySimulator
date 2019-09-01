package ru.dellirium.poecurrencysimulator.items;

import java.util.Random;

class KoreanRandomUtil {
    static final int[] socketChances = {1, 920, 882, 784, 294, 48, 10};
    static final int[] linkChances = {17826, 35118, 26717, 19418, 821, 100};

    static int getRandom (int[] array) {
        int sumOfAllElements = 0;
        for (int i1 : array) {
            sumOfAllElements += i1;
        }
        Random random = new Random();
        int randomFromSum = random.nextInt(sumOfAllElements);

        int currentSum = array[0];
        for (int i = 0; i < array.length; i++ ) {
            if (currentSum > randomFromSum) {
                return i;
            } else {
                currentSum += array[i+1];
            }
        }
        return array[array.length - 1];
    }
}
