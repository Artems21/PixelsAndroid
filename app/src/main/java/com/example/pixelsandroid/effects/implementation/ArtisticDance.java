package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class ArtisticDance extends AbstractEffect {
    @Override
    public String name() {
        return "Artistic Dance";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFff0546,
                0xFF9c173b,
                0xFF660f31,
                0xFF450327,
                0xFF270022,
                0xFF17001d,
                0xFF09010d,
                0xFF0ce6f2,
                0xFF0098db,
                0xFF1e579c
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(3 ,6 ,2));
    }
}
