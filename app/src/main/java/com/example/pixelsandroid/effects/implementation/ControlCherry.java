package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class ControlCherry extends AbstractEffect {
    @Override
    public String name() {
        return "Control Cherry";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFEED5EE,
                0xFF54cea7,
                0xFF2ba4a6,
                0xFF0c6987,
                0xFF054b84,
                0xFF0d2147,
                0xFFffb0bf,
                0xFFff82bd,
                0xFFd74ac7,
                0xFFa825ba,
                0xFF682b9c,
                0xFF050010
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
