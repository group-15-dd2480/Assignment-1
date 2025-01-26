package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic13Test {

    @Test
    void shouldThrowIllegalArgumentException_whenGivenInvalidInputs() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1),
                new Point2D.Double(0, -1),
                new Point2D.Double(-1, 0), new Point2D.Double(1, 0) };

        assertThrows(IllegalArgumentException.class, () -> Main.lic13(points, 1, 1, 1, -1),
                "should throw when RADIUS_2 is < 0");
    }

    @Test
    void shouldReturnFalse_whenThereAreLessThanFivePoints() {
        Point2D[] points = new Point2D[] {};
        assertEquals(false, Main.lic13(points, 1, 1, 0, 0),
                "should return false when number of points is zero");

        points = new Point2D[] { new Point2D.Double(0, 0) };
        assertEquals(false, Main.lic13(points, 1, 1, 0, 0),
                "should return false when number of points is one");

        points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0) };
        assertEquals(false, Main.lic13(points, 1, 1, 0, 0),
                "should return false when number of points is two");

        points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(0, 0) };
        assertEquals(false, Main.lic13(points, 1, 1, 0, 0),
                "should return false when number of points is three");

        points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(0, 0),
                new Point2D.Double(0, 0) };
        assertEquals(false, Main.lic13(points, 1, 1, 0, 0),
                "should return false when number of points is four");
    }

    @Test
    void shouldReturnFalse_whenBothCirclesCanContainThePoints() {

        Point2D[] points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(0, 0) };

        assertEquals(false, Main.lic13(points, 1, 1, 0, 0),
                "should return false when both circles can contain the points");
    }

    @Test
    void shouldReturnTrue_whenOnlyTheSecondCircleCanContainThePoints() {

        Point2D[] points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(1, 0), new Point2D.Double(0, 0), new Point2D.Double(2, 0) };

        assertEquals(true, Main.lic13(points, 1, 1, 0, 1),
                "should return true when only the second circle can contain the points");
    }

    @Test
    void shouldReturnFalse_whenNoCircleCanContainThePoints() {

        Point2D[] points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(1, 0), new Point2D.Double(0, 0), new Point2D.Double(2, 0) };

        assertEquals(false, Main.lic13(points, 1, 1, 0, 0),
                "should return false when no circle can contain the points");
    }

    @Test
    void shouldReturnTrue_ifThereExistsSetsThatTogetherMeetsBothCriteria() {

        Point2D[] points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 100), new Point2D.Double(0, 0), new Point2D.Double(100, 0), new Point2D.Double(0, 0), new Point2D.Double(200, 0) };

        assertEquals(true, Main.lic13(points, 1, 1, 0, 1),
                "should return true if there is a set that cannot be contained within the first circle, and a set that can be contained within the second");
    }

}
