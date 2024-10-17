package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.PaletteDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class SmallTown extends AbstractEffect {
    @Override
    public String name() {
        return "Small Town";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF050010,
                0xFF17283c,
                0xFF7d6fb8,
                0xFFc986f6,
                0xFFf9c4ad,
                0xFFfef6f2,
                0xFFf6623e
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(8,8,8));
    }
}
