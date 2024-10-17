package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class HotChocolate extends AbstractEffect {
    @Override
    public String name() {
        return "Hot Chocolate";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF210011,
                0xFF4d1f00,
                0xFFf06923,
                0xFFf0f9e3,
                0xFFebaaa0
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(4, 3, 3));
    }
}
