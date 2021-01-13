package main.java.com.bluevector.fpsgame.Math;

public class Vector {
    double x;
    double y;
    double z;

    public Vector(double X, double Y, double Z) {
        x = X;
        y = Y;
        z = Z;
    }

    public Vector copy() {
        return new Vector(x, y, z);
    }

    public void translate(Vector v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    public void scale(double scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
    }

    public void multiply(Vector v) {
        x *= v.x;
        y *= v.y;
        z *= v.z;
    }

    private void rotateX(Vector v, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        y = v.y * cos - v.z * sin;
        z = v.y * sin + v.z * cos;
    }

    private void rotateY(Vector v, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        x = v.x * cos + v.z * sin;
        z = -v.y * sin + v.z * cos;
    }

    private void rotateZ(Vector v, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        x = v.x * cos - v.y * sin;
        y = v.x * sin + v.y * cos;
    }

    public void rotate(Vector origin, Vector rotations, String eulerOrder) {
        Vector v = this.copy();
        Vector reversedOrigin = origin.copy();
        reversedOrigin.scale(-1);
        v.translate(reversedOrigin);
        for (int i = 0; i < 3; i++) {
            if (eulerOrder.charAt(i) == 'x') {
                v.rotateX(v, rotations.x);
            } else if (eulerOrder.charAt(i) == 'y') {
                v.rotateY(v, rotations.y);
            } else {
                v.rotateZ(v, rotations.z);
            }
        }
        v.translate(origin);
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double dotProduct(Vector a, Vector b) {
        return (a.x * b.x + a.y * b.y + a.z * b.z) / a.magnitude() / b.magnitude();
    }

    public double angleDifference(Vector a, Vector b) {
        return Math.acos(this.dotProduct(a, b));
    }

    public void normalize() {
        double magnitude = this.magnitude();
        if (magnitude != 1) {
            this.scale(1 / magnitude);
        }
    }

    public void crossProduct(Vector a, Vector b) {
        x = a.y * b.z - a.z * b.y;
        y = a.x * b.z - a.z * b.x;
        z = a.x * b.y - a.y * b.x;
    }

    public static Vector direction(String input){
        if (input == "up"){
            return new Vector(0,1,0);
        }
        else if (input == "down"){
            return new Vector(0,-1,0);
        }
        else if (input == "left"){
            return new Vector(-1,0,0);
        }
        else if (input == "right"){
            return new Vector(1,0,0);
        }
        else if (input == "forward"){
            return new Vector(0,0, 1);
        }
        else if (input == "backward"){
            return new Vector(0,0,-1);
        } else {
            return new Vector(0,0,0);
        }
    }
}