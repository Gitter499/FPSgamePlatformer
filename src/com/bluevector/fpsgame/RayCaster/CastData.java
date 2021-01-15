package com.bluevector.fpsgame.RayCaster;

import com.bluevector.fpsgame.Math.Vector;

public class CastData {
    public float distance;
    public Vector interceptionPoint;
    public boolean hit;

    public CastData(float distance, Vector interceptionPoint, boolean hit) {
        this.distance = distance;
        this.interceptionPoint = interceptionPoint;
        this.hit = hit;
    }
}