package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Win16Classic extends AbstractEffect {
    @Override
    public String name() {
        return "Win16 Classic";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFF7e7e7e,
                0xFFbebebe,
                0xFFffffff,
                0xFF7e0000,
                0xFFfe0000,
                0xFF047e00,
                0xFF06ff04,
                0xFF7e7e00,
                0xFFffff04,
                0xFF00007e,
                0xFF0000ff,
                0xFF7e007e,
                0xFFfe00ff,
                0xFF047e7e,
                0xFF06ffff
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(3,3,3));
    }
}
