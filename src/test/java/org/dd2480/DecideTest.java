package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class DecideTest {

    @Test
    void shouldThrowIllegalArgumentException_whenMoreThan100Points() {
        Point2D[] points = new Point2D[101];
        assertThrows(IllegalArgumentException.class, () -> Main.decide(points, null, null, null),
                "points count should not exceed 100");
    }

}
