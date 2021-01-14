package com.bluevector.fpsgame.GameObject;

import main.java.com.bluevector.fpsgame.Math.Triangle;
import main.java.com.bluevector.fpsgame.Math.Vector;

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

    public static boolean interceptsTriangle(Vector normal, Vector interceptionPoint, Triangle mesh){

        /*
            planeX coefficient is normalX
            planeY coefficient is normalY
            planeZ coefficient is normalZ
            planeD normal dot product point A
        */

        /*

        function triangleContainsIntersection(Vector p,Triangle mesh){
    let n = Vector.normalize(vm.crossProduct(vm.subtract(t[1],t[0]),vm.subtract(t[2],t[0])))
    let AB = vm.normalize(vm.subtract(t[1],t[0]))
    let AC = vm.normalize(vm.subtract(t[2],t[0]))
    let BC = vm.normalize(vm.subtract(t[2],t[1]))
    let C = (Math.sign(vm.dotProduct(n,AB)) == Math.sign(vm.dotProduct(n,vm.normalize(vm.subtract(p,t[0])))))
    let B = (Math.sign(vm.dotProduct(n,AC)) == Math.sign(vm.dotProduct(n,vm.normalize(vm.subtract(p,t[0])))))
    let A = (Math.sign(vm.dotProduct(n,BC)) == Math.sign(vm.dotProduct(n,vm.normalize(vm.subtract(p,t[1])))))
    let contains = (A && B && C)
    return contains
}

         */

        Vector normalN = normal.copy();
        // Refactor to only calculate normal once

        normalN.normalize();

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

        A = (Math.sign(-1));

        /*
           let C = (Math.sign(vm.dotProduct(n,AB)) == Math.sign(vm.dotProduct(n,vm.normalize(vm.subtract(p,t[0])))))
        let B = (Math.sign(vm.dotProduct(n,AC)) == Math.sign(vm.dotProduct(n,vm.normalize(vm.subtract(p,t[0])))))
        let A = (Math.sign(vm.dotProduct(n,BC)) == Math.sign(vm.dotProduct(n,vm.normalize(vm.subtract(p,t[1])))))
         */


        return false;
    }

}

