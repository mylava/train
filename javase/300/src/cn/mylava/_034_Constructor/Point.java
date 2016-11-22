package cn.mylava._034_Constructor;

/**
 * 16/3/9.
 *
 * 三维坐标点
 */
public class Point {
    double x,y,z;

    public Point() {
    }

    public Point(double _x, double _y, double _z) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * 计算两个点的距离
     * @param p
     * @return
     */
    public double distance(Point p){
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) + (z - p.z) * (z - p.z));
    }

    public static void main(String[] args) {
        Point p1 = new Point(3,6,9);
        Point p2 = new Point(3,4,8);
        System.out.println(p1.distance(p2));
    }
}
