package org.dd2480;

import static org.dd2480.Main.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

public class AngleTest {
    @Test
    public void testCalculateAngleUndefined() {
        Point2D A = new Point2D.Double(0, 0);
        Point2D B = new Point2D.Double(0, 0); // Same as A
        Point2D C = new Point2D.Double(1, 1);

        // Expecting IllegalArgumentException for undefined angle
        assertThrows(IllegalArgumentException.class, () -> Main.calculateAngle(A, B, C));
    }
    @Test
    public void testCalculateAngleValid() {
        // Define points A, B, and C forming a right angle (90 degrees or π/2 radians)
        Point2D A = new Point2D.Double(0, 0);
        Point2D B = new Point2D.Double(1, 0);
        Point2D C = new Point2D.Double(0, 1);

        // Expected angle between vectors AB and BC (should be π/4 radians)
        double expectedAngle = PI / 4;

        // Call the method and get the result
        double result = Main.calculateAngle(A, B, C);

        // Assert that the result is close to the expected value
        assertEquals(expectedAngle, result, 1e-9);
    }


}
