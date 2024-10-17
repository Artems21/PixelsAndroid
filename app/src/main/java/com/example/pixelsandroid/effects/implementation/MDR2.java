package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class MDR2 extends AbstractEffect {
    @Override
    public String name() {
        return "MDR 2";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF44d193,
                0xFF18a55a,
                0xFF167d3b,
                0xFF226331,
                0xFF05270c,
                0xFF001930,
                0xFF194063,
                0xFF0e4c82,
                0xFF1179b1,
                0xFF34b0dc,
                0xFF70eeff
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
