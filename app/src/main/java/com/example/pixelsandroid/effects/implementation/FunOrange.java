package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class FunOrange extends AbstractEffect {
    @Override
    public String name() {
        return "Fun Orange";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF2b0f54,
                0xFFab1f65,
                0xFFff4f69,
                0xFFfff7f8,
                0xFFff8142,
                0xFFffda45,
                0xFF3368dc,
                0xFF49e7ec
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(4, 3 ,3));
    }
}
