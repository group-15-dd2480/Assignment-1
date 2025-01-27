package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic0Test {

    @Test
    void shouldReturnTrue_whendistanceIsLongerThanLength() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2) };
        double length = 1;
        assertEquals(true, Main.lic0(points, length));
    }

    @Test
    void shouldReturnFalse_whenOnePointIsGiven() {
        Point2D[] points  = new Point2D[] { new Point2D.Double(1, 1) };

        boolean result = Main.lic0(points, 1);
        assertEquals(false, result, "there needs to be at least two points to generate a true output");
    }

    @Test
    void shouldReturnFalse_whenPointsAreNotConsecutive() {
        Point2D[] points  = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(1.4, 1.4), new Point2D.Double(1.9, 1.9) };

        boolean result = Main.lic0(points, 1);
        assertEquals(false, result, "The points need to be consecutive");
    }

    @Test
    void shouldReturnFalse_whendistanceIsShorterThanLength() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2) };
        double length = 2;
        assertEquals(false, Main.lic0(points, length));
    }

    @Test
    void shouldThrowIllegalArgumentException_whenAnyPointIsNull() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2) };
        assertThrows(IllegalArgumentException.class, () -> Main.lic0(points, -1));
    }

}
