package org.dd2480;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class XDropTest {

    @Test
    void shouldThrowIllegalArgumentException_whenNullPointGiven() {
        assertThrows(IllegalArgumentException.class, () -> Main.checkForXDrop(null, null),"No points can be null");
    }
    @Test
    void shouldReturnFalse_whenNoXDrop(){
        Point2D point1 = new Point2D.Double(0,0);
        Point2D point2 = new Point2D.Double(0,0);

        assertFalse(Main.checkForXDrop(point1,point2),"X1 <= X2");
    }
    @Test
    void shouldReturnTrue_whenXDrop(){
        Point2D point1 = new Point2D.Double(0,0);
        Point2D point2 = new Point2D.Double(-1,0);

        assertTrue(Main.checkForXDrop(point1,point2),"X1 > X2");
    }
}