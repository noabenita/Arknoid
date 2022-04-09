package spritesandvelocity;

import biuoop.DrawSurface;

/**
 * @author noa benita
 * the Sprite interface - (moving objects).
 */
public interface Sprite {

    /**
     * @param d - draw surface
     *          draw the sprite on the given draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
