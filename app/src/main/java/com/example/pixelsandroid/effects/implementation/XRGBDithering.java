package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.PaletteDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class XRGBDithering extends AbstractEffect {
    @Override
    public String name() {
        return "XRGB Dithering";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFFFF0000,
                0xFF00FF00,
                0xFF0000FF,
                0xFFFFFFFF
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new PaletteDitheringAlgorithm(palette());
    }
}
