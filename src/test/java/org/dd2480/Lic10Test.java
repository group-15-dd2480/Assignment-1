package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic10Test {

    @Test
    void shouldThrowIllegalArgumentException_whenGivenInvalidArguments() {
        Point2D point = new Point2D.Double(1, 1);
        Point2D[] points = new Point2D[] {
                point,
                point,
                point,
                point,
                point,
        };

        assertThrows(IllegalArgumentException.class, () -> Main.lic10(points, 0, 1, 1),
                "should throw when ePts is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic10(points, 1, 0, 1),
                "should throw when fPts is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic10(points, 2, 1, 1),
                "should throw when ePts + fPts > points - 3");
        assertThrows(IllegalArgumentException.class, () -> Main.lic10(points, 1, 1, -1),
                "should throw when area1 is < 0");
    }

    @Test
    void shouldReturnFalse_whenThereAreLessThanFivePoints() {
        Point2D[] points = new Point2D[] {};
        assertEquals(false, Main.lic10(points, 1, 1, 1),
                "should return false when number of points is zero");

        points = new Point2D[] { new Point2D.Double(0, 0) };
        assertEquals(false, Main.lic10(points, 1, 1, 1),
                "should return false when number of points is one");

        points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0) };
        assertEquals(false, Main.lic10(points, 1, 1, 1),
                "should return false when number of points is two");

        points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(0, 0) };
        assertEquals(false, Main.lic10(points, 1, 1, 1),
                "should return false when number of points is three");

        points = new Point2D[] { new Point2D.Double(0, 0), new Point2D.Double(0, 0), new Point2D.Double(0, 0),
                new Point2D.Double(0, 0) };
        assertEquals(false, Main.lic10(points, 1, 1, 1),
                "should return false when number of points is four");
    }

    @Test
    void shouldReturnTrue_whenTriangeExists() {
        Point2D[] points = new Point2D[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 0),
        };

        assertEquals(true, Main.lic10(points, 1, 1, 0.3),
                "should return true when a triangle of sufficient area exists");
    }

    @Test
    void shouldReturnFalse_whenTriangeDoesntExists() {
        Point2D[] points = new Point2D[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 0),
        };

        assertEquals(false, Main.lic10(points, 1, 1, 1),
                "should return false when a no triangle of sufficient area exists");
    }

}
