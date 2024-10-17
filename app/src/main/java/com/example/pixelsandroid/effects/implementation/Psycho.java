package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Psycho extends AbstractEffect {

    @Override
    public String name() {
        return "Psycho";
    }

    @Override
    public int[] palette() {
        return palette;
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette);
    }

     final int[] palette = {
            0xFF10101d,
            0xFF49868b,
            0xFF3eca6b,
            0xFF98eb77,
            0xFF7bb1eb,
            0xFF566ed0,
            0xFF443c7e,
            0xFFaa5ec3,
            0xFFfc83c7,
            0xFFd95959,
            0xFF883a56,
            0xFFec955b,
            0xFFffdd84,
            0xFFffffff
    };
}
