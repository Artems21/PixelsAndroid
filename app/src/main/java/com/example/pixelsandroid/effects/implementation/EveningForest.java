package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class EveningForest extends AbstractEffect {
    @Override
    public String name() {
        return "Evening Forest";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF0f0012,
                0xFF004f3f,
                0xFF009e4a,
                0xFF1cba33,
                0xFFFFFFEA,
                0xFFffed87,
                0xFF330033,
                0xFFb3122d,
                0xFFcc2929,
                0xFFe6653a,
                //0xFFffbb5c,
                0xFF330066,
                0xFF1a0099,
                0xFF1433cc,
                0xFF30d2f2,
                0xFF4cffb4
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(5, 4 ,4));
    }
}
