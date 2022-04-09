package spritesandvelocity;

import biuoop.DrawSurface;

import java.awt.Color;

import geometricshapes.Line;
import geometricshapes.Point;
import geometricshapes.Rectangle;

import collidableshapes.CollisionInfo;

import gameclasses.GameEnvironment;
import gameclasses.GameLevel;

/**
 * @author noa benita
 * describe a ball which has a center point, radius, color and velocity values that is in the game.
 */
public class Ball implements Sprite {
    private Point p;
    private int radius;
    private Color color;
    private Velocity v;
    private GameEnvironment game;

    static final int DEFAULT_MAX = 200;
    static final int DEFAULT_MIN = 0;

    /**
     * Constructor.
     *
     * @param x1    - the x value of the center of this ball
     * @param y1    - the y value of the center of this ball
     * @param r     - the radius of the line
     * @param color - the color of this ball
     */
    public Ball(double x1, double y1, int r, java.awt.Color color) {
        this.p = new Point(x1, y1);
        this.radius = r;
        this.color = color;
        this.v = new Velocity(1, 1);
    }

    /**
     * Constructor.
     *
     * @param center - the values of the center of this ball
     * @param r      - the radius of the line
     * @param color  - the color of this ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.p = center;
        this.radius = r;
        this.color = color;
        this.v = new Velocity(1, 1);
    }

    /**
     * @return the center point of this ball
     */
    public Point getCenter() {
        return this.p;
    }

    /**
     * @return the radius length of this ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the x value of the center point
     */
    public double getX() {
        return this.p.getX();
    }

    /**
     * @return the y value of the center point
     */
    public double getY() {
        return this.p.getY();
    }

    /**
     * @return the color of this ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param v1 - new velocity
     *           change the velocity of this ball
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * @param dx - x-axis velocity
     * @param dy - y-axis velocity
     *           change the velocity of this ball
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of this ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * @param c    - the collision point
     * @param rect - a rectangle
     *             updateBallCenter - changing the ball center according to the collision point,
     *             check witch rib the ball hits and act according to it.
     */
    public void updateBallCenter(CollisionInfo c, Rectangle rect) {
        int screenRightBoard = 770;
        int screenLeftBoard = 30;
        int screenBottomBoard = 570;
        double xLeft = rect.getLeftRib().start().getX();
        double xRight = rect.getRightRib().start().getX();
        double yUpper = rect.getUpperRib().start().getY();
        double yBottom = rect.getBottomRib().start().getY();
        if (equalityCheck2(c.collisionPoint().getX(), xLeft)) {
            this.p.setX(xLeft - this.radius);
        }
        if (equalityCheck2(c.collisionPoint().getX(), xRight)) {
            this.p.setX(xRight + this.radius);
        }
        if (equalityCheck2(c.collisionPoint().getY(), yUpper)) {
            this.p.setY(yUpper - this.radius);
        }
        if (equalityCheck2(c.collisionPoint().getY(), yBottom)) {
            this.p.setY(yBottom + this.radius);
        }
        // if the center of the ball is out of the screen , enter it back in
        if (this.p.getY() >= screenBottomBoard) {
            this.p.setY(screenBottomBoard - radius);
        }
        if (this.p.getX() <= screenLeftBoard) {
            this.p.setX(screenLeftBoard + radius);
        }
        if (this.p.getX() >= screenRightBoard) {
            this.p.setX(screenRightBoard - radius);
        }
        Point tempPointR = new Point(rect.getUpperLeft().getX() + 5, rect.getUpperLeft().getY());
        Point tempPointL = new Point(rect.getUpperLeft().getX() - 5, rect.getUpperLeft().getY());
        Rectangle tempR = new Rectangle(tempPointR, rect.getWidth(), rect.getHeight());
        Rectangle tempL = new Rectangle(tempPointL, rect.getWidth(), rect.getHeight());
        if ((this.p.getX() < tempR.getRightRib().start().getX()
                && this.p.getX() > tempR.getLeftRib().start().getX()
                && this.p.getY() < tempR.getBottomRib().start().getY()
                && this.p.getY() > tempR.getUpperRib().start().getY())
                || (this.p.getX() < tempL.getRightRib().start().getX()
                && this.p.getX() > tempL.getLeftRib().start().getX()
                && this.p.getY() < tempL.getBottomRib().start().getY()
                && this.p.getY() > tempL.getUpperRib().start().getY())) {
            if ((p.distance(tempR.getLeftRib().start()) > p.distance(tempR.getRightRib().start()))
                    || (p.distance(tempL.getLeftRib().start()) > p.distance(tempL.getRightRib().start()))
                    || (p.distance(rect.getLeftRib().start())
                    > p.distance(rect.getRightRib().start()))) {
                p.setX(p.getX() + radius);
            } else {
                p.setX(p.getX() - radius);
            }
        }
    }

    /**
     * moveOneStep -- determinate the next step of the ball according to instruction and also,
     * depend on his speed and angle of the hitting in the rectangle (or in the paddle).
     */
    public void moveOneStep() {
        int signDx, signDy;
        // finding the velocity sign
        if (this.v.getDx() < 0) {
            signDx = -1;
        } else {
            signDx = 1;
        }
        if (this.v.getDy() < 0) {
            signDy = -1;
        } else {
            signDy = 1;
        }
        Point start = p;
        Point end = new Point(this.p.getX() + this.v.getDx() + signDx, this.p.getY() + this.v.getDy() + signDy);
        // definition of the movement line
        Line l = new Line(start, end);
        // finding a collision point
        CollisionInfo c = this.game.getClosestCollision(l);
        if (c == null) {
            this.p = this.getVelocity().applyToPoint(this.p);
        } else {
            Rectangle rect = c.collisionObject().getCollisionRectangle();
            // update the ball center according to the collision point
            updateBallCenter(c, rect);
            this.v = c.collisionObject().hit(this, c.collisionPoint(), this.v);
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.p.getX(), (int) this.p.getY(), this.radius);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.p.getX(), (int) this.p.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @param environment - the game environment
     *                    set the game environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.game = environment;
    }

    /**
     * @param g - the game
     *          dd the ball to the game ( to the sprite collection).
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @param a - the first value
     * @param b - the second value
     * @return true / false -- true if two values are equal and false if not
     */
    public boolean equalityCheck2(double a, double b) {
        double epsilon = Math.pow(10, -5);
        if (Math.abs(a - b) < epsilon) {
            return true;
        }
        return false;
    }

    /**
     * @param g - the game
     * Remove a sprite from the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

