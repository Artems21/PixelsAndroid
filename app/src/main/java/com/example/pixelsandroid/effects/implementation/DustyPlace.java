package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class DustyPlace extends AbstractEffect {
    @Override
    public String name() {
        return "Dusty Place";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFFFFFFF,
                0xFFE4E4E4,
                0xFF888888,
                0xFF222222,
                0xFFFFA7D1,
                0xFFE50000,
                0xFFE59500,
                0xFFA06A42,
                0xFFE5D900,
                0xFF94E044,
                0xFF02BE01,
                0xFF00D3DD,
                0xFF0083C7,
                0xFF0000EA,
                0xFFCF6EE4,
                0xFF820080
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(4, 3,3));
    }
}
