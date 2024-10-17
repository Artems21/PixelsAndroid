package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class SuggarWorld extends AbstractEffect {
    @Override
    public String name() {
        return "Suggar World";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF302387,
                0xFFff3796,
                0xFF00faac,
                0xFFfffdaf
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
