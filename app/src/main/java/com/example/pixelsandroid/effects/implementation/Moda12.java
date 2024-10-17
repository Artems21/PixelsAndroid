package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Moda12 extends AbstractEffect {
    @Override
    public String name() {
        return "Moda 12";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF323b2f,
                0xFF424d3c,
                0xFF626e60,
                0xFF778476,
                0xFFa0a896,
                0xFFb7bb9f,
                0xFFdadab8,
                0xFFe0e4c7,
                0xFFe9f6e3,
                0xFFf9fff0,
                0xFF293425,
                0xFF8f9a8b
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
