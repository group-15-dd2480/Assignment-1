package org.dd2480;

import java.awt.geom.Point2D;

public class Main {

    /**
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
                double distance = points[j].distance(points[i]);
                if (distance > 2 * radius)
                    return false;
            }
        return true;
    }

    /**
     * 
     * Function that corresponds to LIC 0
     * 
     * @param points an array of points
     * @param length length to be compared with
     * @return true if there exists at least two points where the distance between
     *         them is greater than length (LENGTH1)
     * @throws IllegalArgumentException if length is negative
     */
    public static boolean lic0(Point2D[] points, double length) {
        if (length < 0)
            throw new IllegalArgumentException("Length must be >= 0");
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].distance(points[i + 1]) > length)
                return true;
        }
        return false;
    }

    /**
     * Function that corresponds to LIC 1
     *
     * @param points array of points
     * @param radius of the containing circle
     * @return true iff there exists a set of three consecutive points that cannot
     *         be contained within (or on) a circle of the specified radius, false
     *         otherwise
     * @throws IllegalArgumentException if radius is negative
     */
    public static boolean lic1(Point2D[] points, double radius) {
        if (radius < 0)
            throw new IllegalArgumentException("Radius must be >= 0");
        for (int i = 0; i < points.length - 2; i++)
            if (!circleContainmentCheck(new Point2D[] { points[i], points[i + 1], points[i + 2] },
                    radius))
                return true;
        return false;
    }

    /**
     * Function that corresponds to LIC 2
     *
     * @param points  array of points
     * @param epsilon deviation from PI in LIC # 2 & 9
     * @return true if there exists at least one set of three consecutive data
     *         points
     *         which form an angle that is not in the range of epsilon from pi
     *         false if number of points are less than 3 or the condition is not met
     * @throws IllegalArgumentException if epsilon is not in the range: [0,pi)
     */
    public static boolean lic2(Point2D[] points, double epsilon) {
        if (epsilon < 0 || epsilon >= Math.PI) {
            throw new IllegalArgumentException("Invalid input for epsilon.");
        }
        if (points.length < 3) {
            return false;
        }
        for (int i = 0; i < points.length - 2; i++) {
            if (checkValidAngle(points[i], points[i + 1], points[i + 2], epsilon)) {
                return true;
            }
        }
        return false;
    }

    /**
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
     * Function that corresponds to LIC 5
     *
     * @param points array of points
     * @return true if there exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
     * that X[j] - X[i] < 0. (where i = j-1)
     * @throws IllegalArgumentException if number of points less than 2
     */
    public static boolean lic5(Point2D[] points){
        if (points.length < 2){
            throw new IllegalArgumentException("No of points less than 2");
        }
        for (int i=0; i < points.length-1;i++){
            if (checkForXDrop(points[i],points[i+1]))
                return true;
        }
        return false;
    }

    /**
     * 
     * Function that corresponds to LIC 6.
     * 
     * @param points array of points.
     * @param nPts   number of consecutive points.
     * @param dist   distance from the line connecting the first and last points.
     * @return {@code true} iff there exists at least one set of {@code nPts}
     *         consecutive points where at least one point is a distance greater
     *         than {@code dist} from the line connecting the first and last points,
     *         {@code false} otherwise.
     *         If the first and last points overlap, the distance is calculated as
     *         the distance from the first point.
     * @throws IllegalArgumentException
     *                                  <ul>
     *                                  <li>If {@code points} is null</li>
     *                                  <li>If {@code nPts} < 3 or {@code nPts} >
     *                                  {@code points.length}</li>
     *                                  <li>If {@code dist} < 0</li>
     *                                  <li>If any point in {@code points} is
     *                                  null</li>
     *                                  <li>If any point in {@code points} has
     *                                  coordinates that are NaN or infinite</li>
     *                                  </ul>
     */
    public static boolean lic6(Point2D[] points, int nPts, double dist) {
        if (points == null)
            throw new IllegalArgumentException("points cannot be null");
        if (points.length < 3)
            return false;
        if (nPts < 3 || nPts > points.length)
            throw new IllegalArgumentException("expects 3 <= nPts <= number of points");
        if (dist < 0)
            throw new IllegalArgumentException("dist must be >= 0");

        for (Point2D point : points) {
            if (point == null)
                throw new IllegalArgumentException("Null points are not allowed");
            if (!Double.isFinite(point.getX()) || !Double.isFinite(point.getY()))
                throw new IllegalArgumentException("Non-finite points are not allowed");
        }

        for (int i = 0; i < points.length - nPts + 1; i++) {
            Point2D first = points[i];
            Point2D last = points[i + nPts - 1];

            // If the first and the last point are the same, we care about the distance to
            // their center not the line.
            // Otherwise calculate the distance from the point to the line.
            if (first.equals(last)) {
                for (int j = i + 1; j < i + nPts - 1; j++) {
                    if (first.distance(points[j]) > dist) {
                        return true;
                    }
                }
            } else {
                for (int j = i + 1; j < i + nPts - 1; j++) {
                    Point2D point = points[j];
                    double numerator = Math.abs((last.getY() - first.getY()) * point.getX()
                            - (last.getX() - first.getX()) * point.getY() + last.getX() * first.getY()
                            - last.getY() * first.getX());
                    double denominator = Math
                            .sqrt(Math.pow(last.getY() - first.getY(), 2) + Math.pow(last.getX() - first.getX(), 2));
                    double distance = numerator / denominator;

                    if (distance > dist) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 
     * Function that corresponds to LIC 7
     * 
     * @param points the array of data points
     * @param kPts   the number of consecutive points between the first and second
     *               point (K_PTS)
     * @param length length to be compared with
     * @returntrue if there exists at least two points separated by kPTS (K_PTS)
     *             consecutive points where the distance between
     *             them is greater than length (LENGTH1)
     * @throws IllegalArgumentException
     * 
     */
    public static boolean lic7(Point2D[] points, int kPts, double length) {
        if (points.length < 3)
            return false;
        if (kPts < 1)
            throw new IllegalArgumentException("K_PTS must be >= 1");
        if (kPts > points.length - 2)
            throw new IllegalArgumentException("K_PTS must be <= NUMPOINTS - 2");

        for (int i = 0; i < points.length - kPts; i++) {
            if (points[i].distance(points[i + kPts]) > length) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * Function that corresponds to LIC 8
     *
     * @param points array of points
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
    public static boolean lic8(Point2D[] points, int aPts, int bPts, double radius) {
        if (points.length < 5)
            return false;
        if (aPts < 1)
            throw new IllegalArgumentException("A_PTS must be >= 1");
        if (bPts < 1)
            throw new IllegalArgumentException("B_PTS must be >= 1");
        if (aPts + bPts > points.length - 3)
            throw new IllegalArgumentException("A_PTS + B_PTS must be <= NUMPOINTS - 3");

        for (int i = 0; i < points.length - 2 - aPts - bPts; i++)
            if (!circleContainmentCheck(
                    new Point2D[] { points[i], points[i + 1 + aPts], points[i + 2 + aPts + bPts] },
                    radius))
                return true;
        return false;
    }

    /**
     * Function that corresponds to LIC 9
     *
     * @param points  array of points
     * @param cPts    the number of consecutive points between the first and second
     *                * point (C_PTS)
     * @param dPts    the number of consecutive points between the second and third
     *                * point (D_PTS)
     * @param epsilon deviation from pi in LIC # 2 & 9
     * @return true if there exists at least one set of three data points
     *         separated by exactly C_PTS and D_PTS consecutive intervening points,
     *         respectively,
     *         that form an angle such that: angle < (PI-EPSILON) or angle >
     *         (PI+EPSILON).
     *
     * @throws IllegalArgumentException when C_PTS is < 1 or D_PTS is < 1 or C_PTS +
     *                                  D_PTS > (NUMPOINTS - 3)
     */
    public static boolean lic9(Point2D[] points, int cPts, int dPts, double epsilon) {
        if (points.length < 5) {
            return false;
        }
        if (cPts < 1)
            throw new IllegalArgumentException("C_PTS must be >= 1");
        if (dPts < 1)
            throw new IllegalArgumentException("D_PTS must be >= 1");
        if (cPts + dPts > points.length - 3)
            throw new IllegalArgumentException("C_PTS + D_PTS must be <= NUMPOINTS - 3");

        for (int i = 0; i < points.length - 2 - cPts - dPts; i++) {
            if (checkValidAngle(points[i], points[i + 1 + cPts], points[i + 2 + cPts + dPts], epsilon))
                return true;
        }
        // If number of points less than 5 or the condition is not met
        return false;
    }
    /**
     *
     * Function that corresponds to LIC 11
     *
     * @param points array of points
     * @param gPts no of points separating the consecutive points
     * @return true if there exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
     * exactly gPts consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
     *         false if number of points are less than 3 or condition is not met
     * @throws IllegalArgumentException if gPts is invalid
     */
    public static boolean lic11(Point2D[] points, int gPts) {
        if (points.length < 3){
            return false;
        }
        if (gPts < 1)
            throw new IllegalArgumentException("gPts must be >= 1");
        if (gPts > points.length-2)
            throw new IllegalArgumentException("gPts must be <= NUMPOINTS - 2");
        for (int i = 0; i < points.length - 1 - gPts; i++) {
            if (checkForXDrop(points[i], points[i + 1 + gPts]))
                return true;
        }
        return false;
    }

    /**
     *
     * Function that corresponds to LIC 13
     *
     * @param points  array of points
     * @param aPts    the number of consecutive points between the first and second
     *                point (A_PTS)
     * @param bPts    the number of consecutive points between the second and third
     *                point (B_PTS)
     * @param radius1 of the circle that shall not contain the points (RADIUS_1)
     * @param radius2 of the circle that shall contain the points (RADIUS_2)
     * @return true iff there exists a valid set of points (separated by the
     *         specified number of consecutive points) that cannot be contained by
     *         the circle of the specified radius (RADIUS_1) AND there exists a
     *         valid set of points that can be contained by the circle of the other
     *         specified radius (RADIUS_2), otherwise false
     * @throws IllegalArgumentException if {@code radius2} < 0
     */
    public static boolean lic13(Point2D[] points, int aPts, int bPts, double radius1, double radius2) {
        if (radius2 < 0)
            throw new IllegalArgumentException("RADIUS_2 must be >= 0");

        if (points.length < 5)
            return false;

        // If there does not exist a set that cannot be contained by circle with
        // RADIUS_1, the condition is false.
        if (!lic8(points, aPts, bPts, radius1))
            return false;

        for (int i = 0; i < points.length - 2 - aPts - bPts; i++)
            if (circleContainmentCheck(
                    new Point2D[] { points[i], points[i + 1 + aPts], points[i + 2 + aPts + bPts] },
                    radius2))
                return true;
        return false;
    }

    /**
     * Function that corresponds to LIC 14
     * 
     * @param points array of points
     * @param ePts   the number of consecutive points between the first and second
     *               point
     * @param fPts   the number of consecutive points between the second and third
     *               point
     * @param area1  the area of the triangle formed by the three points must be
     *               greater than this
     * @param area2  the area of the triangle formed by the three points must be
     *               less than this
     * @return true iff there exists a valid set of points (separated by the
     *         specified number of consecutive points)
     *         that form a triangle with an area greater than {@code area1}, AND
     *         there exists a valid set of points that form a triangle with an area
     *         less than {@code area2}, otherwise false.
     * @throws IllegalArgumentException
     *                                  <ul>
     *                                  <li>If {@code area1} < 0</li>
     *                                  <li>If {@code area2} < 0</li>
     *                                  <li>If {@code ePts} < 0</li>
     *                                  <li>If {@code fPts} < 0</li>
     *                                  <li>If {@code ePts} + {@code fPts} >
     *                                  {@code points} - 3</li>
     *                                  </ul>
     */
    public static boolean lic14(Point2D[] points, int ePts, int fPts, double area1, double area2) {
        if (points.length < 5)
            return false;

        if (area1 < 0)
            throw new IllegalArgumentException("area1 must be >= 0");
        if (area2 < 0)
            throw new IllegalArgumentException("area2 must be >= 0");
        if (ePts < 0)
            throw new IllegalArgumentException("ePts must be >= 0");
        if (fPts < 0)
            throw new IllegalArgumentException("fPts must be >= 0");
        if (ePts + fPts > points.length - 3)
            throw new IllegalArgumentException("ePts + fPts must be <= points - 3");

        boolean condA = false;
        boolean condB = false;

        for (int i = 0; i < points.length - 2 - ePts - fPts; i++) {
            Point2D[] triangle = { points[i], points[i + 1 + ePts], points[i + 2 + ePts + fPts] };
            double triangleArea = triangleArea(triangle);

            if (triangleArea > area1)
                condA = true;
            if (triangleArea < area2)
                condB = true;

            if (condA && condB)
                return true;
        }

        return condA && condB;
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

    /**
     * Calculates the angle between three points A, B, and C, where B is the vertex
     * of the angle.
     * The function checks if the points are valid and calculates the angle using
     * the dot product formula.
     * If any of the points are invalid or the vectors are degenerate, the method
     * will throw IllegalArgumentException.
     *
     * @param pointA the first point (Point2D) representing one side of the angle
     * @param pointB the second point (Point2D) representing the vertex of the angle
     * @param pointC the third point (Point2D) representing the other side of the
     *               angle
     * @return the angle in radians between the vectors BA and BC
     * @throws IllegalArgumentException if any of the points is null, if pointA or
     *                                  pointC is the same as pointB or if the
     *                                  vectors have zero magnitude
     */
    public static double calculateAngle(Point2D pointA, Point2D pointB, Point2D pointC) {
        if (pointA == null || pointB == null || pointC == null) {
            throw new IllegalArgumentException("Points cannot be null.");
        }
        // Check if angle is undefined or not
        if (pointB.equals(pointA) || pointB.equals(pointC)) {
            throw new IllegalArgumentException("Points cannot be the same. Angle is undefined.");
        }
        // Calculate magnitudes of the edges
        double magAB = pointA.distance(pointB);
        double magBC = pointB.distance(pointC);
        // Check if any magnitude is zero
        if (magAB == 0 || magBC == 0) {
            throw new IllegalArgumentException("Magnitude of vectors cannot be zero.");
        }
        // Calculate & normalize the vectors AB & BC
        Point2D vectorBA = new Point2D.Double(pointA.getX() - pointB.getX(), pointA.getY() - pointB.getY());
        Point2D vectorBC = new Point2D.Double(pointC.getX() - pointB.getX(), pointC.getY() - pointB.getY());
        // Calculate the angle
        double dotProduct = vectorBA.getX() * vectorBC.getX() + vectorBA.getY() * vectorBC.getY();
        double cosAngle = dotProduct / (magAB * magBC);
        // Handle the cases where dot product is not within [-1,1]
        if (cosAngle > 1) {
            cosAngle = 1;
        } else if (cosAngle < -1) {
            cosAngle = -1;
        }
        return Math.acos(cosAngle);
    }

    /**
     * Checks whether the angle formed by three given points
     * is not within the specified epsilon of a straight angle (π radians).
     *
     * @param point1  The first point.
     * @param point2  The second point, which is the vertex of the angle.
     * @param point3  The third point.
     * @param epsilon The tolerance for the angle difference. Must be in the range
     *                [0, π).
     * @return True if the angle is valid (bigger than pi + epsilon or smaller than
     *         pi - epsilon), False otherwise.
     * @throws IllegalArgumentException If any of the points is null.
     */
    public static boolean checkValidAngle(Point2D point1, Point2D point2, Point2D point3, double epsilon) {
        if (point1 == null || point2 == null || point3 == null) {
            throw new IllegalArgumentException("Points cannot be null.");
        }
        double angle = calculateAngle(point1, point2, point3);
        return !(Math.abs(angle - Math.PI) <= epsilon);
    }
    /**
     *
     * @param point1 first point
     * @param point2 second point
     * @return true if x-coordinate of the second point is less than the first point
     * @throws IllegalArgumentException if any point is null
     */
    public static boolean checkForXDrop(Point2D point1, Point2D point2){
        if (point1 == null || point2 == null){
            throw new IllegalArgumentException("No points can be null");
        }
        return point2.getX()-point1.getX() < 0;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}