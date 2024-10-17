package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class ClassicC64 extends AbstractEffect {
    @Override
    public String name() {
        return "Classic C64";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFF626262,
                0xFF898989,
                0xFFadadad,
                0xFFffffff,
                0xFF9f4e44,
                0xFFcb7e75,
                0xFF6d5412,
                0xFFa1683c,
                0xFFc9d487,
                0xFF9ae29b,
                0xFF5cab5e,
                0xFF6abfc6,
                0xFF887ecb,
                0xFF50459b,
                0xFFa057a3
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(5, 5, 4));
    }
}
