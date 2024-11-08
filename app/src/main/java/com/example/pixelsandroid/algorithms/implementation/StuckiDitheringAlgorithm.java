package com.example.pixelsandroid.algorithms.implementation;

import static androidx.core.math.MathUtils.clamp;
import static com.example.pixelsandroid.utils.Utils.calculateNewDimensions;
import static com.example.pixelsandroid.utils.Utils.clip;
import static com.example.pixelsandroid.utils.Utils.findClosestColor;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.pixelsandroid.algorithms.AbstractAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class StuckiDitheringAlgorithm extends AbstractAlgorithm {
    public StuckiDitheringAlgorithm(int[] palette) {
        super(palette);
    }

    @Override
    public Bitmap process(Bitmap imageData, float value, int[] sizes) {
        int[] size = calculateNewDimensions(imageData.getWidth(), imageData.getHeight(), sizes);

        int width = size[0];
        int height = size[1];

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageData, width, height, true);
        Bitmap newBitmap = scaledBitmap.copy(scaledBitmap.getConfig(), true);

        value *= 0.7f;


        int[][] currentError = new int[width + 4][3]; // [R, G, B]
        int[][] nextError = new int[width + 4][3];
        int[][] nextNextError = new int[width + 4][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = scaledBitmap.getPixel(x, y);

                int r = (pixel >> 16) & 0xFF; // R
                int g = (pixel >> 8) & 0xFF;  // G
                int b = pixel & 0xFF;         // B

                int adjustedR = clip((int) (r + Math.floor((currentError[x + 2][0] * value) / 42)));
                int adjustedG = clip((int) (g + Math.floor((currentError[x + 2][1] * value) / 42)));
                int adjustedB = clip((int) (b + Math.floor((currentError[x + 2][2] * value) / 42)));

                int closestColor = findClosestColor(adjustedR, adjustedG, adjustedB, getPalette());
                newBitmap.setPixel(x, y, closestColor);

                int errorR = adjustedR - ((closestColor >> 16) & 0xff);
                int errorG = adjustedG - ((closestColor >> 8) & 0xff);
                int errorB = adjustedB - (closestColor & 0xff);

                currentError[x + 3][0] += 8 * errorR;
                currentError[x + 3][1] += 8 * errorG;
                currentError[x + 3][2] += 8 * errorB;

                currentError[x + 4][0] += 4 * errorR;
                currentError[x + 4][1] += 4 * errorG;
                currentError[x + 4][2] += 4 * errorB;

                nextError[x][0] += 2 * errorR;
                nextError[x][1] += 2 * errorG;
                nextError[x][2] += 2 * errorB;

                nextError[x + 1][0] += 4 * errorR;
                nextError[x + 1][1] += 4 * errorG;
                nextError[x + 1][2] += 4 * errorB;

                nextError[x + 2][0] += 8 * errorR;
                nextError[x + 2][1] += 8 * errorG;
                nextError[x + 2][2] += 8 * errorB;

                nextError[x + 3][0] += 4 * errorR;
                nextError[x + 3][1] += 4 * errorG;
                nextError[x + 3][2] += 4 * errorB;

                nextError[x + 4][0] += 2 * errorR;
                nextError[x + 4][1] += 2 * errorG;
                nextError[x + 4][2] += 2 * errorB;

                nextNextError[x][0] += errorR;
                nextNextError[x][1] += errorG;
                nextNextError[x][2] += errorB;

                nextNextError[x + 1][0] += 2 * errorR;
                nextNextError[x + 1][1] += 2 * errorG;
                nextNextError[x + 1][2] += 2 * errorB;

                nextNextError[x + 2][0] += 4 * errorR;
                nextNextError[x + 2][1] += 4 * errorG;
                nextNextError[x + 2][2] += 4 * errorB;

                nextNextError[x + 3][0] += 2 * errorR;
                nextNextError[x + 3][1] += 2 * errorG;
                nextNextError[x + 3][2] += 2 * errorB;

                nextNextError[x + 4][0] += errorR;
                nextNextError[x + 4][1] += errorG;
                nextNextError[x + 4][2] += errorB;
            }

            for (int i = 0; i < currentError.length; i++) {
                currentError[i] = nextError[i];
                nextError[i] = nextNextError[i];
                nextNextError[i] = new int[]{0, 0, 0};
            }
        }

        return newBitmap;
    }

    private int clip(int value) {
        return Math.max(0, Math.min(255, value));
    }



}
