package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class QuadrantTest {

    @Test
    void handleUnambiguousPoints() {
        Point2D one = new Point2D.Double(1, 1);
        assertEquals(1, Main.quadrant(one), "point (1, 1) should be in quadrant 1");

        Point2D two = new Point2D.Double(-1, 1);
        assertEquals(2, Main.quadrant(two), "point (-1, 1) should be in quadrant 2");

        Point2D three = new Point2D.Double(-1, -1);
        assertEquals(3, Main.quadrant(three), "point (-1, -1) should be in quadrant 3");

        Point2D four = new Point2D.Double(1, -1);
        assertEquals(4, Main.quadrant(four), "point (1, -1) should be in quadrant 4");
    }

    @Test
    void handlePointsOnAxes() {
        Point2D origin = new Point2D.Double(0, 0);
        assertEquals(1, Main.quadrant(origin), "point (0, 0) should be in quadrant 1");

        Point2D negX = new Point2D.Double(-1, 0);
        assertEquals(2, Main.quadrant(negX), "point (-1, 0) should be in quadrant 2");

        Point2D negY = new Point2D.Double(0, -1);
        assertEquals(3, Main.quadrant(negY), "point (0, -1) should be in quadrant 3");

        Point2D x = new Point2D.Double(1, 0);
        assertEquals(1, Main.quadrant(x), "point (1, 0) should be in quadrant 1");
    }

    @Test
    void throwWhenPointIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Main.quadrant(null), "point cannot be null");
    }

    @Test
    void throwWhenPointIsNonFinite() {
        Point2D nanPoint = new Point2D.Double(Double.NaN, 1);
        assertThrows(IllegalArgumentException.class, () -> Main.quadrant(nanPoint), "point cannot be non-finite");

        Point2D infPoint = new Point2D.Double(1, Double.POSITIVE_INFINITY);
        assertThrows(IllegalArgumentException.class, () -> Main.quadrant(infPoint), "point cannot be non-finite");
    }

}
