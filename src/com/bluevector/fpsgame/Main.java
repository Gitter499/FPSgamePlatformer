package com.bluevector.fpsgame;

import com.bluevector.fpsgame.RayCaster.RayCaster;
import com.bluevector.fpsgame.RayCaster.CastData;
import com.bluevector.fpsgame.Math.Gmath;
import com.bluevector.fpsgame.Math.Vector;
import com.bluevector.fpsgame.Math.Triangle;

public class Main {


    public static void main(String[] args) {

        Vector origin = new Vector(0,0,0);
        Vector direction = new Vector(0,0,1);
        Triangle triangle = new Triangle(
                new Vector(3,3,3),
                new Vector(-3,3,3),
                new Vector(3,-3,3)
        );

        CastData data = RayCaster.cast(origin,direction,triangle);

        data.interceptionPoint.print();
        System.out.println(data.hit?"Hit Triangle":"Didn't Hit");
        System.out.println(data.distance);

    }
}