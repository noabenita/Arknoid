package animation;

import biuoop.DrawSurface;

/**
 * @author noa benita
 * the Animation interface.
 */
public interface Animation {

    /**
     * @param d - drawing surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return - boolean value - true/false
     * true if the animation should stop, or false otherwise.
     */
    boolean shouldStop();
}