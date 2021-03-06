package RenderEngineBase;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 getScaled(double scalar) {
        return new Vector3(x * scalar, y * scalar, z * scalar);
    }

    public void scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
    }

    public Vector3 getSum(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    public Vector3 getProduct(Vector3 other) {
        return new Vector3(x * other.x, y * other.y, z * other.z);
    }

    public Vector3 getQuotient(Vector3 other) {
        return new Vector3(x / other.x, y / other.y, z / other.z);
    }

    public void add(Vector3 other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

    public Vector3 getDifference(Vector3 other) {
        return new Vector3(other.x - x, other.y - y, other.z - z);
    }

    public Vector3 getCrossProduct(Vector3 other) {
        return new Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }

    public double getDotProduct(Vector3 other) {
        return other.x * x + other.y * y + other.z * z;
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getMagnitudeSquared() {
        return x * x + y * y + z * z;
    }

    public double getAngleDifference(Vector3 other) {
        return Math.acos(getDotProduct(other) / (getMagnitude() * other.getMagnitude()));
    }

    public void normalize() {
        scale(1.0 / getMagnitude());
    }

    public Vector3 getNormalized() {
        if (getMagnitude() < 0.0001) {
            return new Vector3(1, 0, 0);
        }
        return getScaled(1.0 / getMagnitude());
    }

    public Vector3 getReflection(Vector3 normal) {
        return this.getSum(normal.getScaled(-2 * this.getDotProduct(normal)));
    }

    public Vector3 getCopy() {
        return new Vector3(x, y, z);
    }

    public void set(Vector3 newVector) {
        this.x = newVector.x;
        this.y = newVector.y;
        this.z = newVector.z;
    }

    public boolean isDuplicate(Vector3 other) {
        if (Math.abs(this.x - other.x) > 0.001) {
            return false;
        }
        if (Math.abs(this.y - other.y) > 0.001) {
            return false;
        }
        if (Math.abs(this.z - other.z) > 0.001) {
            return false;
        }
        return true;
    }
    public void enforceAbsoluteValue(double value){
        if(x > value){
            x = value;
        }
        else if(x < -value){
            x = -value;
        }
        if(y > value){
            y = value;
        }
        else if(y < -value){
            y = -value;
        }
        if(z > value){
            z = value;
        }
        else if(z < -value){
            z = -value;
        }
    }


    public void rotateMagnitude(Quaternion rot){

//        Vector3 rotation = rot.getEulerAngles();
//        Vector3 forward = new Vector3(-Math.cos(Math.PI/2-rotation.z)*Math.sin(rotation.x),Math.sin(Math.PI/2-rotation.z)*Math.sin(rotation.x),Math.sin(-Math.PI/2+rotation.x));
//        Vector3 left = new Vector3(-Math.cos(-rotation.z)*Math.sin(rotation.x),Math.sin(-rotation.z)*Math.sin(rotation.x),0);
//        Vector3 up = new Vector3(-Math.cos(Math.PI/2-rotation.z)*Math.sin(rotation.x+Math.PI/2),Math.sin(Math.PI/2-rotation.z)*Math.sin(rotation.x+Math.PI/2),Math.sin(rotation.x));
//        forward.scale(z);
//        left.scale(x);
//        up.scale(y);
//        up.add(forward);
//        up.add(left);
//        up.scale(-1);
//        this.x = up.x;
//        this.y = up.y;
//        this.z = up.z;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
    public String toRoundString() {
        return String.format("(x: %.2f, y: %.2f, z: %.2f)",x,y,z);
    }
}