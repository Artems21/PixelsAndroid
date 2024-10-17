package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class FrozenPhoto extends AbstractEffect {
    @Override
    public String name() {
        return "Frozen Photo";
    }

    @Override
    public int[] palette() {
        return new int[] {
                0xFF000000,
                0xFF201533,
                0xFF252446,
                0xFF203562,
                0xFF1e579c,
                0xFF0098db,
                0xFF0ce6f2,
                0xFFffffff
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
