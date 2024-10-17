package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class T800 extends AbstractEffect {
    @Override
    public String name() {
        return "T-800";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFF880011,
                0xFFFF0022,
                0xFFFFFFFF
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
