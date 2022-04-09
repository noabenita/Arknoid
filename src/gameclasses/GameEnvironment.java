package gameclasses;

import collidableshapes.Collidable;
import collidableshapes.CollisionInfo;
import geometricshapes.Line;
import geometricshapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author noa benita
 * the game environment - contains the Collidable objects list
 */
public class GameEnvironment {
    private List<Collidable> items;

    /**
     * Constructor.
     * initialized the objects list
     */
    public GameEnvironment() {
        this.items = new ArrayList<>();
    }

    /**
     * @param c - the colloidal object
     *          addCollidable -- add the given colloidal to the environment.
     */
    public void addColloidalble(Collidable c) {
        this.items.add(c);
    }

    /**
     * @return a list
     * getCollisions -- return the list of the collidable objects
     */
    public List<Collidable> getCollisions() {
        return this.items;
    }

    /**
     * @param trajectory - a line - assume an object moving from line.start() to line.end().
     * @return a collision info
     * getClosestCollision --  if this object will not collide with any of the collidables
     *      in this collection, return null. Else, return the information
     *      about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo info = null;
        Point p;
        List<Point> intersectionsPoints = new ArrayList<>();
        List<Collidable> cList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            p = trajectory.closestIntersectionToStartOfLine(items.get(i).getCollisionRectangle());
            if (p != null) {
                intersectionsPoints.add(p);
                cList.add(items.get(i));
            }
        }
        int index = 0, j;
        if (intersectionsPoints.size() != 0) {
            Point min = intersectionsPoints.get(0);
            for (j = 0; j < intersectionsPoints.size(); j++) {
                Point p1 = intersectionsPoints.get(j);
                if (min.distance(trajectory.start()) >= p1.distance(trajectory.start())) {
                    min = p1;
                    index = j;
                }
            }
            info = new CollisionInfo(min, cList.get(index));
        }
        return info;
    }

    /**
     * @param c - the Collidabl
     *          remove the Collidabl.
     */
    public void deleteCollidable(Collidable c) {
        this.items.remove(c);
    }
}