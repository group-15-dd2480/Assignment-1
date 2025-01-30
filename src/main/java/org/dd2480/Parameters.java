package org.dd2480;

import java.awt.geom.Point2D;

/**
 * Class to contain the inputs to the decide method.
 * 
 */
public class Parameters {
    public double epsilon;

    public double area1;
    public double area2;
    public double radius1;
    public double radius2;
    public double length1;
    public double length2;

    public int aPts;
    public int bPts;
    public int cPts;
    public int dPts;
    public int ePts;
    public int fPts;
    public int gPts;
    public int kPts;
    public int nPts;
    public int qPts;

    public int quads;
    public double dist;

    public Parameters(
            double epsilon,
            double area1,
            double area2,
            double radius1,
            double radius2,
            double length1,
            double length2,
            int aPts,
            int bPts,
            int cPts,
            int dPts,
            int ePts,
            int fPts,
            int gPts,
            int kPts,
            int nPts,
            int qPts,
            int quads,
            double dist) {
        this.epsilon = epsilon;
        this.area1 = area1;
        this.area2 = area2;
        this.radius1 = radius1;
        this.radius2 = radius2;
        this.length1 = length1;
        this.length2 = length2;
        this.aPts = aPts;
        this.bPts = bPts;
        this.cPts = cPts;
        this.dPts = dPts;
        this.ePts = ePts;
        this.fPts = fPts;
        this.gPts = gPts;
        this.kPts = kPts;
        this.nPts = nPts;
        this.qPts = qPts;
        this.quads = quads;
        this.dist = dist;
    }

}
