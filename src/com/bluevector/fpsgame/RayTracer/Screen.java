package com.bluevector.fpsgame.RayTracer;

import com.bluevector.fpsgame.Math.Vector;

import java.awt.*;


public class Screen {
    /*
    Methods
    https://dinex86.github.io/FOV-Calculator/
     */

    public static int width;
    public static int height;
    public static Color[][] pixels;
    public Vector[][] directions;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        this.pixels = new Color[width][height];
        this.directions = new Vector[width][height];
    }

    public void setPixelDirections(int x, int y, Vector origin, Camera camera){
        /*

                    x:(i-canvas.width/this.pix/2)/(canvas.width/this.pix/2)*this.fov,
                    y:(i-canvas.height/this.pix/2)/(canvas.height/this.pix/2)*(this.fov*canvas.height/canvas.width),
                    z:1

         */



        this.directions[x][y] = new Vector(
                (x-Screen.width)/(Screen.width/2)*camera.fov,
                (y-Screen.height)/(Screen.height/2)*(camera.fov*Screen.height/Screen.width),
                1
        );
        this.directions[x][y] = Vector.normal(this.directions[x][y]);
        this.directions[x][y] = Vector.rotate(camera.position,this.directions[x][y],camera.rotations,"zyx");
        this.directions[x][y].print("Position: x~"+x+" y~"+y);

        //camera.rotations.print("camera rotations: " + camera.rotations);
        //origin.print("vector "+x+" "+y);

    }

    public static void traceRays (Vector origin, Vector direction){

    }

    public void setup (Vector origin, Camera camera){
        for(int i = 0;i<Screen.width;i++){
            for(int j = 0;j<Screen.height;j++){
                this.setPixelDirections(i,j,origin,camera);
            }
        }
    }

    public static void draw(){

    }



}

/*

import {rayTrace} from "../scripts/rayTrace.js"

export class Camera {
    constructor(pos,dir,fov,pix,col){
        this.pos = pos
        this.dir = dir
        this.fov = fov * Math.PI / 180
        this.pix = pix
        this.col = col
    }
    renderView(canvas,triangles){
        for(let i = 0;i<canvas.width/this.pix;i++){
            for(let j = 0;j<canvas.height/this.pix;j++){
                let dir = {
                    x:(i-canvas.width/this.pix/2)/(canvas.width/this.pix/2)*this.fov,
                    y:(i-canvas.height/this.pix/2)/(canvas.height/this.pix/2)*(this.fov*canvas.height/canvas.width),
                    z:1
                }

                let m = Math.sqrt(dir.x**2+dir.y**2+dir.z**2)
                dir.x /= m
                dir.y /= m
                dir.z /= m

                let closest = {distance:Infinity,color:this.col}
                triangles.forEach(triangle => {
                    let rt = rayTrace({pos:this.pos,dir:dir},triangle.coors)
                    if(rt.hit){console.log(rt.distance)}
                    // if(rt.hit && rt.distance < closest.distance && rt.distance > 0){
                    //     closest.distance = rt.distance
                    //     closest.color = triangle.color
                    // }
                })
                //canvas.getContext("2d").fillStyle=closest.color
                //canvas.getContext("2d").fillRect(i*this.pix,j*this.pix,this.pix,this.pix)
            }
        }
    }
}
 */