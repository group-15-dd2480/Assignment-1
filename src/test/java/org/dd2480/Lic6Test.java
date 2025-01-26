package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic6Test {

    @Test
    void shouldReturnFalse_whenPointsAreClose() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(1.1, 1.1),
                new Point2D.Double(1.2, 1.2) };
        assertEquals(false, Main.lic6(points, 3, 100), "should return false when points are close");
    }

    @Test
    void shouldReturnTrue_whenPointsAreFar() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(1, 10),
                new Point2D.Double(2, 2) };
        assertEquals(true, Main.lic6(points, 3, 1), "should return true when points are far");
    }

    @Test
    void shouldReturnTrue_whenPointsAreFarAndEndsOverlap() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(3, 3),
                new Point2D.Double(1, 1) };
        assertEquals(true, Main.lic6(points, 3, 1), "should return true when points are far and ends overlap");
    }

    @Test
    void throwsWhenNptsIsInvalidRange() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1) };
        assertThrows(IllegalArgumentException.class, () -> Main.lic6(points, 3, 1),
                "should throw an exception when qPts is > points.length");
        assertThrows(IllegalArgumentException.class, () -> Main.lic6(points, 2, 1),
                "should throw an exception when qPts is < 3");
    }

    @Test
    void throwsWhenPointsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Main.lic6(null, 3, 1),
                "should throw an exception when points is null");
    }

    @Test
    void throwsWhenPointsContainsNull() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), null };
        assertThrows(IllegalArgumentException.class, () -> Main.lic6(points, 3, 1),
                "should throw an exception when points contains null");
    }

    @Test
    void throwsWhenPointsContainInvalidCoordinates() {
        Point2D[] nanPoints = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(Double.NaN, 1) };
        assertThrows(IllegalArgumentException.class, () -> Main.lic6(nanPoints, 3, 1),
                "should throw an exception when points contain NaN");

        Point2D[] infPoints = new Point2D[] { new Point2D.Double(1, 1),
                new Point2D.Double(1, Double.POSITIVE_INFINITY) };
        assertThrows(IllegalArgumentException.class, () -> Main.lic6(infPoints, 3, 1),
                "should throw an exception when points contain Infinity");
    }

}
