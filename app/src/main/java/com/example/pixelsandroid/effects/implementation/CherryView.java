package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class CherryView extends AbstractEffect {
    @Override
    public String name() {
        return "Cherry View";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFFFEEFF,
                0xFFFF0033,
                0xFFFF88FF,
                0xFF880000,
                0xFF000000,
                0xFFFFFFFF,
                0xFFFF00FF,
                0xFF880088,
                0xFF8800FF,
                0xFF5500EE
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(4, 4,4));
    }
}
