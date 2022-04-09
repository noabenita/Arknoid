package geometricshapes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author noa benita
 * describe a Rectangle, it's height, width, and hes a line to each one of its ribs.
 */
public class Rectangle {

    private Point p;
    private double width;
    private double height;
    private Line leftRib;
    private Line rightRib;
    private Line upperRib;
    private Line bottomRib;

    /**
     * Constructor.
     *
     * @param upperLeft     - the upper left point of the rectangle
     * @param width - the width of this rectangle
     * @param height - the height of this rectangle
     *               Create a new rectangle with location and width/height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.p = upperLeft;
        this.width = width;
        this.height = height;

        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.upperRib = new Line(upperLeft, upperRight);
        Point bottomRight = new Point(upperRight.getX(), upperRight.getY() + height);
        this.rightRib = new Line(upperRight, bottomRight);
        Point bottomLeft = new Point(bottomRight.getX() - width, bottomRight.getY());
        this.bottomRib = new Line(bottomLeft, bottomRight);
        this.leftRib = new Line(upperLeft, bottomLeft);

    }

    /**
     * @param line - the movement line
     * @return a list
     *  Return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        if (line.intersectionWith(upperRib) != null) {
            intersections.add(line.intersectionWith(upperRib));
        }
        if (line.intersectionWith(bottomRib) != null) {
            intersections.add(line.intersectionWith(bottomRib));
        }
        if (line.intersectionWith(leftRib) != null) {
            intersections.add(line.intersectionWith(leftRib));
        }
        if (line.intersectionWith(rightRib) != null) {
            intersections.add(line.intersectionWith(rightRib));
        }
        return intersections;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.p;
    }

    /**
     * @param newUpperLeft - the new upper left point to set to the rectangle.
     * change the upper-left point of the rectangle, and the line description of each rib.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.p = newUpperLeft;
        Point upperRight = new Point(newUpperLeft.getX() + width, newUpperLeft.getY());
        this.upperRib = new Line(newUpperLeft, upperRight);
        Point bottomRight = new Point(upperRight.getX(), upperRight.getY() + height);
        this.rightRib = new Line(upperRight, bottomRight);
        Point bottomLeft = new Point(bottomRight.getX() - width, bottomRight.getY());
        this.bottomRib = new Line(bottomLeft, bottomRight);
        this.leftRib = new Line(newUpperLeft, bottomLeft);
    }

    /**
     * @return the right rib of the rectangle.
     */
    public Line getRightRib() {
        return this.rightRib;
    }

    /**
     * @return the upper rib of the rectangle.
     */
    public Line getUpperRib() {
        return this.upperRib;
    }

    /**
     * @return the bottom rib of the rectangle.
     */
    public Line getBottomRib() {
        return this.bottomRib;
    }

    /**
     * @return the left rib of the rectangle.
     */
    public Line getLeftRib() {
        return this.leftRib;
    }
}
