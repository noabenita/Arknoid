package geometricshapes;

/**
 * @author noa benita
 * describe a point which has a x and a y values.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x - the x value of a point
     * @param y - the y value of a point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param x1 - new x value
     * change the x value of this point
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * @param y1 - new y value
     * change the y value of this point
     */
    public void setY(double y1) {
        this.y = y1;
    }

    /**
     * @param other 2nd point
     * @return the distance between two points -- this point and other point
     */
    public double distance(Point other) {
        return (Math.sqrt((Math.pow(this.x - other.getX(), 2)) + (Math.pow(this.y - other.getY(), 2))));
    }

    /**
     * @param other 2nd point
     * @return true / false -- true if the points are equal and false if not
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -15);
        if ((Math.abs(this.x - other.getX()) < epsilon)) {
            if (Math.abs(this.y - other.getY()) < epsilon) {
                return true;
            }
            return false;
        }
        return false;
    }
}