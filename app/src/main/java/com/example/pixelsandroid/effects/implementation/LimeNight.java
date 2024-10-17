package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class LimeNight extends AbstractEffect {
    @Override
    public String name() {
        return "Lime Night";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF372134,
                0xFF474476,
                0xFF4888b7,
                0xFF6dbcb9,
                0xFF8cefb6
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
