package score;

import collidableshapes.Block;
import spritesandvelocity.Ball;
import removing.HitListener;

/**
 * @author noa benita
 * listener of the score in the game
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter - count the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Update the score according to the hit.
     *
     * @param beingHit block that is being hit
     * @param hitter   the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}