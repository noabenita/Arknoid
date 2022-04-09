package collidableshapes;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import geometricshapes.Point;
import geometricshapes.Rectangle;
import spritesandvelocity.Ball;
import spritesandvelocity.Velocity;
import spritesandvelocity.Sprite;
import removing.HitListener;
import removing.HitNotifier;
import gameclasses.GameLevel;

/**
 * @author noa benita
 * describe a block which is a rectangle, and it's color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     *
     * @param r     - a rectangle
     * @param color - the color of this rectangle
     */
    public Block(Rectangle r, java.awt.Color color) {
        this.rect = r;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return the colloidal object
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * @param hitter          - the ball
     * @param collisionPoint  - the collision point with the ball
     * @param currentVelocity - the current velocity of the ball
     * @return a velocity value
     * hit -- return a new velocity according to the ball hitting point
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        if ((rect.getLeftRib().pointOnLine(collisionPoint.getX(), collisionPoint.getY()))
                || rect.getRightRib().pointOnLine(collisionPoint.getX(), collisionPoint.getY())) {
            v = new Velocity(-v.getDx(), v.getDy());
        }
        if ((rect.getUpperRib().pointOnLine(collisionPoint.getX(), collisionPoint.getY()))
                || rect.getBottomRib().pointOnLine(collisionPoint.getX(), collisionPoint.getY())) {
            v = new Velocity(v.getDx(), -v.getDy());
        }
        this.notifyHit(hitter);
        return v;
    }

    /**
     * @param surface - draw surface
     *                draw the block on the given draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * @param g - the game
     *          add the block to the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @param game game
     *             remove the block from the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @param hitter ball
     * called whenever a hit() occurs.
     * notifiers all of the registered HitListener objects by calling their hitEvent method.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
