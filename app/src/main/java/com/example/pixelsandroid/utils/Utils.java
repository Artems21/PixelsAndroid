package com.example.pixelsandroid.utils;

import android.graphics.Color;

public class Utils {
    public static int[] calculateNewDimensions(int originalWidth, int originalHeight, int maxSize) {
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        if (originalWidth > maxSize || originalHeight > maxSize) {
            if (originalWidth > originalHeight) {
                newWidth = maxSize;
                newHeight = (newWidth * originalHeight) / originalWidth;
            } else {
                newHeight = maxSize;
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

    public static int clip(int v) {
        return (v < 0) ? 0 : (Math.min(v, 255));
    }
}
