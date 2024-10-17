package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class BK0010 extends AbstractEffect {
    @Override
    public String name() {
        return "BK0010";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFF0000FF,
                0xFFFF0000,
                0xFF00FF00
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
