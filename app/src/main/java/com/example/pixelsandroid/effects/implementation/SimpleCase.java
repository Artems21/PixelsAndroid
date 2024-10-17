package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class SimpleCase extends AbstractEffect {
    @Override
    public String name() {
        return "Simple Case";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFFffffff,
                0xFFffd588,
                0xFF72cb48,
                0xFFb2d4d4,
                0xFFc45544,
                0xFFcc9155,
                0xFF0a8a71,
                0xFF66aaf7,
                0xFF7f3355,
                0xFF000000,
                0xFF114c77,
                0xFF8891aa
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
