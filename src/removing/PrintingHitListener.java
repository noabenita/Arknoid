package removing;

import collidableshapes.Block;
import spritesandvelocity.Ball;

/**
 * @author noa benita
 * PrintingHitListener - a HitListener that prints a message to the screen whenever a block is hit.
 */
public class PrintingHitListener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}