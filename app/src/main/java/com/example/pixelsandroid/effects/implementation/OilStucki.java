package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class OilStucki extends AbstractEffect {
    @Override
    public String name() {
        return "Oil Stucki";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFfbf5ef,
                0xFFf2d3ab,
                0xFFc69fa5,
                0xFF8b6d9c,
                0xFF494d7e,
                0xFF272744
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
