package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class LoFiPixels extends AbstractEffect {
    @Override
    public String name() {
        return "Lo-Fi Pixels";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF28282e,
                0xFF6c5671,
                0xFFd9c8bf,
                0xFFf98284,
                0xFFb0a9e4,
                0xFFaccce4,
                0xFFb3e3da,
                0xFFfeaae4,
                0xFF87a889,
                0xFFe9f59d,
                0xFFffe6c6,
                0xFFdea38b,
                0xFFffc384,
                0xFFfff7a0,
                0xFFfff7e4
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(5,7,6 ));
    }
}
