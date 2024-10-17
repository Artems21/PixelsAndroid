package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class UndergroundWorms extends AbstractEffect {
    @Override
    public String name() {
        return "Undergroung Worms";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFf3e3ca,
                0xFFd8c072,
                0xFFbeaa87,
                0xFFb89251,
                0xFF9f866c,
                0xFF8f6444,
                0xFF714235,
                0xFF4b362a,
                0xFF282737,
                0xFF384869,
                0xFF3f6b92,
                0xFF5c94b1,
                0xFF69b6c2,
                0xFF96e4e4,
                0xFF7ecc9e,
                0xFF65a972,
                0xFF4c8549,
                0xFF4d5c3b,
                0xFF444347,
                0xFF61606c,
                0xFF7b7995,
                0xFF9997a9,
                0xFFb6b9be,
                0xFFfeffef,
                0xFFe0b2c9,
                0xFFc390b7,
                0xFFb16b6b,
                0xFFa162a7,
                0xFF9e4141,
                0xFF6b5286
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(6 ,6, 4));
    }
}
