package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic3Test {

    @Test
    void shouldThrowIllegalArgumentException_whenAreaNegative() {
        Point2D[] points = new Point2D[] {};

        assertThrows(IllegalArgumentException.class, () -> Main.lic3(points, -1),
                "should throw when area1 < 0");
    }

    @Test
    void shouldReturnTrue_whenAreaLargeEnough() {
        Point2D[] points = new Point2D[] { new Point2D.Double(3, 0), new Point2D.Double(0, 3),
                new Point2D.Double(0, 0) };

        assertEquals(true, Main.lic3(points, 1),
                "should return true when area of triangle is greater than area1");
    }

    @Test
    void shouldReturnFalse_whenAreaTooSmall() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 0), new Point2D.Double(0, 1),
                new Point2D.Double(0, 0) };

        assertEquals(false, Main.lic3(points, 1),
                "should return false when area of triangle is less than area1");
    }

}
