package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class DreamStar extends AbstractEffect {
    @Override
    public String name() {
        return "Dream Star";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF3c42c4,
                0xFF6e51c8,
                0xFFa065cd,
                0xFFce79d2,
                0xFFd68fb8,
                0xFFdda2a3,
                0xFFeac4ae,
                0xFFf4dfbe
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
