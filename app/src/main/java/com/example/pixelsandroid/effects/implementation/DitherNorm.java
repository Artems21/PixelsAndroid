package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.PaletteDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class DitherNorm extends AbstractEffect {
    @Override
    public String name() {
        return "Dither Norm";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF0f0f1b,
                0xFF565a75,
                0xFFc6b7be,
                0xFFfafbf6
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new PaletteDitheringAlgorithm(palette());
    }
}
