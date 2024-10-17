package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class PocketConsole extends AbstractEffect {
    @Override
    public String name() {
        return "Pocket Console";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF202020,
                0xFF5e6745,
                0xFFaeba89,
                0xFFe3eec0
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
