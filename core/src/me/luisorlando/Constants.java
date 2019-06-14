package me.luisorlando;

import java.util.Random;

public class Constants {
    public static final float PIXELS_IN_METER = 64f;

    public static int getRand(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static float getRandFloat(float min, float max) {
        return min + new Random().nextFloat() % (max - min);
    }
}
