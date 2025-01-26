package org.dd2480;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class Lic2Test {

    @Test
    void shouldThrowIllegalArgumentException_whenEpsilonNegative() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2), new Point2D.Double(3, 3)};
        // Test when epsilon negative
        assertThrows(IllegalArgumentException.class, () -> {
            Main.lic2(points,-0.1); // Epsilon should be in the range [0, Math.PI)
        },"Invalid input for epsilon.");
    }
    @Test
    void shouldThrowIllegalArgumentException_whenEpsilonGreaterThanPi() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2), new Point2D.Double(3, 3)};
        // Test with epsilon greater than Math.PI
        assertThrows(IllegalArgumentException.class, () -> {
            Main.lic2(points,Math.PI + 0.1); // Epsilon should be in the range [0, Math.PI)
        },"Invalid input for epsilon.");
    }
    @Test
    void shouldReturnTrue_whenValidInput() {
        // Test with a valid epsilon value & correct input
        // Assuming numPoints and points are set up correctly
        Point2D[] points = {
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 0),
                new Point2D.Double(1, 1)
        };
        assertTrue(Main.lic2(points,Math.PI / 10)); // Valid epsilon
    }
    @Test
    void shouldReturnFalse_whenLessThan3PointsGiven(){
        // Test with less than 3 points
        Point2D[] points = {
                new Point2D.Double(0,0),
                new Point2D.Double(1,1)
        };
        assertFalse(Main.lic2(points,Math.PI/10));
    }
    @Test
    void shouldReturnFalse_whenInvalidInput() {
        // Test with a valid epsilon value & incorrect input
        // Assuming numPoints and points are set up correctly
        Point2D[] points = {
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 0),
                new Point2D.Double(1, 1)
        };
        assertFalse(Main.lic2(points,Math.PI/2)); // Valid epsilon
    }
}