package com.example.pixelsandroid.algorithms.implementation;

import static com.example.pixelsandroid.utils.Utils.calculateNewDimensions;
import static com.example.pixelsandroid.utils.Utils.clip;
import static com.example.pixelsandroid.utils.Utils.findClosestColor;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;

public class BayerDitheringAlgorithm extends AbstractAlgorithm {

    private final int[][] bayerMatrix4x4 = {
            {0, 12, 3, 15},
            {8, 4, 11, 7},
            {2, 14, 1, 13},
            {10, 6, 9, 5}
    };

    private Color levels;

    public BayerDitheringAlgorithm(int[] palette, Color levels) {
        super(palette);
        this.levels = levels;
    }

    @Override
    public Bitmap process(Bitmap imageData, float value, int[] sizes) {
        int[] size = calculateNewDimensions(imageData.getWidth(), imageData.getHeight(), sizes);

        int width = size[0];
        int height = size[1];

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageData, width, height, true);

        Bitmap newBitmap = scaledBitmap.copy(scaledBitmap.getConfig(), true);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = scaledBitmap.getPixel(x, y);

                int r = (pixel >> 16) & 0xFF; // R
                int g = (pixel >> 8) & 0xFF;  // G
                int b = pixel & 0xFF;         // B

                int bayer = bayerMatrix4x4[y % 4][x % 4];

                int levelR = (int) Math.floor((r * (this.levels.red() + 1) + (bayer - 8) * 16 * value) / 256);
                int levelG = (int) Math.floor((g * (this.levels.green() + 1) + (bayer - 8) * 16 * value) / 256);
                int levelB = (int) Math.floor((b * (this.levels.blue() + 1) + (bayer - 8) * 16 * value) / 256);

                Color fakeColor = Color.valueOf(
                        this.levels.red() > 1 ? clip((int) (levelR * (255 / (this.levels.red() - 1)))) : r,
                        this.levels.green() > 1 ? clip((int) (levelG * (255 / (this.levels.green() - 1)))) : g,
                        this.levels.blue() > 1 ? clip((int) (levelB * (255 / (this.levels.blue() - 1)))) : b);

                int newColor = findClosestColor(fakeColor, getPalette());
                newBitmap.setPixel(x, y, newColor);
            }
        }
        return newBitmap;
    }

}
