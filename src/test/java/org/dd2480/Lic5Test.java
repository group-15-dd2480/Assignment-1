package org.dd2480;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class Lic5Test {

    @Test
    void shouldThrowIllegalArgumentException_whenLessThan2PointsGiven() {
        Point2D[] points = new Point2D[]{new Point2D.Double(0,0)};
        assertThrows(IllegalArgumentException.class, () -> {
            Main.lic5(points);
        },"Less than 2 points given");
    }
    @Test
    void shouldReturnTrue_whenConsecutiveXDrop(){
        Point2D[] points = new Point2D[] {
                new Point2D.Double(1, 0),
                new Point2D.Double(0, 1)
        };
        assertTrue(Main.lic5(points),"Should return true for at least one consecutive x drop");
    }
    @Test
    void shouldReturnFalse_whenNoConsecutiveXDrop(){
        Point2D[] points = new Point2D[] {
                new Point2D.Double(1, 0),
                new Point2D.Double(1, 1)
        };
        assertFalse(Main.lic5(points),"Should return false for no consecutive x drop");
    }
}