package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.StuckiDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class Impulse extends AbstractEffect {
    @Override
    public String name() {
        return "Impulse";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF9f3c91,
                0xFF452060,
                0xFF291546,
                0xFF070c1d,
                0xFF3d1430,
                0xFF632240,
                0xFF913a52,
                0xFFbc5960,
                0xFFe2b570,
                0xFFeee98a,
                0xFFb7d974,
                0xFF5da75d,
                0xFF39755c,
                0xFF285454,
                0xFF1f394d,
                0xFF191b3f,
                0xFF192d50,
                0xFF286080,
                0xFF3fa0a4,
                0xFF86e0ce,
                0xFFe3f5f1,
                0xFF81afb5,
                0xFF446374,
                0xFF32495f,
                0xFF182e41
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new StuckiDitheringAlgorithm(palette());
    }
}
