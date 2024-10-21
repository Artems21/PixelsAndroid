package com.example.pixelsandroid.utils;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Utils {
    public static int[] calculateNewDimensions(int originalWidth, int originalHeight, int[] maxSize) {
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        int maxWidth = maxSize[0];
        int maxHeight = maxSize[1];

        if (originalWidth > maxWidth || originalHeight > maxHeight) {
            if (originalWidth > originalHeight) {
                newWidth = maxWidth;
                newHeight = (newWidth * originalHeight) / originalWidth;
            } else {
                newHeight = maxHeight;
                newWidth = (newHeight * originalWidth) / originalHeight;
            }
        }

        newWidth = roundToNearestMultipleOf4(newWidth);
        newHeight = roundToNearestMultipleOf4(newHeight);

        return new int[]{newWidth, newHeight};
    }

    public static int roundToNearestMultipleOf4(int value) {
        return (value + 3) & ~3;
    }

    public static int findClosestColor(int targetColor, int[] palette) {
        int closestColor = palette[0];
        double minDistance = Double.MAX_VALUE;

        for (int color : palette) {
            double distance = calculateDistance(targetColor, color);
            if (distance < minDistance) {
                minDistance = distance;
                closestColor = color;
            }
        }
        return closestColor;
    }

    public static int findClosestColor(int r, int g, int b, int[] palette) {
        int closestColor = palette[0];
        double minDistance = Double.MAX_VALUE;

        for (int color : palette) {
            double distance = calculateDistance(r ,g, b, color);
            if (distance < minDistance) {
                minDistance = distance;
                closestColor = color;
            }
        }
        return closestColor;
    }

    private static double calculateDistance(int r1, int g1, int b1, int color2) {
        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        return Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2));
    }

    public static int findClosestColor(Color targetColor, int[] palette) {
        int closestColor = palette[0];
        double minDistance = Double.MAX_VALUE;

        for (int color : palette) {
            double distance = calculateDistance(targetColor, color);
            if (distance < minDistance) {
                minDistance = distance;
                closestColor = color;
            }
        }
        return closestColor;
    }

    private static double calculateDistance(Color color1, int color2) {
        int r1 = (int) color1.red();
        int g1 = (int) color1.green();
        int b1 = (int) color1.blue();

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        return Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2));
    }

    private static double calculateDistance(int color1, int color2) {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        return Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2));
    }

    public static void sortColorsByBrightness(ArrayList<Integer> colors) {
        colors.sort((color1, color2) -> {
            float brightness1 = calculateBrightness(color1);
            float brightness2 = calculateBrightness(color2);

            return Float.compare(brightness1, brightness2);
        });
    }

    private static float calculateBrightness(int color) {
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;
        return (0.299f * red + 0.587f * green + 0.114f * blue);
    }


    public static int clip(int v) {
        return (v < 0) ? 0 : (Math.min(v, 255));
    }
}
