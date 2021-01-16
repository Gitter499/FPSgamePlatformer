package com.bluevector.fpsgame.RayTracer;

import com.bluevector.fpsgame.Math.Vector;

public class Camera {

    public Vector position;
    public Vector rotations;

    public int fov;

    public Camera(Vector position, Vector rotations, int fov) {
        this.position = position;
        this.rotations = rotations;
        this.fov = fov;
    }
}
