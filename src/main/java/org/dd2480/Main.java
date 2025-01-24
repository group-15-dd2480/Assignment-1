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

    public static final double PI = 3.1415926535;
    /**
     * EPSILON: Deviation from PI in LICs 2,9
     */
    public static double EPSILON = 0.01;

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
     * 
     * Checks the distance between two 2D points
     * 
     * @param point1 a 2D point
     * @param point2 a 2D point
     * @return distance between point1 and point2
     */
    public static double pointDistance(Point2D point1, Point2D point2) {
        double xDiff = point2.getX() - point1.getX();
        double yDiff = point2.getY() - point1.getY();
        double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        return distance;
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
     * 
     * Function that corresponds to LIC 8
     * 
     * @param aPts   the number of consecutive points between the first and second
     *               point (A_PTS)
     * @param bPts   the number of consecutive points between the second and third
     *               point (B_PTS)
     * @param radius of the containing circle (RADIUS_1)
     * @return true iff there exists a valid set of points (separated by the
     *         specified number of consecutive points) that cannot be contained by
     *         the circle of the specified radius, otherwise false
     * @throws IllegalArgumentException
     *                                  <ul>
     *                                  <li>If {@code aPts} < 1</li>
     *                                  <li>If {@code bPts} < 1</li>
     *                                  <li>If {@code aPts} + {@code bPts} >
     *                                  {@code Main.numPoints} - 3</li>
     *                                  </ul>
     */
    public static boolean lic8(int aPts, int bPts, double radius) {
        if (aPts < 1)
            throw new IllegalArgumentException("A_PTS must be >= 1");
        if (bPts < 1)
            throw new IllegalArgumentException("B_PTS must be >= 1");
        if (aPts + bPts > Main.numPoints - 3)
            throw new IllegalArgumentException("A_PTS + B_PTS must be <= NUMPOINTS - 3");

        for (int i = 0; i < Main.numPoints - 2 - aPts - bPts; i++)
            if (!circleContainmentCheck(
                    new Point2D[] { Main.points[i], Main.points[i + 1 + aPts], Main.points[i + 2 + aPts + bPts] },
                    radius))
                return true;
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
     * Calculates the angle between three points A, B, and C, where B is the vertex of the angle.
     * The function checks if the points are valid and calculates the angle using the dot product formula.
     * If any of the points are invalid or the vectors are degenerate, the method will return -1.
     *
     * @param A the first point (Point2D) representing one side of the angle
     * @param B the second point (Point2D) representing the vertex of the angle
     * @param C the third point (Point2D) representing the other side of the angle
     * @return the angle in radians between the vectors AB and BC, or -1 if the calculation is not possible
     *         (e.g., when any point is the same, or when the vectors have zero magnitude)
     * @throws IllegalArgumentException if any of the points is null
     */
    public static double calculateAngle(Point2D A,Point2D B,Point2D C) {
        // Check if angle is undefined or not
        if (B.equals(A) || B.equals(C)){
            throw new IllegalArgumentException("Points cannot be the same. Angle is undefined.");
        }
        // Calculate magnitudes of the edges
        double magAB = getVectorMagnitude(A,B);
        double magBC = getVectorMagnitude(B,C);
        // Check if any magnitude is zero
        if (magAB == 0 || magBC == 0){
            throw new IllegalArgumentException("Magnitude of vectors cannot be zero.");
        }
        // Calculate & normalize the vectors AB & BC
        Point2D BA = new Point2D.Double(A.getX()-B.getX(),A.getY()-B.getY());
        Point2D BC = new Point2D.Double(C.getX()-B.getX(),C.getY()-B.getY());
        // Calculate the angle
        double dotProduct = BA.getX()*BC.getX() + BA.getY()*BC.getY();
        double cosAngle = dotProduct / (magAB * magBC);
        // Handle the cases where dot product is not within [-1,1]
        if (cosAngle > 1){
            cosAngle = 1;
        } else if (cosAngle < -1){
            cosAngle = -1;
        }
        return Math.acos(cosAngle);
    }
    /**
     * Calculates the magnitude (length) of the vector formed by two points A and B.
     * The magnitude is computed as the Euclidean distance between the two points.
     *
     * @param A the first point (Point2D) representing one end of the vector
     * @param B the second point (Point2D) representing the other end of the vector
     * @return the magnitude of the vector formed by points A and B
     * @throws IllegalArgumentException if either of the points is null
     */
    public static double getVectorMagnitude(Point2D A,Point2D B){
        double x = A.getX()-B.getX();
        double y = A.getY()-B.getY();
        return Math.sqrt(x*x + y*y);
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