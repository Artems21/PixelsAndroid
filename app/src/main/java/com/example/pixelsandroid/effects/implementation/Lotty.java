package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Lotty extends AbstractEffect {
    @Override
    public String name() {
        return "Lotty";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF260d34,
                0xFF452459,
                0xFF87286a,
                0xFFd03791,
                0xFFfe6c90,
                0xFFffffff
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
