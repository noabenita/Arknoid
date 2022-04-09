package collidableshapes;

import java.awt.Color;

import geometricshapes.Point;
import geometricshapes.Rectangle;
import spritesandvelocity.Ball;
import spritesandvelocity.Velocity;

/**
 * @author noa benita
 * describe a Collidable interface.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param hitter - the ball
     * @param collisionPoint  - the collision point with the ball
     * @param currentVelocity - the current velocity of the ball
     * @return the new velocity of the ball after the hit (based on
     * // the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * @return Color.
     */
    Color getColor();
}
