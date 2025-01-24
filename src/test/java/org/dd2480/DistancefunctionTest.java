package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class DistancefunctionTest {

    @Test
    void distanceIsCorrectGivenTwoPoints() {
        Main.points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(2, 2) };
        assertEquals(Math.sqrt(2), Main.calculateDistanceBetween2Points(Main.points[0], Main.points[1]));
    }

}
