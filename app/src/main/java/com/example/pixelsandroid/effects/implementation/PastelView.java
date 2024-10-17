package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class PastelView extends AbstractEffect {
    @Override
    public String name() {
        return "Pastel View";
    }

    @Override
    public int[] palette() {
        return new int[] {
                0xFFf0dab1,
                0xFFe39aac,
                0xFFc45d9f,
                0xFF634b7d,
                0xFF6461c2,
                0xFF2ba9b4,
                0xFF93d4b5,
                0xFFf0f6e8,
                0xFF131b3d
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(3, 6, 2));
    }
}
