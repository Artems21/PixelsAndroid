package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Just16 extends AbstractEffect {
    @Override
    public String name() {
        return "Just 16";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF1a1c2c,
                0xFF5d275d,
                0xFFb13e53,
                0xFFef7d57,
                0xFFffcd75,
                0xFFa7f070,
                0xFF38b764,
                0xFF257179,
                0xFF29366f,
                0xFF3b5dc9,
                0xFF41a6f6,
                0xFF73eff7,
                0xFFf4f4f4,
                0xFF94b0c2,
                0xFF566c86,
                0xFF333c57
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(3,3,3));
    }
}
