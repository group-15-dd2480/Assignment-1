package org.dd2480;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class Lic11Test {

    @Test
    void shouldThrowIllegalArgumentException_whenGivenInvalidG_PTS() {
        Point2D[] points = new Point2D[]{
                new Point2D.Double(0,0),
                new Point2D.Double(0,0),
                new Point2D.Double(0,0)
        };
        assertThrows(IllegalArgumentException.class, () -> {
            Main.lic11(points,0);
        },"Invalid gPts: less than 1");
        assertThrows(IllegalArgumentException.class, () -> {
            Main.lic11(points,2);
        },"Invalid gPts: bigger than NUM_POINTS - 2");
    }
    @Test
    void shouldReturnTrue_whenCorrectInput(){
        Point2D[] points = new Point2D[] {
                new Point2D.Double(1, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(0, 1)
        };
        assertTrue(Main.lic11(points,1),"Should return true for correct input");
    }
    @Test
    void shouldReturnFalse_whenFalseInput(){
        Point2D[] points = new Point2D[] {
                new Point2D.Double(1, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(1, 1)
        };
        assertFalse(Main.lic11(points,1),"Should return false for incorrect input");
    }
}