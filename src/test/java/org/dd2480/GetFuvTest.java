package org.dd2480;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetFuvTest {

    @Test
    void shouldThrowIllegalArgumentException_whenSuppliedWithParametersOfIncorrectDimensions() {

        boolean[][] pumRowTooShort = new boolean[14][15];
        boolean[][] pumRowTooLong = new boolean[16][15];
        boolean[][] pumColumnTooShort = new boolean[15][14];
        boolean[][] pumColumnTooLong = new boolean[15][16];
        boolean[][] pumCorrect = new boolean[15][15];

        boolean[] puvTooShort = new boolean[14];
        boolean[] puvTooLong = new boolean[16];
        boolean[] puvCorrect = new boolean[15];

        assertThrows(IllegalArgumentException.class, () -> Main.getFuv(pumRowTooShort, puvCorrect),
                "too small row count in PUM should not be accepted");
        assertThrows(IllegalArgumentException.class, () -> Main.getFuv(pumRowTooLong, puvCorrect),
                "too big row count in PUM should not be accepted");
        assertThrows(IllegalArgumentException.class, () -> Main.getFuv(pumColumnTooShort, puvCorrect),
                "too small column count in PUM should not be accepted");
        assertThrows(IllegalArgumentException.class, () -> Main.getFuv(pumColumnTooLong, puvCorrect),
                "too big column count in PUM should not be accepted");

        assertThrows(IllegalArgumentException.class, () -> Main.getFuv(pumCorrect, puvTooShort),
                "too short PUV should not be accepted");
        assertThrows(IllegalArgumentException.class, () -> Main.getFuv(pumCorrect, puvTooLong),
                "too long PUV should not be accepted");
    }

    @Test
    void shouldReturnCorrectArray_whenGivenCorrectParameters() {

        // Smaller constructions are used to make it more human readable

        boolean[][] subPum = {
                { true, true, true, true },
                { true, false, true, false },
                { true, false, true, true },
                { true, true, true, false },
        };

        boolean[] subPuv = { true, true, false, true };

        boolean[] subFuv = { true, false, true, false };

        // Incorporating the constructions into the actual sized matrices/arrays

        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < pum.length; i++)
            for (int j = 0; j < pum[0].length; j++)
                pum[i][j] = true;
        for (int i = 0; i < subPum.length; i++)
            for (int j = 0; j < subPum[0].length; j++)
                pum[i][j] = subPum[i][j];

        boolean[] puv = new boolean[15];
        for (int i = 0; i < puv.length; i++)
            puv[i] = false;
        for (int i = 0; i < subPuv.length; i++)
            puv[i] = subPuv[i];

        boolean[] fuv = new boolean[15];
        for (int i = 0; i < fuv.length; i++)
            fuv[i] = true;
        for (int i = 0; i < subFuv.length; i++)
            fuv[i] = subFuv[i];

        // Checks if the calculated FUV is different from the true FUV
        boolean[] funcFuv = Main.getFuv(pum, puv);
        boolean equalValues = true;
        for (int i = 0; i < fuv.length; i++)
            if (funcFuv[i] != fuv[i])
                equalValues = false;

        assertEquals(true, equalValues, "incorrect values were produced in the PUM");
    }

    @Test
    void shouldReturnFalseArray_whenGivenAllFalseParameters() {

        // Smaller constructions are used to make it more human readable

        boolean[][] subPum = {
                { false, false, false, false },
                { false, false, false, false },
                { false, false, false, false },
                { false, false, false, false },
        };

        boolean[] subPuv = { true, true, true, true }; // Has to be true for the PUM to be inspected

        boolean[] subFuv = { false, false, false, false };

        // Incorporating the constructions into the actual sized matrices/arrays

        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < pum.length; i++)
            for (int j = 0; j < pum[0].length; j++)
                pum[i][j] = true;
        for (int i = 0; i < subPum.length; i++)
            for (int j = 0; j < subPum[0].length; j++)
                pum[i][j] = subPum[i][j];

        boolean[] puv = new boolean[15];
        for (int i = 0; i < puv.length; i++)
            puv[i] = false;
        for (int i = 0; i < subPuv.length; i++)
            puv[i] = subPuv[i];

        boolean[] fuv = new boolean[15];
        for (int i = 0; i < fuv.length; i++)
            fuv[i] = true;
        for (int i = 0; i < subFuv.length; i++)
            fuv[i] = subFuv[i];

        // Checks if the calculated FUV is different from the true FUV
        boolean[] funcFuv = Main.getFuv(pum, puv);
        boolean equalValues = true;
        for (int i = 0; i < fuv.length; i++)
            if (funcFuv[i] != fuv[i])
                equalValues = false;

        assertEquals(true, equalValues, "a true value was incorrectly produced");
    }

    @Test
    void shouldReturnTrueArray_whenGivenAllTrueParameters() {

        // Smaller constructions are used to make it more human readable

        boolean[][] subPum = {
                { true, true, true, true },
                { true, true, true, true },
                { true, true, true, true },
                { true, true, true, true },
        };

        boolean[] subPuv = { true, true, true, true };

        boolean[] subFuv = { true, true, true, true };

        // Incorporating the constructions into the actual sized matrices/arrays

        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < pum.length; i++)
            for (int j = 0; j < pum[0].length; j++)
                pum[i][j] = true;
        for (int i = 0; i < subPum.length; i++)
            for (int j = 0; j < subPum[0].length; j++)
                pum[i][j] = subPum[i][j];

        boolean[] puv = new boolean[15];
        for (int i = 0; i < puv.length; i++)
            puv[i] = false;
        for (int i = 0; i < subPuv.length; i++)
            puv[i] = subPuv[i];

        boolean[] fuv = new boolean[15];
        for (int i = 0; i < fuv.length; i++)
            fuv[i] = true;
        for (int i = 0; i < subFuv.length; i++)
            fuv[i] = subFuv[i];

        // Checks if the calculated FUV is different from the true FUV
        boolean[] funcFuv = Main.getFuv(pum, puv);
        boolean equalValues = true;
        for (int i = 0; i < fuv.length; i++)
            if (funcFuv[i] != fuv[i])
                equalValues = false;

        assertEquals(true, equalValues, "a true value was incorrectly produced");
    }
}