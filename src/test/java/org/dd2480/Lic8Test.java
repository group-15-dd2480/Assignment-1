package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic8Test {

    @Test
    void shouldThrowIllegalArgumentException_whenGivenInvalidInputs() {

        Main.points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1), new Point2D.Double(0, -1),
                new Point2D.Double(-1, 0) };
        Main.numPoints = Main.points.length;

        assertThrows(IllegalArgumentException.class, () -> Main.lic8(0, 1, 2),
                "should throw when A_PTS is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic8(1, 0, 2),
                "should throw when B_PTS is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic8(1, 1, 2),
                "should throw when A_PTS + B_PTS > (NUMPOINTS - 3)");
    }

    @Test
    void shouldReturnTrue_whenThereExistsASetThatCannotBeContained() {
        Main.points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 3), new Point2D.Double(0, 1),
                new Point2D.Double(0, 3), new Point2D.Double(0, 1), new Point2D.Double(0, 3),  new Point2D.Double(0, 2)};
        Main.numPoints = Main.points.length;

        assertEquals(true, Main.lic8(1, 1, 0),
                "the set (0, 1), (0, 1), (0, 2) should not be able to be contained");
    }

    @Test
    void shouldReturnFalse_whenThereIsNoSetThatCannotBeContained() {
        Main.points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 3), new Point2D.Double(0, 1),
                new Point2D.Double(0, 3), new Point2D.Double(0, 1) };
        Main.numPoints = Main.points.length;

        assertEquals(false, Main.lic8(1, 1, 0),
                "should false since the only set of points can be contained");

        Main.points = new Point2D[] { new Point2D.Double(0, 3), new Point2D.Double(0, 1), new Point2D.Double(0, 3), new Point2D.Double(0, 1),
                new Point2D.Double(0, 3), new Point2D.Double(0, 1) };
        Main.numPoints = Main.points.length;

        assertEquals(false, Main.lic8(1, 1, 0),
                "both of the valid sets should be able to be contained");
    }
}
