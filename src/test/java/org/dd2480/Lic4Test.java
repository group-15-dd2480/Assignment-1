package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic4Test {

    @Test
    void shouldReturnTrue_whenPointsLieInEnoughQuadrants() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1) };
        assertEquals(true, Main.lic4(points, 2, 1),
                "the points (1, 1) and (-1, -1) lie in two different quadrants");

        points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1), new Point2D.Double(1, -1) };
        assertEquals(true, Main.lic4(points, 3, 2),
                "the points (1, 1), (-1, -1) and (1, -1) lie in three different quadrants");

    }

    @Test
    void shouldReturnFalse_whenPointsDoNotLieInEnoughQuadrants() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1) };
        assertEquals(false, Main.lic4(points, 2, 2),
                "the points (1, 1) and (-1, -1) lie in less than 3 different quadrants");

        points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1), new Point2D.Double(1, -1) };
        assertEquals(false, Main.lic4(points, 3, 3),
                "the points (1, 1), (-1, -1) and (1, -1) lie in less than 4 different quadrants");
    }

    @Test
    void throwsWhenQptsIsInvalidRange() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1) };
        assertThrows(IllegalArgumentException.class, () -> Main.lic4(points, 3, 1),
                "should throw an exception when qPts is > points.length");
        assertThrows(IllegalArgumentException.class, () -> Main.lic4(points, 1, 1),
                "should throw an exception when qPts is < 2");
    }

    @Test
    void throwsWhenQuadsIsInvalidRange() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1) };
        assertThrows(IllegalArgumentException.class, () -> Main.lic4(points, 2, 4),
                "should throw an exception when quads is > 3");
        assertThrows(IllegalArgumentException.class, () -> Main.lic4(points, 2, 0),
                "should throw an exception when quads is < 1");
    }

}
