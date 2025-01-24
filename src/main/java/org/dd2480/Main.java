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
    public static final double PI = 3.1415926535;
    public static double EPSILON;    // Deviation from PI in LICs 2, 9
    public static int C_PTS;         // Number of intervals in LIC 9
    public static int D_PTS;         // Number of intervals in LIC 9

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
            return -1;
        }
        // Calculate magnitudes of the edges
        double magAB = getVectorMagnitude(A,B);
        double magBC = getVectorMagnitude(B,C);
        // Check if any magnitude is zero
        if (magAB == 0 || magBC == 0){
            return -1;
        }
        // Calculate & normalize the vectors AB & BC
        Point2D AB = new Point2D.Double((B.getX()-A.getX())/magAB,(B.getY()-A.getY())/magAB);
        Point2D BC = new Point2D.Double((C.getX()-B.getX())/magBC,(C.getY()-B.getY())/magBC);
        // Calculate the angle
        double dotProduct = AB.getX()*BC.getX() + AB.getY()*BC.getY();
        // Handle the cases where dot product is not within [-1,1]
        if (dotProduct > 1){
            dotProduct = 1;
        } else if (dotProduct < -1){
            dotProduct = -1;
        }
        return Math.acos(dotProduct);
    }
    public static boolean checkValidAngle(Point2D A,Point2D B,Point2D C){
        double angle = calculateAngle(A,B,C);
        if (angle != -1){
            return (angle < PI - EPSILON) || (angle > PI + EPSILON);
        }
        return false;
    }
    public static boolean lic2(Point2D[] points){
        for (int i=0; i<points.length-2; i++){
            if (checkValidAngle(points[i],points[i+1],points[i+2])){
                return true;
            }
        }
        return false;
    }
    public static boolean lic9(Point2D[] points){
        for (int i=0; i<points.length-2; i++){
            Point2D A = points[i];
            Point2D B = points[i+1];
            Point2D C = points[i+2];
            if (checkValidAngle(A,B,C) && getVectorMagnitude(A,B) == C_PTS && getVectorMagnitude(B,C) == D_PTS){
                return true;
            }
        }
        return false;
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


    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}