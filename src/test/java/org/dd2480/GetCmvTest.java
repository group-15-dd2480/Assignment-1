package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class GetCmvTest {

    @Test
    void shouldThrowIllegalArgumentException_whenGivenInvalidInputs() {
        Point2D[] fewPoints = new Point2D[] {};
        assertThrows(IllegalArgumentException.class,
                () -> Main.getCmv(fewPoints, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                "should throw when too few points");

        Point2D[] manyPoints = new Point2D[101];
        assertThrows(IllegalArgumentException.class,
                () -> Main.getCmv(manyPoints, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                "should throw when too many points");
    }
}
