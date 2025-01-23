package org.dd2480;

import java.awt.geom.Point2D;

public class Main {

    /**
     * 
     * Checks if a circle can enclose all points. It does this by checking that the
     * distance between every point is less than the diameter of the circle.
     * 
     * @param points an array of points
     * @param radius of the containing circle
     * @return true if a circle with the given radius can contain the points, false
     *         otherwise
     * @throws IllegalArgumentException if radius is negative
     */
    public static boolean circleContainmentCheck(Point2D[] points, double radius) {
        if (radius < 0)
            throw new IllegalArgumentException("Radius must be >= 0");
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++) {
                // TODO: Change this distance calculation to the function proposed in Issue #4
                // For example: if (dist(point[i], point[j]) > 2 * radius) return false;
                double xDiff = points[j].getX() - points[i].getX();
                double yDiff = points[j].getY() - points[i].getY();
                double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                if (distance > 2 * radius)
                    return false;
            }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}