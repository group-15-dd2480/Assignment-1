package org.dd2480;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class Lic9Test {

    @Test
    void shouldThrowIllegalArgumentException_whenGivenInvalidInputs() {
        Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(-1, -1), new Point2D.Double(0, -1),
                new Point2D.Double(-1, 0) };
        Point2D[] points2 = new Point2D[] {
                new Point2D.Double(1, 0),
                new Point2D.Double(1, 1),
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 1),
                new Point2D.Double(1,0)
        };

        assertThrows(IllegalArgumentException.class, () -> Main.lic9(points2, 0, 1, Math.PI/10),
                "Invalid input: C_PTS is < 1");
        assertThrows(IllegalArgumentException.class, () -> Main.lic9(points2, 1, 0, Math.PI/10),
                "Invalid input: D_PTS is < 1");
        assertFalse(Main.lic9(points, 1, 1, Math.PI/10),"Number of points < 5");
    }
    @Test
    void shouldReturnTrue_whenCorrectInputGiven(){
        Point2D[] points = new Point2D[] {
                new Point2D.Double(1, 0),
                new Point2D.Double(1, 1),
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 1),
                new Point2D.Double(1,0)
        };
        assertTrue(Main.lic9(points,1,1,Math.PI/2 - 0.01),"the condition satisfied for lic9");
    }
    @Test
    void shouldReturnFalse_whenFalseInputGiven(){
        Point2D[] points = new Point2D[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 0),
                new Point2D.Double(2, 0),
                new Point2D.Double(3, 0),
                new Point2D.Double(4,0)
        };
        assertFalse(Main.lic9(points,1,1,0.01),"the condition not satisfied for lic9");
    }

}