package org.dd2480;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class ValidAngleTest {

    @Test
    void shouldThrowIllegalArgumentException_whenNullPointGiven() {
        // Test with null point1
        assertThrows(IllegalArgumentException.class, () -> {
            Main.checkValidAngle(null, new Point2D.Double(1, 1), new Point2D.Double(2, 2), Math.PI / 10);
        });
    }
    @Test
    void shouldReturnTrue_whenGivenValidPoints() {
        // Test with valid points
        Point2D point1 = new Point2D.Double(0, 0);
        Point2D point2 = new Point2D.Double(1, 0);
        Point2D point3 = new Point2D.Double(1, 1);

        assertTrue(Main.checkValidAngle(point1, point2, point3, Math.PI / 10)); // Valid points and epsilon
    }
    @Test
    void shouldReturnFalse_whenGivenInvalidAngle() {
        // Test where angle is within epsilon range of pi
        Point2D point1 = new Point2D.Double(0, 0);
        Point2D point2 = new Point2D.Double(1, 0);
        Point2D point3 = new Point2D.Double(1, 1);

        assertFalse(Main.checkValidAngle(point1, point2, point3, Math.PI)); // Should be false since angle is close to Pi
    }

}