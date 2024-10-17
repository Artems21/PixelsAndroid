package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class DualBase extends AbstractEffect {
    @Override
    public String name() {
        return "Dual Base";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFFb17912,
                0xFFffbb2a,
                0xFF013f85,
                0xFF010565,
                0xFF430800
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(5, 5, 8));
    }
}
