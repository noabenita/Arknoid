package geometricshapes;

import java.util.List;

/**
 * @author noa benita
 * describe a line which has two points values - start and end (each one with a x and a y values).
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor.
     *
     * @param start - the start of the line
     * @param end   - the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     *
     * @param x1 - the x value of the start of the line
     * @param y1 - the y value of the start of the line
     * @param x2 - the x value of the end of the line
     * @param y2 - the y value of the end of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of this line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double a = ((this.start.getX() + this.end.getX()) / 2);
        double b = ((this.start.getY() + this.end.getY()) / 2);
        Point middlePoint = new Point(a, b);
        return middlePoint;
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param a - the first value
     * @param b - the second value
     * @return true / false -- true if two values are equal and false if not
     */
    public boolean equalityCheck(double a, double b) {
        double epsilon = Math.pow(10, -5);
        if (Math.abs(a - b) < epsilon) {
            return true;
        }
        return false;
    }

    /**
     * @param other 2nd line
     * @return a point value if the lines intersect and null otherwise
     */
    public Point samePoint(Line other) {
        if ((this.start.equals(other.start) || this.start.equals(other.end))
                && (this.end.equals(other.start) || this.end.equals(other.end))) {
            return null;
        }
        double distance1 = this.start.distance(this.end);
        double distance2 = other.start.distance(other.end);
        double sum = distance1 + distance2;

        if (this.start.equals(other.start)) {
            double distance = this.end.distance(other.end);
            if (equalityCheck(distance, sum)) {
                return this.start;
            }
        } else if (this.start.equals(other.end)) {
            double distance = this.end.distance(other.start);
            if (equalityCheck(distance, sum)) {
                return this.start;
            }
        } else if (this.end.equals(other.start)) {
            double distance = this.start.distance(other.end);
            if (equalityCheck(distance, sum)) {
                return this.end;
            }
        } else if (this.end.equals(other.end)) {
            double distance = this.start.distance(other.start);
            if (equalityCheck(distance, sum)) {
                return this.end;
            }
        }
        return null;
    }

    /**
     * @return true / false
     * vertical -- return true if the line is vertical to the x-axis and false otherwise
     */
    public boolean isVertical() {
        if (equalityCheck(this.start.getX(), this.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * @param x - x value of a point
     * @param y - y value of a point
     * @return true / false
     * point on line -- return true if the point is one of the points on the line, and false otherwise
     */
    public boolean pointOnLine(double x, double y) {
        double a1 = Math.min(this.start.getX(), this.end.getX());
        double a2 = Math.max(this.start.getX(), this.end.getX());
        double b1 = Math.min(this.start.getY(), this.end.getY());
        double b2 = Math.max(this.start.getY(), this.end.getY());
        if (((x > a1) || (equalityCheck(x, a1))) && ((x < a2) || (equalityCheck(x, a2)))
                && ((y > b1) || equalityCheck(y, b1)) && ((y < b2) || (equalityCheck(y, b1)))) {
            return true;
        }
        return false;
    }

    /**
     * @return the incline of this line
     */
    public double getM() {
        return ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
    }

    /**
     * @return the cutting point with the y-axis
     */
    public double getB() {
        return this.start.getY() - this.getM() * (this.start.getX());
    }

    /**
     * @param other 2nd line
     * @return a point value if the two lines intersect and null otherwise
     */
    public Point intersectionWith(Line other) {
        double x;
        double y;
        // check if the lines are just 2 points
        if (twoPoints(other) != null) {
            return twoPoints(other);
        }
        // check if one line is just a point and if this point is on the second line
        if (justPoint(other) != null) {
            return justPoint(other);
        }
        // check if the two lines are vertical to the x-axis
        if (this.isVertical() && other.isVertical()) {
            // check if the lines start at the same point
            if (equalityCheck(this.start.getX(), other.start.getX())) {
                Point p = samePoint(other);
                if (p != null) {
                    return p;
                }
                return null;
            }
            return null;
        }
        // check if this line is vertical to the x-axis
        if (this.isVertical()) {
            x = this.start.getX();
            y = other.getM() * x + other.getB();
            if (this.pointOnLine(x, y) && other.pointOnLine(x, y)) {
                return new Point(x, y);
            }
        }
        // check if the other line is vertical to the x-axis
        if (other.isVertical()) {
            x = other.start.getX();
            y = this.getM() * x + this.getB();
            if (this.pointOnLine(x, y) && other.pointOnLine(x, y)) {
                return new Point(x, y);
            }
        }
        // check if the lines are parallel or converge because their slope is the same
        if (equalityCheck(this.getM(), other.getM())) {
            Point p = samePoint(other);
            if (p != null) {
                return p;
            }
            return null;
        }
        x = (this.getB() - other.getB()) / (other.getM() - this.getM());
        y = this.getM() * x + this.getB();

        if (this.pointOnLine(x, y) && other.pointOnLine(x, y)) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * @param other 2nd line
     * @return point / null
     * return a point if the two lines are just a point, and null otherwise
     */
    public Point twoPoints(Line other) {
        if ((this.start.equals(this.end)) && (other.start.equals(other.end)) && (this.start.equals(other.start))) {
            return new Point(this.start.getX(), this.start.getY());
        }
        return null;
    }

    /**
     * @param other 2nd line
     * @return point / null
     * return a point if one line is just a point and if this point is on the second line
     */
    public Point justPoint(Line other) {
        if (this.start.equals(this.end)) {
            if (other.pointOnLine(this.start.getX(), this.start.getY())) {
                return new Point(this.start.getX(), this.start.getY());
            }
        }
        if (other.start.equals(other.end)) {
            if (this.pointOnLine(other.start.getX(), other.start.getY())) {
                return new Point(other.start.getX(), other.start.getY());
            }
        }
        return null;
    }

    /**
     * @param other 2nd line
     * @return true / false
     * return true if the two lines intersect and false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * @param other 2nd line
     * @return true / false
     * equals -- return true if the two lines are equals and false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start) || this.start.equals(other.end))
                && (this.end.equals(other.start) || this.end.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * @param rect Rectangle
     * @return a point or null
     * closestIntersectionToStartOfLine -- If this line does not intersect with the rectangle, return null.
     *      Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line l = new Line(this.start, this.end);
        List<Point> pointsList = rect.intersectionPoints(l);
        if (pointsList.isEmpty()) {
            return null;
        }
        // if there are two intersection points
        if (pointsList.size() == 2) {
            if ((pointsList.get(0) != null) && (pointsList.get(1) != null)) {
                if (pointsList.get(0).distance(this.start) <= pointsList.get(1).distance(this.start)) {
                    return pointsList.get(0);
                } else {
                    return pointsList.get(1);
                }
            }
        }
        return pointsList.get(0);
    }
}




