package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class ClassicCGA extends AbstractEffect {
    @Override
    public String name() {
        return "Classic CGA";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFFff55ff,
                0xFF55ffff,
                0xFFffffff
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(4,3,4));
    }
}
