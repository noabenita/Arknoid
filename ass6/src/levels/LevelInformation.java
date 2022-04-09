package levels;

import java.util.List;

import spritesandvelocity.Sprite;
import spritesandvelocity.Velocity;
import biuoop.DrawSurface;
import collidableshapes.Block;

/**
 * @author Noa Benita
 * The LevelInformation interface - in charge of holding the neccesery information about a level in the game
 */
public interface LevelInformation {

    /**
     * @return The balls number at the level.
     */
    int numberOfBalls();

    /**
     * @return a list with the balls velocities.
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return The paddle speed.
     */
    int paddleSpeed();

    /**
     * @return The paddle width.
     */
    int paddleWidth();

    /**
     * @return a string that represent the level name.
     * the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @param d - the drawing surface.
     *          draw the background of the level
     */
    void drawBackground(DrawSurface d);

    /**
     * @return blocks list.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();
}