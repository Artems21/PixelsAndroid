package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class FruitFive extends AbstractEffect {
    @Override
    public String name() {
        return "Fruit Five";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFFb63dff,
                0xFFea5d15,
                0xFF10a4e3,
                0xFFffffff
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(3, 3, 3));
    }
}
