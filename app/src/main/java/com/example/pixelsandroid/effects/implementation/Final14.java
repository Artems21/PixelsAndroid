package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Final14 extends AbstractEffect {
    @Override
    public String name() {
        return "Final 14";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFffffff,
                0xFFffdd84,
                0xFFec955b,
                0xFF883a56,
                0xFFd95959,
                0xFFfc83c7,
                0xFFaa5ec3,
                0xFF443c7e,
                0xFF566ed0,
                0xFF7bb1eb,
                0xFF98eb77,
                0xFF3eca6b,
                0xFF49868b,
                0xFF10101d
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
