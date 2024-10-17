package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Rock16 extends AbstractEffect {
    @Override
    public String name() {
        return "Rock 16";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF140c00,
                0xFF690804,
                0xFFde2c2c,
                0xFFfa5555,
                0xFF382400,
                0xFFa1858d,
                0xFFd0b2ba,
                0xFFfacaca,
                0xFF002000,
                0xFF405544,
                0xFF617561,
                0xFF99b295,
                0xFF0c3044,
                0xFF556d89,
                0xFF7595b6,
                0xFFdeeeff
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
