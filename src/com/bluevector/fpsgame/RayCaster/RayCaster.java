package com.bluevector.fpsgame.RayCaster;

import com.bluevector.fpsgame.Math.Triangle;
import com.bluevector.fpsgame.Math.Vector;

import java.lang.Math;

public class RayCaster {

    public static float castDistance(Vector origin, Vector direction, Triangle triangle) {
        // Equation of plane is dot product of normal and point1 - d
        // Interception point is origin + direction * distance
        // dot product of (point1 - (origin + direction * distance)) and normal = 0
        // dp(p1 - add(origin+mult(direction,distance)), normal) = 0
        // dp(normal,p1) - dp(normal,add(origin+mult(direction,distance))) = 0
        // dp(normal,p1) = dp(normal,add(origin+mult(direction,distance)))
        // dp(normal,p1) = dp(normal,origin) + dp(normal,direction*distance)
        // dp(normal,p1) - dp(normal,origin) = dp(normal,direction*distance)
        // ( dp(normal,p1) - dp(normal,origin) ) / dp(normal,direction) = distance
        float distance = (Vector.dotProduct(triangle.normal, triangle.points[1])
                - Vector.dotProduct(triangle.normal, origin))
                / (Vector.dotProduct(triangle.normal, Vector.normal(direction)));
        return distance;
    }

    // https://blackpawn.com/texts/pointinpoly/#:~:text=Same%20Side%20Technique,but%20it%20is%20very%20slow

    public static boolean sameSide(Vector p1, Vector p2, Vector a, Vector b) {
        Vector baUnitDifference = Vector.normal(Vector.subtract(b, a));
        Vector cp1 = Vector.crossProduct(baUnitDifference, Vector.normal(Vector.subtract(p1, a)));
        Vector cp2 = Vector.crossProduct(baUnitDifference, Vector.normal(Vector.subtract(p2, a)));
        return Vector.dotProduct(cp1, cp2) >= 0;
    }

    public static boolean triangleContainsPoint(Vector point, Triangle triangle) {
        return RayCaster.sameSide(point, triangle.points[0], triangle.points[1], triangle.points[2])
                && RayCaster.sameSide(point, triangle.points[1], triangle.points[0], triangle.points[2])
                && RayCaster.sameSide(point, triangle.points[2], triangle.points[0], triangle.points[1]);
    }

    public static CastData cast(Vector origin, Vector direction, Triangle triangle) {
        float distance = RayCaster.castDistance(origin, direction, triangle);
        Vector interceptionPoint = origin.copy();
        boolean hit = false;
        if (distance >= 0) {
            interceptionPoint = Vector.add(interceptionPoint, Vector.scalarMultiply(direction, distance));
            hit = RayCaster.triangleContainsPoint(interceptionPoint, triangle);
        }
        return new CastData(distance, interceptionPoint, hit);
    }
}