package org.dd2480;

import java.awt.geom.Point2D;

import org.dd2480.Main.Op;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class DecideTest {

    @Test
    void shouldThrowIllegalArgumentException_whenMoreThan100Points() {
        Point2D[] points = new Point2D[101];
        assertThrows(IllegalArgumentException.class, () -> Main.decide(points, null, null, null),
                "points count should not exceed 100");
    }

    @Test
    void shouldReturnTrue_whenParametersAreCorrect() {
        Point2D[] points = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(0, 2),
            new Point2D.Double(2, 2),
            new Point2D.Double(4, 2),
            new Point2D.Double(2, 4),
            new Point2D.Double(0, 4),};

        boolean[] puv = new boolean[]{true, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

        Op[][] lcm = new Op[][]{
            {Op.ANDD, Op.ANDD, Op.ANDD, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED}
        };

        Parameters parameters = new Parameters(
                Math.PI / 4,
                2.,
                3.,
                2.,
                3.,
                2.,
                4.,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                3,
                2,
                1,
                2.
        );

        assertEquals(true, Main.decide(points, parameters, lcm, puv));
    }

    @Test
    void shouldReturnFalse_whenParametersAreIncorrect() {
        Point2D[] points = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(0, 2),
            new Point2D.Double(2, 2),
            new Point2D.Double(4, 2),
            new Point2D.Double(2, 4),
            new Point2D.Double(0, 4),};

        boolean[] puv = new boolean[]{true, true, true, true, true, true, false, false, false, false, false, false, false, false, false};

        Op[][] lcm = new Op[][]{
            {Op.ANDD, Op.ANDD, Op.ANDD, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED},
            {Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED, Op.NOTUSED, Op.ANDD, Op.ANDD, Op.ORR, Op.NOTUSED}
        };

        Parameters parameters = new Parameters(
                Math.PI / 4,
                2.,
                3.,
                2.,
                3.,
                2.,
                4.,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                3,
                2,
                1,
                2.
        );

        assertEquals(false, Main.decide(points, parameters, lcm, puv));
    }

}
