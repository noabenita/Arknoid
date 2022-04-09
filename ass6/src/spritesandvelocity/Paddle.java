package spritesandvelocity;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import geometricshapes.Point;
import geometricshapes.Rectangle;
import collidableshapes.Block;
import collidableshapes.Collidable;
import gameclasses.GameLevel;


/**
 * @author noa benita
 * describe a block which is a rectangle, and it's color.
 * this specific rectangle is a paddle that is able to move to the right and to the left,
 * according to the user's orders.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    private int paddleSpeed;

    /**
     * Constructor.
     *
     * @param rect     - a rectangle
     * @param color    - the color of this rectangle (paddle)
     * @param keyboard - the user keyboard
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard) {
        this.rectangle = rect;
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * move the paddle one step to the left.
     */
    public void moveLeft() {
        int leftWall = 30;
        Point p = new Point((this.rectangle.getUpperLeft().getX() - paddleSpeed), this.rectangle.getUpperLeft().getY());
        rectangle.setUpperLeft(p);
        if (rectangle.getLeftRib().start().getX() - paddleSpeed <= leftWall) {
            p = new Point(leftWall, this.rectangle.getUpperLeft().getY());
            rectangle.setUpperLeft(p);
        }
    }

    /**
     * move the paddle one step to the right.
     */
    public void moveRight() {
        int rightWall = 770;
        Point p = new Point((this.rectangle.getUpperLeft().getX() + paddleSpeed),
                (int) this.rectangle.getUpperLeft().getY());
        rectangle.setUpperLeft(p);
        if (rectangle.getRightRib().start().getX() + paddleSpeed >= rightWall) {
            p = new Point(rightWall - this.rectangle.getWidth(), this.rectangle.getUpperLeft().getY());
            rectangle.setUpperLeft(p);
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double speed;
        double x = this.rectangle.getWidth() / 5;
        double x1 = this.rectangle.getUpperLeft().getX();
        double x2 = x + x1;
        double x3 = x + x2;
        double x4 = x + x3;
        double x5 = x + x4;
        double x6 = x + x5;
        double angle;
        if (collisionPoint.getX() >= x1 && collisionPoint.getX() < x2) {
            angle = 300;
        } else if (collisionPoint.getX() >= x2 && collisionPoint.getX() < x3) {
            angle = 330;
        } else if (collisionPoint.getX() >= x4 && collisionPoint.getX() < x5) {
            angle = 30;
        } else if (collisionPoint.getX() >= x5 && collisionPoint.getX() <= x6) {
            angle = 60;
        } else {
            Block block = new Block(this.rectangle, this.color);
            return block.hit(hitter, collisionPoint, currentVelocity);
        }
        speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                + (currentVelocity.getDy() * currentVelocity.getDy()));
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * @param g - the game
     *          Add this paddle to the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @return paddle color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @param s speed
     *          set the paddle speed
     */
    public void setSpeed(int s) {
        this.paddleSpeed = s;
    }
}