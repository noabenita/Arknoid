package collidableshapes;

import geometricshapes.Point;

/**
 * @author noa benita
 * the information of the collision point - with witch rectangle and the point values.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable rectangle;

    /**
     * Constructor.
     *
     * @param collisionPoint     - the collision point
     * @param rect - the rectangle
     */
    public CollisionInfo(Point collisionPoint, Collidable rect) {
        this.collision = collisionPoint;
        this.rectangle = rect;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.rectangle;
    }
}