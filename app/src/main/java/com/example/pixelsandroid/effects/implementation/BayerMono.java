package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class BayerMono extends AbstractEffect {
    @Override
    public String name() {
        return "Bayer Mono";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFFFFFFFF
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
