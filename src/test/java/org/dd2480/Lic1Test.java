package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic1Test {

    @Test
    void shouldReturnFalse_whenNoPointsAreGiven() {
        Point2D[] points = new Point2D[] {};

        boolean result = Main.lic1(points, 0);
        assertEquals(false, result, "there needs to be at least three points to generate a true output");
    }

    @Test
    void shouldReturnFalse_whenOnePointIsGiven() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1) };

        boolean result = Main.lic1(points, 0);
        assertEquals(false, result, "there needs to be at least three points to generate a true output");
    }

    @Test
    void shouldReturnFalse_whenTwoPointsAreGiven() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2)};

        boolean result = Main.lic1(points, 0);
        assertEquals(false, result, "there needs to be at least three points to generate a true output");
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRadiusIsNegative() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2), new Point2D.Double(3, 3)};

        assertThrows(IllegalArgumentException.class, () -> Main.lic1(points, -1), "a negative radius circle cannot exist");
    }

    @Test
    void shouldReturnTrue_whenThreeConsecutivePointsCannotBeContained() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2), new Point2D.Double(3, 3)};

        boolean result = Main.lic1(points, 1);
        assertEquals(true, result, "a 1 radius circle cannot contain both (1, 1) and (3, 3)");
    }

    @Test
    void shouldReturnFalse_whenThreeNonConsecutivePointsCannotBeContained() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(1, 1.5), new Point2D.Double(1, 2), new Point2D.Double(1, 2.5), new Point2D.Double(1, 3)};

        boolean result = Main.lic1(points, 1);
        assertEquals(false, result, "the circle should be able to contain all sets of three consecutive points");
    }
}
