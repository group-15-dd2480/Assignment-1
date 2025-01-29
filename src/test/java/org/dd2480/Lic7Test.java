package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class Lic7Test {

        @Test
        void shouldThrowIllegalArgumentException_whenGivenInvalidInputs() {

                Point2D[] points = new Point2D[] { new Point2D.Double(1, 1), new Point2D.Double(1, 2),
                                new Point2D.Double(2, 1) };

                assertThrows(IllegalArgumentException.class, () -> Main.lic7(points, 0, 1),
                                "should throw when K_PTS is < 1");
                assertThrows(IllegalArgumentException.class, () -> Main.lic7(points, 2, 1),
                                "should throw when K_PTS is > NUMPOINTS - 2");
        }

        @Test
        void shouldReturnTrue_whenThereExistsAValidSet() {
                Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 2),
                                new Point2D.Double(0, 3),
                                new Point2D.Double(0, 3), };

                assertEquals(true, Main.lic7(points, 2, 1),
                                "there exists a valid set");
        }

        @Test
        void shouldReturnFalse_whenThereIsNoValidSet() {
                Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 2),
                                new Point2D.Double(0, 1),
                                new Point2D.Double(0, 2), new Point2D.Double(1, 1) };

                assertEquals(false, Main.lic7(points, 3, 2),
                                "should be false since no set can be obtained");
        }

        @Test
        void shouldReturnFalse_whenSetIsConsecutive() {
                Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 3),
                                new Point2D.Double(0, 2), };

                assertEquals(false, Main.lic7(points, 1, 1.1),
                                "should be false since set is not separated by K_PTS");
        }

        @Test
        void shouldReturnFalse_whenNotEnoughPoints() {
                Point2D[] points = new Point2D[] { new Point2D.Double(0, 1), new Point2D.Double(0, 2), };

                assertEquals(false, Main.lic7(points, 1, 2),
                                "should be false since NUMPOINTS < 3");
        }
}
