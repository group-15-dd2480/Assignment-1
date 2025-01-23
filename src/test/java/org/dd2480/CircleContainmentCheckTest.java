package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class CircleContainmentCheckTest {

    @Test
    void shouldReturnTrue_whenNoPointsAreGiven() {
        boolean result = Main.circleContainmentCheck(new Point2D[0], 0);
        assertEquals(true, result, "any circle should be able to contain no points");
    }

    @Test
    void shouldReturnTrue_whenOnePointIsGivenAndCircleRadiusIsZero() {
        Point2D[] points = { new Point2D.Double(4.5, 3.5) };
        boolean result = Main.circleContainmentCheck(points, 0);
        assertEquals(true, result, "a zero radius circle should be able to contain one point");
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRadiusIsNegative() {
        Point2D[] points = { new Point2D.Double(4.5, 3.5) };
        assertThrows(IllegalArgumentException.class, () -> Main.circleContainmentCheck(points, -1), "a negative radius circle cannot exist");
    }

    @Test
    void shouldReturnTrue_whenTwoPointsAreTwoRadiusesApart() {
        Point2D[] points = { new Point2D.Double(4.5, 3.5), new Point2D.Double(0.5, 3.5) };
        boolean result = Main.circleContainmentCheck(points, 2);
        assertEquals(true, result, "two points at the very edge of a circle are still considered contained");
    }

    @Test
    void shouldReturnFalse_whenTwoPointsAreMoreThanTwoRadiusesApart() {
        Point2D[] points = { new Point2D.Double(5.5, 3.5), new Point2D.Double(0.5, 3.5) };
        boolean result = Main.circleContainmentCheck(points, 2);
        assertEquals(false, result, "two points separated by more than the diameter of the circle cannot be contained");
    }
}
