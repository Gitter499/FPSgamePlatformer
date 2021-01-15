package com.bluevector.fpsgame.Math;

import java.lang.Math;

public class Vector {

    public float x;
    public float y;
    public float z;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector copy() {
        return new Vector(this.x, this.y, this.z);
    }

    public void print() {
        System.out.println("\nv | x: " + this.x + ",  y: " + this.y + ",  z: " + this.z + "\n");
    }

    public void print(String msg) {
        System.out.println("\n" + msg + "\nv | x: " + this.x + ",  y: " + this.y + ",  z: " + this.z + "\n");
    }

    public static Vector add(Vector v1, Vector v2) {
        Vector v = new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
        // v.print("Sum Vector:");
        return v;
    }

    public static Vector subtract(Vector v1, Vector v2) {
        Vector v = new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
        // v.print("Difference Vector:");
        return v;
    }

    public static Vector multiply(Vector v1, Vector v2) {
        Vector v = new Vector(v1.x * v2.x, v1.y * v2.y, v1.z * v2.z);
        // v.print("Product Vector:");
        return v;
    }

    public static Vector scalarMultiply(Vector v1, float scalar) {
        Vector v = new Vector(v1.x * scalar, v1.y * scalar, v1.z * scalar);
        // v.print("Scaled Vector:");
        return v;
    }

    public static float magnitude(Vector v) {
        return (float) (Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z));
    }

    public static Vector normal(Vector v1) {
        float magnitude = Vector.magnitude(v1);
        if (magnitude != 1) {
            Vector v = Vector.scalarMultiply(v1, 1 / magnitude);
            // v.print("Normalized Vector:");
            return v;
        } else {
            // v1.print("Normalized Vector");
            return v1;
        }
    }

    public static Vector crossProduct(Vector v1, Vector v2) {
        Vector v = new Vector(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
        // v.print("Orthogonal Vector:");
        return v;
    }

    public static float dotProduct(Vector v1, Vector v2) {
        float cos = v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
        // System.out.println("\nDot Product\n" + cos + "\n");
        return cos;
    }

    private static Vector rotateX(Vector v1, float theta) {
        Vector v = v1.copy();
        float cos = (float) Math.cos(theta);
        float sin = (float) Math.sin(theta);
        v.y = v.y * cos - v.z * sin;
        v.z = v.y * sin + v.z * cos;
        return v;
    }

    private static Vector rotateY(Vector v1, float theta) {
        Vector v = v1.copy();
        float cos = (float) Math.cos(theta);
        float sin = (float) Math.sin(theta);
        v.x = v.x * cos + v.z * sin;
        v.z = -v.y * sin + v.z * cos;
        return v;
    }

    private static Vector rotateZ(Vector v1, float theta) {
        Vector v = v1.copy();
        float cos = (float) Math.cos(theta);
        float sin = (float) Math.sin(theta);
        v.x = v.x * cos - v.y * sin;
        v.y = v.x * sin + v.y * cos;
        return v;
    }

    public static Vector rotate(Vector origin, Vector direction, Vector rotations, String eulerOrder) {
        Vector v = Vector.subtract(direction,origin);
        for (int i = 0; i < 3; i++) {
            if (eulerOrder.charAt(i) == 'x') {
                v = Vector.rotateX(v,rotations.x);
            } else if (eulerOrder.charAt(i) == 'y') {
                v = Vector.rotateY(v,rotations.y);
            } else if (eulerOrder.charAt(i) == 'z') {
                v = Vector.rotateZ(v,rotations.z);
            }
        }
        v = v.add(v,origin);
        return v;
    }

}