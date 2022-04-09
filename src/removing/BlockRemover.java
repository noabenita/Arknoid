package removing;

import collidableshapes.Block;
import spritesandvelocity.Ball;
import score.Counter;
import gameclasses.GameLevel;

/**
 * @author Noa Benita
 * The type BlockRemover - BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game - the game
     * @param removedBlocks - the counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

   @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}