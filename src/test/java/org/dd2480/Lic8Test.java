package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic8Test {

    @Test
    void shouldThrowIllegalArgumentException_whenGivenInvalidInputs() {

        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1), new Point2D.Double(0, -1),
                new Point2D.Double(-1, 0) };

        assertThrows(IllegalArgumentException.class, () -> Main.lic8(points, 0, 1, 2),
                "should throw when A_PTS is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic8(points, 1, 0, 2),
                "should throw when B_PTS is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic8(points, 1, 1, 2),
                "should throw when A_PTS + B_PTS > (NUMPOINTS - 3)");
    }

    @Test
    void shouldReturnTrue_whenThereExistsASetThatCannotBeContained() {
        Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 3), new Point2D.Double(0, 1),
                new Point2D.Double(0, 3), new Point2D.Double(0, 1), new Point2D.Double(0, 3),  new Point2D.Double(0, 2)};

        assertEquals(true, Main.lic8(points, 1, 1, 0),
                "the set (0, 1), (0, 1), (0, 2) should not be able to be contained");
    }

    @Test
    void shouldReturnFalse_whenThereIsNoSetThatCannotBeContained() {
        Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 3), new Point2D.Double(0, 1),
                new Point2D.Double(0, 3), new Point2D.Double(0, 1) };

        assertEquals(false, Main.lic8(points, 1, 1, 0),
                "should false since the only set of points can be contained");

        points = new Point2D[] { new Point2D.Double(0, 3), new Point2D.Double(0, 1), new Point2D.Double(0, 3), new Point2D.Double(0, 1),
                new Point2D.Double(0, 3), new Point2D.Double(0, 1) };

        assertEquals(false, Main.lic8(points, 1, 1, 0),
                "both of the valid sets should be able to be contained");
    }
}
