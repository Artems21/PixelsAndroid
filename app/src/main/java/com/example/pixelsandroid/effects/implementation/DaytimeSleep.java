package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class DaytimeSleep extends AbstractEffect {
    @Override
    public String name() {
        return "Daytime Sleep";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF430f43,
                0xFF472561,
                0xFF205973,
                0xFF248077,
                0xFF2d9a77,
                0xFF5ec688,
                0xFFaae68f,
                0xFF64154d,
                0xFF8e184b,
                0xFFba3155,
                0xFFd9505e,
                0xFFe3744f,
                0xFFf29e64,
                0xFFffc477,
                0xFFffdd96,
                0xFFfff4b0,
                0xFFc22e35,
                0xFFd24f38,
                0xFFdf6939,
                0xFFed9b4a
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(4,4,6));
    }
}
