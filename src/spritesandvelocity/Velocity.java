package spritesandvelocity;
import geometricshapes.Point;


/**
 * @author noa benita
 * describe a velocity of a ball which has a x-axis velocity and a y-axis velocity
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx - the x-axis velocity
     * @param dy - the y-axis velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor.
     *
     * @param angle - the angle of the velocity
     * @param speed - the speed value of the velocity
     * @return the velocity with a angle and a speed values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx value of this velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy value of this velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param p - a point to apply the changes on
     * Take a point with position (x,y) and return a new point
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x, y);
    }
}