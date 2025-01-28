package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic14Test {

    @Test
    void shouldThrowIllegalArgumentException_whenArgumentsAreInvalidRange() {
        Point2D point = new Point2D.Double(1, 1);
        Point2D[] points = new Point2D[] { point, point, point, point, point };

        assertThrows(IllegalArgumentException.class, () -> Main.lic14(points, -1, 0, 0, 0),
                "should throw when ePts is < 0");
        assertThrows(IllegalArgumentException.class, () -> Main.lic14(points, 0, -1, 0, 0),
                "should throw when fPts is < 0");
        assertThrows(IllegalArgumentException.class, () -> Main.lic14(points, 0, 0, -1, 0),
                "should throw when area1 is < 0");
        assertThrows(IllegalArgumentException.class, () -> Main.lic14(points, 0, 0, 0, -1),
                "should throw when area2 is < 0");

        assertThrows(IllegalArgumentException.class, () -> Main.lic14(points, 1, 2, 1, 1),
                "should throw when ePts + fPts > points - 3");
    }

    @Test
    void shouldReturnFalse_whenNoPointsAreInArea() {
        Point2D[] points = new Point2D[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(1, 0),
                new Point2D.Double(1, 1),
                new Point2D.Double(2, 2)
        };

        assertEquals(false, Main.lic14(points, 1, 1, 1, 1), "should return false when no points are in area");
    }

    @Test
    void shouldReturnTrue_whenPointsAreInArea() {
        Point2D[] points = new Point2D[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(1, 1),
                new Point2D.Double(1, 1),
                new Point2D.Double(1, 0),
                new Point2D.Double(10, 10),
        };

        assertEquals(true, Main.lic14(points, 1, 1, 4, 0.9), "should return true when points are in area");
    }

}
