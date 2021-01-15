package com.bluevector.fpsgame.Math;

public class Triangle {

    public Vector[] points = {};
    public Vector normal;

    public Triangle(Vector p1, Vector p2, Vector p3) {
        this.points = new Vector[] { p1, p2, p3 };
        this.normal = Vector.normal(Vector.crossProduct(Vector.normal(Vector.subtract(this.points[0], this.points[1])),
                Vector.normal(Vector.subtract(this.points[2], this.points[1]))));
    }
}
