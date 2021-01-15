package com.bluevector.fpsgame;

import com.bluevector.fpsgame.Math.Vector;
import com.bluevector.fpsgame.RayTracer.Camera;
import com.bluevector.fpsgame.RayTracer.Screen;

public class Main {


    public static void main(String[] args) {

        Camera camera = new Camera(new Vector(0,0,0),new Vector(0f,0f,0f),1);

        Screen screen = new Screen(4,4);
        screen.setup(camera.position,camera);


    }
}