package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic12Test {

    @Test
    void shouldThrowIllegalArgumentException_whenGivenInvalidInputs() {

        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(1, 2),
                new Point2D.Double(2, 1) };

        assertThrows(IllegalArgumentException.class, () -> Main.lic12(points, -1, -1, 3),
                "should throw when K_PTS is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic12(points, 1, -1, 3),
                "should throw when LENGTH1 is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic12(points, 1, 1, -1),
                "should throw when LENGTH2 is < 1");
    }

    
    @Test
    void shouldReturnFalse_whenNotEnoughPoints() {
        Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 2) };

        assertEquals(false, Main.lic12(points, 3, 2, 1),
                "should be false since not enough points");
    }

    @Test
    void shouldReturnTrue_whenThereExistsAValidSet() {
        Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 2),
                new Point2D.Double(0, 3),
                new Point2D.Double(0, 3), };

        assertEquals(true, Main.lic12(points, 1, 1, 2),
                "should be true since there exists a valid set");
    }

    @Test
    void shouldReturnFalse_whenThereIsNoValidSet() {
        Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 2),
                new Point2D.Double(0, 2),
                new Point2D.Double(0, 1) };

        assertEquals(false, Main.lic12(points, 1, 2, 1),
                "should be false since no set can be obtained");
    }

}
