package com.example.pixelsandroid.effects.implementation;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;
import com.example.pixelsandroid.algorithms.implementation.BayerPowerAlgorithm;
import com.example.pixelsandroid.effects.AbstractEffect;

public class PhotoPaper extends AbstractEffect {
    @Override
    public String name() {
        return "Photo Paper";
    }

    @Override
    public int[] palette() {
        return new int[]{
                0xFF211e20,
                0xFF555568,
                0xFFa0a08b,
                0xFFe9efec
        };
    }

    @Override
    public AbstractAlgorithm algorithm() {
        return new BayerPowerAlgorithm(palette());
    }
}
