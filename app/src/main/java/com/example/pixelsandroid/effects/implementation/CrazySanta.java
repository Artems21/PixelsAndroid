package com.example.pixelsandroid.effects.implementation;

import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerDitheringAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class CrazySanta extends AbstractEffect {
    @Override
    public String name() {
        return "Crazy Santa";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF1a0d0f,
                0xFF331416,
                0xFF4d1717,
                0xFF73201d,
                0xFF8c2a23,
                0xFFa63229,
                0xFFcc4733,
                0xFFe56545,
                0xFFf28861,
                0xFFf2aa85,
                0xFFf2ceb6,
                0xFFffffff,
                0xFFd8f2c2,
                0xFFb8f291,
                0xFF98d977,
                0xFF7acc5c,
                0xFF5db347,
                0xFF50993d,
                0xFF3a802d,
                0xFF2f6624,
                0xFF234d1b,
                0xFF1f401a,
                0xFF1b3317,
                0xFF162613
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerDitheringAlgorithm(palette(), Color.valueOf(10,10,2));
    }
}
