package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class TriangeAreaTest {

    @Test
    void throwOnNullPoints() {
        Point2D[] points = { new Point2D.Double(0, 0), null, new Point2D.Double(2, 2) };
        assertThrows(IllegalArgumentException.class, () -> Main.triangleArea(points),
                "null points should throw an IllegalArgumentException");
    }

    @Test
    void throwOnNaNPoints() {
        Point2D[] points = { new Point2D.Double(0, 0), new Point2D.Double(1, 1), new Point2D.Double(2, Double.NaN) };
        assertThrows(IllegalArgumentException.class, () -> Main.triangleArea(points),
                "NaN points should throw an IllegalArgumentException");
    }

    @Test
    void throwOnInfinitePoints() {
        Point2D[] points = { new Point2D.Double(0, 0), new Point2D.Double(1, 1),
                new Point2D.Double(2, Double.POSITIVE_INFINITY) };
        assertThrows(IllegalArgumentException.class, () -> Main.triangleArea(points),
                "infinite points should throw an IllegalArgumentException");
    }

    @Test
    void throwIfLengthIsNotThree() {
        Point2D[] pointsShort = { new Point2D.Double(0, 0), new Point2D.Double(1, 1) };
        assertThrows(IllegalArgumentException.class, () -> Main.triangleArea(pointsShort),
                "less than 3 points should throw an IllegalArgumentException");

        Point2D[] pointsLong = { new Point2D.Double(0, 0), new Point2D.Double(1, 1), new Point2D.Double(2, 2),
                new Point2D.Double(3, 3) };
        assertThrows(IllegalArgumentException.class, () -> Main.triangleArea(pointsLong),
                "more than 3 points should throw an IllegalArgumentException");

        Point2D[] pointsThree = { new Point2D.Double(0, 0), new Point2D.Double(1, 1), new Point2D.Double(2, 2) };
        assertDoesNotThrow(() -> Main.triangleArea(pointsThree), "exactly 3 points should not throw an exception");
    }

    @Test
    void zeroAreaWhenPointsFormALine() {
        Point2D[] points = { new Point2D.Double(0, 0), new Point2D.Double(1, 1), new Point2D.Double(2, 2) };
        assertEquals(0, Main.triangleArea(points), "points forming a line should have an area of 0");
    }

    @Test
    void correctAreaWhenPointsFormATriangle() {
        {
            Point2D[] points = { new Point2D.Double(0, 0), new Point2D.Double(1, 0), new Point2D.Double(0, 1) };
            assertEquals(0.5, Main.triangleArea(points), "points forming a triangle should have the correct area");
        }

        {
            Point2D[] points = { new Point2D.Double(0, 0), new Point2D.Double(1, 0), new Point2D.Double(1, 1) };
            assertEquals(0.5, Main.triangleArea(points), "points forming a triangle should have the correct area");
        }
    }

    @Test
    void orderOfPointsShouldNotMatter() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(0, 1);
        Point2D c = new Point2D.Double(1, 0);

        double[] areas = {
                Main.triangleArea(new Point2D[] { a, b, c }),
                Main.triangleArea(new Point2D[] { a, c, b }),
                Main.triangleArea(new Point2D[] { b, a, c }),
                Main.triangleArea(new Point2D[] { b, c, a }),
                Main.triangleArea(new Point2D[] { c, a, b }),
                Main.triangleArea(new Point2D[] { c, b, a })
        };

        for (int i = 1; i < areas.length; i++)
            assertEquals(areas[0], areas[i], "the order of points should not matter");
    }
}
