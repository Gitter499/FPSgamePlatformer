package com.bluevector.fpsgame.GameObject;

import com.bluevector.fpsgame.Math.Gmath;
import com.bluevector.fpsgame.Math.Triangle;
import com.bluevector.fpsgame.Math.Vector;

import java.lang.Math;

public class RayCaster {


    public static double interceptionDistance(Vector rayOrigin, Vector rayDirection, Triangle mesh) {
        Vector v1 = mesh.points[0].copy();
        v1.negativeTranslate(mesh.points[0]);
        Vector v2 = mesh.points[2].copy();
        v2.negativeTranslate(mesh.points[2]);
        Vector normal = Vector.crossProduct(v1, v2);
        double dot = Vector.dotProduct(normal, mesh.points[1]);
        double dist = -(Vector.dotProduct(normal, rayOrigin) + dot) / (Vector.dotProduct(normal, rayDirection));
        return dist;
    }

    public static boolean interceptsTriangle(Vector normal, Vector interceptionPoint, Triangle mesh) {

        // Refactor to only calculate normal once

        Vector _Ab = mesh.points[1].copy();
        Vector _Bc = mesh.points[2].copy();
        Vector _Ac = mesh.points[2].copy();

        // Points AB
        _Ab.negativeTranslate(mesh.points[0]);

        _Ab.normalize();


        // Points AC
        _Ac.negativeTranslate(mesh.points[0]);

        _Ac.normalize();


        // Points BC
        _Bc.negativeTranslate(mesh.points[1]);

        _Bc.normalize();

        boolean A;
        boolean B;
        boolean C;
        Vector temp = new Vector(0, 0, 0);

        temp = interceptionPoint.copy();
        temp.negativeTranslate((mesh.points[0]));
        temp.normalize();
        A = Gmath.sign(Vector.dotProduct(normal, _Ab)) == Gmath.sign(Vector.dotProduct(normal, temp));


        temp = interceptionPoint.copy();
        temp.negativeTranslate((mesh.points[0]));
        temp.normalize();
        B = Gmath.sign(Vector.dotProduct(normal, _Ac)) == Gmath.sign(Vector.dotProduct(normal, temp));

        temp = interceptionPoint.copy();
        temp.negativeTranslate((mesh.points[1]));
        temp.normalize();
        C = Gmath.sign(Vector.dotProduct(normal, _Bc)) == Gmath.sign(Vector.dotProduct(normal, temp));

        return false;
    }

}



