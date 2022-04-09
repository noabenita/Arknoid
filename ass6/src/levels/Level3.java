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
 * The tird level
 */
public class Level3 implements LevelInformation {
    private static final int LENGTH = 600, WIDTH = 800;
    private String levelName;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private Sprite background;
    //private boolean change;
    //private int count;

    /**
     * Constructor.
     * holds the information about the level
     */
    public Level3() {
        levelName = "Green 3";
        background = new Block(new Rectangle(new Point(0, 0), WIDTH, LENGTH), Color.green.darker().darker());
        numberOfBlocksToRemove = 40;
        numberOfBalls = 2;
        paddleSpeed = 6;
        paddleWidth = 80;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(60, 6));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(25, -5));
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
        int firstY = 240, firstX = 725;
        int bWidth = 50, bHeight = 20;
        int i, j;
        for (i = 0, j = firstX; i < 10; i++, j -= bWidth) {
            Block b5 = new Block((new Rectangle(new Point(j, firstY - 4 * bHeight), bWidth, bHeight)), Color.yellow);
            blocksList.add(b5);
            if (i == 9) {
                continue;
            }
            Block b4 = new Block((new Rectangle(new Point(j, firstY - 3 * bHeight), bWidth, bHeight)), Color.green);
            blocksList.add(b4);
            if (i == 8) {
                continue;
            }
            Block b3 = new Block((new Rectangle(new Point(j, firstY - 2 * bHeight), bWidth, bHeight)), Color.pink);
            blocksList.add(b3);
            if (i == 7) {
                continue;
            }
            Block b2 = new Block((new Rectangle(new Point(j, firstY - bHeight), bWidth, bHeight)), Color.cyan);
            blocksList.add(b2);
            if (i == 6) {
                continue;
            }
            Block b1 = new Block((new Rectangle(new Point(j, firstY), bWidth, bHeight)), Color.lightGray);
            blocksList.add(b1);
        }
        return blocksList;
    }

    @Override
    public void drawBackground(DrawSurface d) {
        background.drawOn(d);
        d.setColor(Color.darkGray.darker());
        d.fillRectangle(70, 450, 120, 150);
        d.setColor(Color.WHITE);
        for (int i = 80; i < 170; i += 20) {
            for (int j = 460; j < 600; j += 30) {
                d.fillRectangle(i, j, 15, 25);
            }
        }
        d.setColor(Color.darkGray);
        d.fillRectangle(125, 200, 10, 250);
        d.fillRectangle(105, 400, 50, 50);
        d.setColor(Color.orange.brighter());
        d.fillCircle(130, 200, 10);
        d.setColor(Color.red.brighter());
        d.fillCircle(130, 200, 8);
        d.setColor(Color.white);
        d.fillCircle(130, 200, 3);
        d.setColor(Color.BLACK);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}