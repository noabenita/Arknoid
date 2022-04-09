package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import spritesandvelocity.Sprite;
import spritesandvelocity.Velocity;
import biuoop.DrawSurface;
import collidableshapes.Block;
import geometricshapes.Point;
import geometricshapes.Rectangle;

/**
 * @author Noa Benita
 * The first level
 */
public class Level1 implements LevelInformation {
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
    public Level1() {
        levelName = "Direct Hit";
        background = new Block(new Rectangle(new Point(0, 0), WIDTH, LENGTH), java.awt.Color.black);
        numberOfBlocksToRemove = 1;
        numberOfBalls = 1;
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
        ballsVelocity.add(Velocity.fromAngleAndSpeed(0, 6));
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
        List<Block> blocksList = new LinkedList<Block>();
        Block blockI = new Block(new Rectangle(new Point(380, 150), 40, 40), java.awt.Color.RED);
        blocksList.add(blockI);
        return blocksList;
    }

    @Override
    public void drawBackground(DrawSurface d) {
        this.background.drawOn(d);
        Point p = new Point(400, 170);
        int pX = (int) p.getX();
        int pY = (int) p.getY();
        int radius = 40;
        d.drawLine(pX, pY - radius, pX, pY + radius);
        d.drawLine(pX - radius, pY, pX + radius, pY);
        d.setColor(Color.blue);
        d.drawCircle(pX, pY, radius);
        d.setColor(Color.blue);
        d.drawCircle(pX, pY, radius + 30);
        d.setColor(Color.blue);
        d.drawCircle(pX, pY, radius + 60);
        d.setColor(Color.blue);
        d.drawLine(270, 170, 530, 170);
        d.drawLine(400, 0, 400, 300);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}