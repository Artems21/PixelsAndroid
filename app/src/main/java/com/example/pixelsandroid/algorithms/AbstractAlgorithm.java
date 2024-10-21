package com.example.pixelsandroid.algorithms;

import android.graphics.Bitmap;

public abstract class AbstractAlgorithm {

    private int[] palette;

    public AbstractAlgorithm(int[] palette) {
        this.palette = palette;
    }

    protected int[] getPalette() {
        return palette;
    }

    public abstract Bitmap process(Bitmap imageData, float value, int[] sizes);
    public void updatePalette(int[] palette) {
        this.palette = palette;
    }

}
