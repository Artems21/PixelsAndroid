package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class LiquidChrome extends AbstractEffect {
    @Override
    public String name() {
        return "Liquid Chrome";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF08141e,
                0xFF0f2a3f,
                0xFF20394f,
                0xFF4e495f,
                0xFF997577,
                0xFFf6d6bd,
                0xFFc3a38a,
                0xFF816271,
                0xFF4e495f
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
