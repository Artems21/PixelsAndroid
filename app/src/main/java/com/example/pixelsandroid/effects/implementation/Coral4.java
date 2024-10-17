package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Coral4 extends AbstractEffect {
    @Override
    public String name() {
        return "Coral 4";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFF1D2B53,
                0xFF7E2553,
                0xFF008751,
                0xFFAB5236,
                0xFF5F574F,
                0xFFC2C3C7,
                0xFFFFF1E8,
                0xFFFF004D,
                0xFFFFA300,
                0xFFFFEC27,
                0xFF00E436,
                0xFF29ADFF,
                0xFF83769C,
                0xFFFF77A8,
                0xFFFFCCAA
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
