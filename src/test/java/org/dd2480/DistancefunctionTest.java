package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class DistancefunctionTest {

    @Test
    void distanceIsCorrectGivenTwoPoints() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2) };
        assertEquals(Math.sqrt(2), Main.pointDistance(points[0], points[1]));
    }
    @Test
    void shouldThrowIllegalArgumentException_whenAnyPointNull() {
        // Expecting IllegalArgumentException for undefined angle
        assertThrows(IllegalArgumentException.class, () -> Main.pointDistance(null, null));
    }

}
