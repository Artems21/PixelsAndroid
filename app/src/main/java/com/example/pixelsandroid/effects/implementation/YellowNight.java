package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class YellowNight extends AbstractEffect {
    @Override
    public String name() {
        return "Yellow Night";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFF000000,
                0xFFffff20
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
