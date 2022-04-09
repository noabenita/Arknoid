package removing;

import collidableshapes.Block;
import spritesandvelocity.Ball;
import score.Counter;
import gameclasses.GameLevel;

/**
 * @author Noa Benita
 * The type BallRemover - BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game         - the game
     * @param removedBalls - the counter
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getUpperLeft().getY() >= 570) {
            hitter.removeFromGame(this.game);
            this.game.countBalls();
        }
    }
}