package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class GreenTree extends AbstractEffect {
    @Override
    public String name() {
        return "Green Tree";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF1d4010,
                0xFF358879,
                0xFF46a65c,
                0xFF56c157,
                0xFFbeed84,
                0xFFe9fcaf,
                0xFFfffff0,
                0xFFf38760,
                0xFFcd3647,
                0xFF811c4e,
                0xFF6e2c1a,
                0xFF100605
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(4,2,4));
    }
}
