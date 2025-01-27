package org.dd2480;

import java.awt.geom.Point2D;

public class Main {

    /**
     * NUMPOINTS: The number of planar data points.
     */
    public static int numPoints;
    /**
     * POINTS: Array containing the coordinates of data points.
     */
    public static Point2D[] points;

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
                double distance = pointDistance(points[j], points[i]);
                if (distance > 2 * radius)
                    return false;
            }
        return true;
    }

    /**
     * Checks the distance between two 2D points
     *
     * @param point1 a 2D point
     * @param point2 a 2D point
     * @return distance between point1 and point2
     * @throws IllegalArgumentException if any point is null
     */
    public static double pointDistance(Point2D point1, Point2D point2) {
        if (point1 == null || point2 == null) {
            throw new IllegalArgumentException("No points can be null.");
        }
        double xDiff = point2.getX() - point1.getX();
        double yDiff = point2.getY() - point1.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    /**
     * 
     * Function that corresponds to LIC 0
     * 
     * @param points  an array of points
     * @param LENGTH1 length of the containing circle
     * @return true if there exists at least two points where the distance between
     *         them is greater than LENGTH1
     * @throws IllegalArgumentException if length is negative
     */
    public static boolean lic0(Point2D[] points, double LENGTH1) {
        if (LENGTH1 < 0)
            throw new IllegalArgumentException("Length must be >= 0");
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (pointDistance(points[j], points[i]) > LENGTH1)
                    return true;
            }
        }
        return false;
    }

    /**
     * 
     * Function that corresponds to LIC 1
     * 
     * @param radius of the containing circle
     * @return true iff there exists a set of three consecutive points that cannot
     *         be contained within (or on) a circle of the specified radius, false
     *         otherwise
     * @throws IllegalArgumentException if radius is negative
     */
    public static boolean lic1(double radius) {
        if (radius < 0)
            throw new IllegalArgumentException("Radius must be >= 0");
        for (int i = 0; i < Main.numPoints - 2; i++)
            if (!circleContainmentCheck(new Point2D[] { Main.points[i], Main.points[i + 1], Main.points[i + 2] },
                    radius))
                return true;
        return false;
    }

    /**
     * 
     * Function that corresponds to LIC 4
     * 
     * @param points array of points
     * @param qPts   number of consecutive points
     * @param quads  number of quadrants
     * @return {@code true} iff there exists at least one set of {@code qPts}
     *         consecutive
     *         points that lie in more than {@code quads} quadrants, {@code false}
     *         otherwise
     */
    public static boolean lic4(Point2D[] points, int qPts, int quads) {
        if (qPts < 2 || qPts > points.length)
            throw new IllegalArgumentException("expects 2 <= qPts <= number of points");
        if (quads < 1 || quads > 3)
            throw new IllegalArgumentException("expects 1 <= quads <= 3");

        for (int i = 0; i < points.length - qPts + 1; i++) {
            boolean[] quadrants = new boolean[4];
            for (int j = 0; j < qPts; j++) {
                int quad = quadrant(points[i + j]);
                quadrants[quad - 1] = true;
            }

            int count = 0;
            for (var quad : quadrants)
                if (quad)
                    count++;

            if (count > quads)
                return true;
        }

        return false;
    }

    /**
     * Calculates the area of a triangle given its three points.
     * 
     * @param points an array of points representing the vertices of the triangle
     * @return the area of the triangle
     * @throws IllegalArgumentException
     *                                  <ul>
     *                                  <li>If the number of points is not 3</li>
     *                                  <li>If any point is null</li>
     *                                  <li>If any point has coordinates that are
     *                                  NaN or infinite</li>
     *                                  </ul>
     */
    public static double triangleArea(Point2D[] points) {
        if (points.length != 3)
            throw new IllegalArgumentException("Exactly 3 points are required to calculate the area of a triangle");

        for (var point : points) {
            if (point == null)
                throw new IllegalArgumentException("Null points are not allowed");
            if (!Double.isFinite(point.getX()) || !Double.isFinite(point.getY()))
                throw new IllegalArgumentException("Non-finite points are not allowed");
        }

        double a = points[0].getX() * (points[1].getY() - points[2].getY());
        double b = points[1].getX() * (points[2].getY() - points[0].getY());
        double c = points[2].getX() * (points[0].getY() - points[1].getY());

        return Math.abs((a + b + c) / 2);
    }

    /**
     * 
     * Calculates which of the four quadrants a point lies in.
     * When a point lies on an axis, it is considered to be in the quadrant with the
     * lowest number.
     * 
     * @param point the point to check
     * @return Which quadrant the point lies in (1, 2, 3, or 4)q
     * @throws IllegalArgumentException
     *                                  <ul>
     *                                  <li>If the point is null</li>
     *                                  <li>If the point has coordinates that are
     *                                  NaN or infinite</li>
     *                                  </ul>
     */
    public static int quadrant(Point2D point) {
        if (point == null)
            throw new IllegalArgumentException("Null points are not allowed");
        if (!Double.isFinite(point.getX()) || !Double.isFinite(point.getY()))
            throw new IllegalArgumentException("Non-finite points are not allowed");

        if (point.getY() >= 0) {
            if (point.getX() >= 0)
                return 1;
            else
                return 2;
        } else {
            if (point.getX() <= 0)
                return 3;
            else
                return 4;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}