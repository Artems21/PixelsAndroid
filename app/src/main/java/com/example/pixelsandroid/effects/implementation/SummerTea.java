package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class SummerTea extends AbstractEffect {
    @Override
    public String name() {
        return "Summer Tea";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF0d2b45,
                0xFF203c56,
                0xFF544e68,
                0xFF8d697a,
                0xFFd08159,
                0xFFffaa5e,
                0xFFffd4a3,
                0xFFffecd6
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
