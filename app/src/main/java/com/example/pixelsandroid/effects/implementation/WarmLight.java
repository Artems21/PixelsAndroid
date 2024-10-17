package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class WarmLight extends AbstractEffect {
    @Override
    public String name() {
        return "Warm Light";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFF00b6ff,
                0xFFff6c00,
                0xFFff0000,
                0xFF0000ff,
                0xFF009100,
                0xFF6cff00,
                0xFFffff00,
                0xFFff00ff,
                0xFF914700,
                0xFFcc4f00,
                0xFFffb691,
                0xFF476c6c,
                0xFFffffff,
                0xFFdadab6,
                0xFF6c91b6
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(5,4,3));
    }
}
