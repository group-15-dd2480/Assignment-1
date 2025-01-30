package org.dd2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.dd2480.Main.Op;
import org.junit.jupiter.api.Test;

class GetPumTest {

    @Test
    void shouldThrowIllegalArgumentException_whenSuppliedWithParametersOfIncorrectDimensions() {

        Op[][] lcmRowTooShort = new Op[14][15];
        Op[][] lcmRowTooLong = new Op[16][15];
        Op[][] lcmColumnTooShort = new Op[15][14];
        Op[][] lcmColumnTooLong = new Op[15][16];
        Op[][] lcmCorrect = new Op[15][15];

        boolean[] cmvTooShort = new boolean[14];
        boolean[] cmvTooLong = new boolean[16];
        boolean[] cmvCorrect = new boolean[15];

        assertThrows(IllegalArgumentException.class, () -> Main.getPum(lcmRowTooShort, cmvCorrect),
                "too small row count in LCM should not be accepted");
        assertThrows(IllegalArgumentException.class, () -> Main.getPum(lcmRowTooLong, cmvCorrect),
                "too big row count in LCM should not be accepted");
        assertThrows(IllegalArgumentException.class, () -> Main.getPum(lcmColumnTooShort, cmvCorrect),
                "too small column count in LCM should not be accepted");
        assertThrows(IllegalArgumentException.class, () -> Main.getPum(lcmColumnTooLong, cmvCorrect),
                "too big column count in LCM should not be accepted");

        assertThrows(IllegalArgumentException.class, () -> Main.getPum(lcmCorrect, cmvTooShort),
                "too short CMV should not be accepted");
        assertThrows(IllegalArgumentException.class, () -> Main.getPum(lcmCorrect, cmvTooLong),
                "too long CMV should not be accepted");
    }

    @Test
    void shouldReturnIncorrectMatrix_whenGivenIncorrectParameters() {

        // Smaller constructions are used to make it more human readable

        Op[][] subLcm = {
            { Op.ANDD, Op.ANDD, Op.ANDD, Op.ANDD },
            { Op.ANDD, Op.ANDD, Op.ORR, Op.ORR },
            { Op.ORR, Op.ORR, Op.ANDD, Op.ORR },
            { Op.ORR, Op.ORR, Op.ANDD, Op.ANDD },
    };

        boolean[] subCmv = { true, true, true, true };

        boolean[][] subPum = {
            // Diagonal is irrelevant
            { false, false, false, false },
            { false, false, false, false },
            { false, false, false, false },
            { false, false, false, false },
    };

        // Incorporating the constructions into the actual sized matrices/arrays

        Op[][] lcm = new Op[15][15];
        for (int i = 0; i < lcm.length; i++)
            for (int j = 0; j < lcm[0].length; j++)
                lcm[i][j] = Op.NOTUSED;
        for (int i = 0; i < subLcm.length; i++)
            for (int j = 0; j < subLcm[0].length; j++)
                lcm[i][j] = subLcm[i][j];

        boolean[] cmv = new boolean[15];
        for (int i = 0; i < cmv.length; i++)
            cmv[i] = false;
        for (int i = 0; i < subCmv.length; i++)
            cmv[i] = subCmv[i];

        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < pum.length; i++)
            for (int j = 0; j < pum[0].length; j++)
                pum[i][j] = true;
        for (int i = 0; i < subPum.length; i++)
            for (int j = 0; j < subPum[0].length; j++)
                pum[i][j] = subPum[i][j];

        // Checks if the calculated PUM is different from the true PUM, matrix diagonal
        // is ignored
        boolean[][] funcPum = Main.getPum(lcm, cmv);
        boolean equalValues = true;
        for (int i = 0; i < pum.length; i++)
            for (int j = 0; j < pum[0].length; j++)
                if (i != j && funcPum[i][j] != pum[i][j])
                    equalValues = false;

        assertEquals(false, equalValues, "incorrect values were produced in the PUM");
    }

    @Test
    void shouldReturnCorrectMatrix_whenGivenCorrectParameters() {

        // Smaller constructions are used to make it more human readable

        Op[][] subLcm = {
                { Op.ANDD, Op.ANDD, Op.ORR, Op.ANDD },
                { Op.ANDD, Op.ANDD, Op.ORR, Op.ORR },
                { Op.ORR, Op.ORR, Op.ANDD, Op.ANDD },
                { Op.ANDD, Op.ORR, Op.ANDD, Op.ANDD },
        };

        boolean[] subCmv = { false, true, true, true };

        boolean[][] subPum = {
                // Diagonal is irrelevant
                { true, false, true, false },
                { false, true, true, true },
                { true, true, true, true },
                { false, true, true, true },
        };

        // Incorporating the constructions into the actual sized matrices/arrays

        Op[][] lcm = new Op[15][15];
        for (int i = 0; i < lcm.length; i++)
            for (int j = 0; j < lcm[0].length; j++)
                lcm[i][j] = Op.NOTUSED;
        for (int i = 0; i < subLcm.length; i++)
            for (int j = 0; j < subLcm[0].length; j++)
                lcm[i][j] = subLcm[i][j];

        boolean[] cmv = new boolean[15];
        for (int i = 0; i < cmv.length; i++)
            cmv[i] = false;
        for (int i = 0; i < subCmv.length; i++)
            cmv[i] = subCmv[i];

        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < pum.length; i++)
            for (int j = 0; j < pum[0].length; j++)
                pum[i][j] = true;
        for (int i = 0; i < subPum.length; i++)
            for (int j = 0; j < subPum[0].length; j++)
                pum[i][j] = subPum[i][j];

        // Checks if the calculated PUM is different from the true PUM, matrix diagonal
        // is ignored
        boolean[][] funcPum = Main.getPum(lcm, cmv);
        boolean equalValues = true;
        for (int i = 0; i < pum.length; i++)
            for (int j = 0; j < pum[0].length; j++)
                if (i != j && funcPum[i][j] != pum[i][j])
                    equalValues = false;

        assertEquals(true, equalValues, "incorrect values were produced in the PUM");
    }
}
