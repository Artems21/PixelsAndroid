package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class OldSunset extends AbstractEffect {
    @Override
    public String name() {
        return "Old Sunset";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF1e1610,
                0xFFd3ad8b,
                0xFFfcce8d,
                0xFFf3ede3,
                0xFFf95142,
                0xFFff8f46,
                0xFFf2bb4e,
                0xFF84a3a5,
                0xFF4d7c71,
                0xFF405987,
                0xFF1f2f49
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(4, 4, 4));
    }
}
