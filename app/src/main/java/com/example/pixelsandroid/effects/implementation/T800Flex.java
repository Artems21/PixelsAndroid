package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class T800Flex extends AbstractEffect {
    @Override
    public String name() {
        return "T800 Flex";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF000000,
                0xFFFFFFFF,
                0xFFFF0000
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
