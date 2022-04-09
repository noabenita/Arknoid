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
 * The forth level
 */
public class Level4 implements LevelInformation {
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
    public Level4() {
        levelName = "Final Four";
        background = new Block(new Rectangle(new Point(0, 0), WIDTH, LENGTH), new Color(51, 153, 255));
        numberOfBlocksToRemove = 105;
        numberOfBalls = 3;
        paddleSpeed = 7;
        paddleWidth = 80;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        int angel = 40;
        for (int i = 0; i < this.numberOfBalls; i++) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(angel, 5));
            angel -= 40;
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
        int firstY = 240, firstX = 725;
        int bWidth = 50, bHeight = 20;
        int i, j;
        for (i = 0, j = firstX; i < 16; i++, j -= bWidth) {
            Block b7 = new Block(new Rectangle(new Point(j, firstY - 6 * bHeight), bWidth, bHeight), Color.green);
            blocksList.add(b7);
            if (i == 15) {
                continue;
            }
            Block b6 = new Block(new Rectangle(new Point(j, firstY - 5 * bHeight), bWidth, bHeight), Color.yellow);
            blocksList.add(b6);
            Block b5 = new Block(new Rectangle(new Point(j, firstY - 4 * bHeight), bWidth, bHeight), Color.orange);
            blocksList.add(b5);
            Block b4 = new Block(new Rectangle(new Point(j, firstY - 3 * bHeight), bWidth, bHeight),
                    Color.red.brighter());
            blocksList.add(b4);
            Block b3 = new Block((new Rectangle(new Point(j, firstY - 2 * bHeight), bWidth, bHeight)),
                    Color.pink.brighter());
            blocksList.add(b3);
            Block b2 = new Block((new Rectangle(new Point(j, firstY - bHeight), bWidth, bHeight)), Color.pink);
            blocksList.add(b2);
            Block b1 = new Block((new Rectangle(new Point(j, firstY), bWidth, bHeight)), Color.cyan);
            blocksList.add(b1);
        }
        return blocksList;
    }

    @Override
    public void drawBackground(DrawSurface d) {
        // drawing clouds and rain
        background.drawOn(d);
        Color color1 = new Color(190, 230, 250);
        Color color2 = new Color(180, 220, 240);
        Color color3 = new Color(200, 238, 248);
        Color color4 = new Color(195, 225, 250);

        d.setColor(Color.white);
        d.drawLine(670, 330, 720, 600);
        d.drawLine(660, 340, 710, 600);
        d.drawLine(650, 350, 700, 600);
        d.drawLine(640, 350, 690, 600);
        d.drawLine(630, 350, 680, 600);
        d.drawLine(620, 350, 670, 600);

        d.drawLine(480, 430, 500, 600);
        d.drawLine(470, 440, 490, 600);
        d.drawLine(460, 450, 480, 600);
        d.drawLine(450, 450, 470, 600);
        d.drawLine(440, 460, 460, 600);

        d.drawLine(180, 430, 200, 600);
        d.drawLine(170, 440, 190, 600);
        d.drawLine(160, 450, 180, 600);
        d.drawLine(150, 450, 170, 600);
        d.drawLine(140, 450, 160, 600);
        d.drawLine(130, 460, 150, 600);

        d.setColor(color1);
        d.fillCircle(670, 300, 35);
        d.setColor(color2);
        d.fillCircle(635, 320, 40);
        d.setColor(color3);
        d.fillCircle(630, 290, 35);
        d.setColor(color4);
        d.fillCircle(590, 310, 30);

        d.setColor(color1);
        d.fillCircle(470, 400, 35);
        d.setColor(color2);
        d.fillCircle(435, 420, 40);
        d.setColor(color3);
        d.fillCircle(430, 390, 35);
        d.setColor(color4);
        d.fillCircle(490, 410, 38);

        d.setColor(color1);
        d.fillCircle(170, 400, 35);
        d.setColor(color2);
        d.fillCircle(135, 420, 40);
        d.setColor(color3);
        d.fillCircle(130, 390, 35);
        d.setColor(color4);
        d.fillCircle(190, 410, 40);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}