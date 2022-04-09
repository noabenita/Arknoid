package spritesandvelocity;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;


/**
 * @author noa benita
 * the sprite collection - contains the sprites (movoing objects) list
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructor.
     * initialized the sprites list
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * @return a list
     * getSpriteCollection -- return the list of the sprites objects
     */
    public List<Sprite> getSpriteCollection() {
        return spriteList;
    }

    /**
     * @param s - the sprite object
     *         addSprite -- add the given sprite to the sprite collection list.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * notifyAllTimePassed -- call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }

    /**
     * @param d - surface
     * drawAllOn -- drawOn on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).drawOn(d);
        }
    }

    /**
     * @param s - sprite
     * remove this sprite from the sprite list.
     */
    public void deleteSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}
