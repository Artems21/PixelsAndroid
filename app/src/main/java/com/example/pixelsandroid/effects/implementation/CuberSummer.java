package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class CuberSummer extends AbstractEffect {
    @Override
    public String name() {
        return "Cuber Summer";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF3a2b3b,
                0xFF2d4a54,
                0xFF0c7475,
                0xFFbc4a9b,
                0xFFeb8d9c,
                0xFFffd8ba
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(9,3,5));
    }
}
