package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class BayerMono4 extends AbstractEffect {

    @Override
    public String name() {
        return "Bayer Mono 4";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFF676767,
                0xFFb6b6b6,
                0xFFFFFFFF
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerAlgorithm(palette());
    }
}
