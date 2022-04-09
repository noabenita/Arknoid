package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import spritesandvelocity.Sprite;
import spritesandvelocity.Velocity;
import biuoop.DrawSurface;
import collidableshapes.Block;
import geometricshapes.Point;
import geometricshapes.Rectangle;

/**
 * @author Noa Benita
 * The second level
 */
public class Level2 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800;
    private String levelName;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private Sprite background;

    /**
     * Constructor.
     * holds the information about the level
     */
    public Level2() {
        levelName = "Wide Easy";
        background = new Block(new Rectangle(new Point(0, 0), WIDTH, LENGTH), Color.white);
        numberOfBlocksToRemove = 15;
        numberOfBalls = 10;
        paddleSpeed = 6;
        paddleWidth = (int) (WIDTH / 1.5);
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        int angel = 60, i;
        for (i = 0; i < this.numberOfBalls; i++) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(angel, 6));
            angel -= 13;
        }
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        int rowHeight = 250, firstX = 725;
        int bWidth = 50, bHeight = 25;
        int i, j;
        for (i = 0, j = firstX; i < 2; i++, j -= bWidth) {
            Block b = new Block((new Rectangle(new Point(j, rowHeight), bWidth, bHeight)), Color.cyan);
            blocksList.add(b);
        }
        for (i = 0, j = firstX - 2 * bWidth; i < 2; i++, j -= bWidth) {
            Block b = new Block((new Rectangle(new Point(j, rowHeight), bWidth, bHeight)), Color.pink);
            blocksList.add(b);
        }
        for (i = 0, j = firstX - 4 * bWidth; i < 2; i++, j -= bWidth) {
            Block b = new Block((new Rectangle(new Point(j, rowHeight), bWidth, bHeight)), Color.blue);
            blocksList.add(b);
        }
        for (i = 0, j = firstX - 6 * bWidth; i < 3; i++, j -= bWidth) {
            Block b = new Block((new Rectangle(new Point(j, rowHeight), bWidth, bHeight)), Color.green);
            blocksList.add(b);
        }
        for (i = 0, j = firstX - 9 * bWidth; i < 2; i++, j -= bWidth) {
            Block b = new Block((new Rectangle(new Point(j, rowHeight), bWidth, bHeight)), Color.yellow);
            blocksList.add(b);
        }
        for (i = 0, j = firstX - 11 * bWidth; i < 2; i++, j -= bWidth) {
            Block b = new Block((new Rectangle(new Point(j, rowHeight), bWidth, bHeight)), Color.orange);
            blocksList.add(b);
        }
        for (i = 0, j = firstX - 13 * bWidth; i < 2; i++, j -= bWidth) {
            Block b = new Block((new Rectangle(new Point(j, rowHeight), bWidth, bHeight)), Color.red);
            blocksList.add(b);
        }
        return blocksList;
    }

    @Override
    public void drawBackground(DrawSurface d) {
        background.drawOn(d);
        int j = 0;
        Color color = new Color(255, 250, 180);
        d.setColor(color);
        for (int i = 25; i < 100; i++) {
            d.drawLine(100, 150, j, 250);
            j += 12;
        }
        d.fillCircle(100, 150, 60);

        color = new Color(255, 250, 120);
        d.setColor(color);
        d.fillCircle(100, 150, 50);

        color = new Color(250, 250, 20);
        d.setColor(color);
        d.fillCircle(100, 150, 40);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}