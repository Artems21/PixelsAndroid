package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class MorningCoffee extends AbstractEffect {
    @Override
    public String name() {
        return "Morning Coffee";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF385074,
                0xFF4670a2,
                0xFF70819d,
                0xFF86a2b8,
                0xFFc0d1de,
                0xFFb2a08a,
                0xFFd9b48a,
                0xFFfeeb9f,
                0xFFffebbc,
                0xFFf0d1a5,
                0xFF968981,
                0xFF7f7574,
                0xFF484850,
                0xFF313848,
                0xFF1c283e,
                0xFF0b1321
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
