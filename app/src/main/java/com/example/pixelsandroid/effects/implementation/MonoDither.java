package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.PaletteDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class MonoDither extends AbstractEffect {
    @Override
    public String name() {
        return "Mono Dither";
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
        return new PaletteDitheringAlgorithm(palette());
    }
}
