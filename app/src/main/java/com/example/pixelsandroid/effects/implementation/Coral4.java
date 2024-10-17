package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Coral4 extends AbstractEffect {
    @Override
    public String name() {
        return "Coral 4";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF1b0326,
                0xFF7a1c4b,
                0xFFba5044,
                0xFFeff9d6
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
