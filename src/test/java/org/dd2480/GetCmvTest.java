package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
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

    @Test
    void shouldReturnTrueArray_whenGivenParametersThatYieldTrueValues() {

        Point2D[] points = new Point2D[] {
            new Point.Double(-1, 0),
            new Point.Double(0, -1),
            new Point.Double(1, -1),
            new Point.Double(0, 0),
            new Point.Double(0, 0),
            new Point.Double(0, 0),
            new Point.Double(0, 1),
            new Point.Double(0, 2),
            new Point.Double(0, 2),
            new Point.Double(0, 4),
            new Point.Double(0, 3),
            new Point.Double(0, 6),
        };

        boolean[] cmv = Main.getCmv(
            points, 
            2, 
            1, 
            Math.PI/4, 
            0.2, 
            4, 
            3, 
            0.5, 
            3, 
            1, 
            1, 
            1, 
            1, 
            1, 
            1, 
            1, 
            1, 
            10, 
            10, 
            10
        );

        for(int i = 0; i < cmv.length; i++)
            assertTrue(cmv[i], String.format("LIC %2d gives wrong output", i));

    }

    @Test
    void shouldReturnFalseArray_whenGivenParametersThatYieldFalseValues() {

        Point2D[] points = new Point2D[] {
            new Point.Double(-1, 0),
            new Point.Double(0, -1),
        };

        boolean[] cmv = Main.getCmv(
            points, 
            2, 
            1, 
            Math.PI/4, 
            0.2, 
            2, 
            3, 
            0.5, 
            3, 
            1, 
            1, 
            1, 
            1, 
            1, 
            1, 
            1, 
            1, 
            10, 
            10, 
            10
        );

        for(int i = 0; i < cmv.length; i++)
            assertFalse(cmv[i], String.format("LIC %2d gives wrong output", i));

    }
}
