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
    public Bitmap process(Bitmap imageData, float value) {
        int[] size = calculateNewDimensions(imageData.getWidth(), imageData.getHeight(), 256);

        int width = size[0];
        int height = size[1];

        Bitmap scaledBitmap = imageData;

        // Scaled image if needed
        if (imageData.getHeight() != height || imageData.getWidth() != width)
            scaledBitmap = Bitmap.createScaledBitmap(imageData, width, height, true);

        value = (float) (value * 0.7);

        List<Color> currentError = new ArrayList<>(width + 4);
        List<Color> nextError = new ArrayList<>(width + 4);
        List<Color> nextNextError = new ArrayList<>(width + 4);

        // Initialize lists with black color
        for (int i = 0; i < width + 4; i++) {
            currentError.add(Color.valueOf(0, 0, 0));
            nextError.add(Color.valueOf(0, 0, 0));
            nextNextError.add(Color.valueOf(0, 0, 0));
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = scaledBitmap.getPixel(x, y);

                int r = (pixel >> 16) & 0xFF; // R
                int g = (pixel >> 8) & 0xFF;  // G
                int b = pixel & 0xFF;         // B

                Color color = Color.valueOf(
                        clip((int) (r + Math.floor((currentError.get(x + 2).red() * value) / 42))),
                        clip((int) (g + Math.floor((currentError.get(x + 2).green() * value) / 42))),
                        clip((int) (b + Math.floor((currentError.get(x + 2).blue() * value) / 42)))
                );

                int closestColor = findClosestColor(color, getPalette());

                float closestR = (closestColor >> 16) & 0xFF; // R
                float closestG = (closestColor >> 8) & 0xFF;  // G
                float closestB = closestColor & 0xFF;         // B

                scaledBitmap.setPixel(x, y, closestColor);

                // Calculate error
                Color error = Color.valueOf(
                        color.red() - closestR,
                        color.green() - closestG,
                        color.blue() - closestB
                );

                currentError.set(x + 3, Color.valueOf(
                        clamp(currentError.get(x + 3).red() + 8 * error.red()),
                        clamp(currentError.get(x + 3).green() + 8 * error.green()),
                        clamp(currentError.get(x + 3).blue() + 8 * error.blue())
                )); // 1

                currentError.set(x + 4, Color.valueOf(
                        clamp(currentError.get(x + 4).red() + 4 * error.red()),
                        clamp(currentError.get(x + 4).green() + 4 * error.green()),
                        clamp(currentError.get(x + 4).blue() + 4 * error.blue())
                )); // 2

                // Update nextError
                nextError.set(x, Color.valueOf(
                        clamp(nextError.get(x).red() + 2 * error.red()),
                        clamp(nextError.get(x).green() + 2 * error.green()),
                        clamp(nextError.get(x).blue() + 2 * error.blue())
                )); // 3

                nextError.set(x + 1, Color.valueOf(
                        clamp(nextError.get(x + 1).red() + 4 * error.red()),
                        clamp(nextError.get(x + 1).green() + 4 * error.green()),
                        clamp(nextError.get(x + 1).blue() + 4 * error.blue())
                )); // 4

                nextError.set(x + 2, Color.valueOf(
                        clamp(nextError.get(x + 2).red() + 8 * error.red()),
                        clamp(nextError.get(x + 2).green() + 8 * error.green()),
                        clamp(nextError.get(x + 2).blue() + 8 * error.blue())
                )); // 5

                nextError.set(x + 3, Color.valueOf(
                        clamp(nextError.get(x + 3).red() + 4 * error.red()),
                        clamp(nextError.get(x + 3).green() + 4 * error.green()),
                        clamp(nextError.get(x + 3).blue() + 4 * error.blue())
                )); // 6

                nextError.set(x + 4, Color.valueOf(
                        clamp(nextError.get(x + 4).red() + 2 * error.red()),
                        clamp(nextError.get(x + 4).green() + 2 * error.green()),
                        clamp(nextError.get(x + 4).blue() + 2 * error.blue())
                )); // 7

                // Update nextNextError
                nextNextError.set(x, Color.valueOf(
                        clamp(nextNextError.get(x).red() + 1 * error.red()),
                        clamp(nextNextError.get(x).green() + 1 * error.green()),
                        clamp(nextNextError.get(x).blue() + 1 * error.blue())
                )); // 8

                nextNextError.set(x + 1, Color.valueOf(
                        clamp(nextNextError.get(x + 1).red() + 2 * error.red()),
                        clamp(nextNextError.get(x + 1).green() + 2 * error.green()),
                        clamp(nextNextError.get(x + 1).blue() + 2 * error.blue())
                )); // 9

                nextNextError.set(x + 2, Color.valueOf(
                        clamp(nextNextError.get(x + 2).red() + 4 * error.red()),
                        clamp(nextNextError.get(x + 2).green() + 4 * error.green()),
                        clamp(nextNextError.get(x + 2).blue() + 4 * error.blue())
                )); // 10

                nextNextError.set(x + 3, Color.valueOf(
                        clamp(nextNextError.get(x + 3).red() + 2 * error.red()),
                        clamp(nextNextError.get(x + 3).green() + 2 * error.green()),
                        clamp(nextNextError.get(x + 3).blue() + 2 * error.blue())
                )); // 11

                nextNextError.set(x + 4, Color.valueOf(
                        clamp(nextNextError.get(x + 4).red() + 1 * error.red()),
                        clamp(nextNextError.get(x + 4).green() + 1 * error.green()),
                        clamp(nextNextError.get(x + 4).blue() + 1 * error.blue())
                )); // 12
            }

            // Shift errors
            for (int i = 0; i < currentError.size(); i++) {
                currentError.set(i, nextError.get(i));
                nextError.set(i, nextNextError.get(i));
                nextNextError.set(i, Color.valueOf(0, 0, 0));
            }
        }
        return scaledBitmap;
    }

    private float clamp(float value) {
        return Math.max(0, Math.min(255, value));
    }



}
