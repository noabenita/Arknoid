package gameclasses;

import spritesandvelocity.Sprite;
import spritesandvelocity.SpriteCollection;
import spritesandvelocity.Velocity;
import spritesandvelocity.Paddle;
import spritesandvelocity.Ball;
import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.CountdownAnimation;
import animation.PauseScreen;
import collidableshapes.Block;
import collidableshapes.Collidable;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import levels.LevelInformation;
import levels.LevelName;
import removing.BallRemover;
import removing.BlockRemover;
import score.Counter;
import score.ScoreIndicator;
import score.ScoreTrackingListener;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import biuoop.GUI;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author noa benita
 * the information of the game - the sprites, the enviroment, and the GUI
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation information;

    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    /**
     * Constructor.
     * initialized the class fields
     *
     * @param information - the level information
     * @param score       - the score.
     * @param gui         - GUI.
     */
    public GameLevel(LevelInformation information, Counter score, GUI gui) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.runner = new AnimationRunner(gui, 60);
        this.running = true;
        this.keyboard = gui.getKeyboardSensor();
        this.information = information;
        this.score = score;
    }

    /**
     * @param c - the colloidal object
     *          addCollidable -- add the object to the game
     */
    public void addCollidable(Collidable c) {
        this.environment.addColloidalble(c);
    }

    /**
     * @param s - the sprite object
     *          addSprite -- add the object to the game
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * create several blocks according to the instructions.
     */
    public void createBlocks() {
        this.blocksCounter = new Counter(information.numberOfBlocksToRemove());
        List<Block> blocks = this.information.blocks();

        for (int i = 0; i < information.numberOfBlocksToRemove(); i++) {
            blocks.get(i).addToGame(this);
        }
        // create the blocks in the boarders of the screen
        int blockH = 30, blockW = 30;
        Block up = new Block((new Rectangle(new Point(0, 0), WIDTH, 2 * blockH)), Color.gray);
        up.addToGame(this);
        Block left = new Block((new Rectangle(new Point(0, blockH), blockW, HEIGHT)), Color.gray);
        left.addToGame(this);
        Block right = new Block((new Rectangle(new Point(WIDTH - blockW, blockH), blockW, HEIGHT)), Color.gray);
        right.addToGame(this);
    }

    /**
     * create a paddle.
     */
    public void createPaddle() {
        int paddleHeight = 30;
        int x = (WIDTH - information.paddleWidth()) / 2;
        Rectangle rec = new Rectangle(new Point(x, HEIGHT - paddleHeight), information.paddleWidth(), paddleHeight);
        Paddle paddle = new Paddle(rec, Color.orange, gui.getKeyboardSensor());
        paddle.setSpeed(information.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * create 2 balls.
     */
    public void createBall() {
        int paddleHeight = 30;
        int ballR = 6;
        Ball[] balls = new Ball[information.numberOfBalls()];
        Velocity[] velocity = new Velocity[information.numberOfBalls()];
        for (int i = 0; i < information.numberOfBalls(); i++) {
            balls[i] = new Ball(800 / 2, HEIGHT - paddleHeight - ballR, ballR, Color.white);
            velocity[i] = information.initialBallVelocities().get(i);
            balls[i].setVelocity(velocity[i]);
            balls[i].addToGame(this);
            balls[i].setEnvironment(environment);
        }
        this.ballsCounter = new Counter(information.numberOfBalls());
    }

    /**
     * Initialize a new game: create the Blocks and Balls (and Paddle)
     * and add them to the game.
     * also, create the blue screen according to the instructions.
     */
    public void initialize() {
        createBlocks();
        List<Collidable> col = new ArrayList<>(this.environment.getCollisions());
        Counter count = new Counter(col.size() - 3); //not including bounds
        this.blocksCounter = count;
        BlockRemover blockRemove = new BlockRemover(this, count);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        for (Collidable hl : col) {
            if (!hl.getColor().equals(Color.gray)) {
                ((Block) hl).addHitListener(blockRemove);
                ((Block) hl).addHitListener(scoreListener);
            }
        }
        createLevelName();
        createScoreIndicator();
        createBall();
        Point deathPoint = new Point(30, HEIGHT + 10);
        Rectangle death = new Rectangle(deathPoint, 740, 30);
        Block deathBlock = new Block(death, Color.black);
        deathBlock.addToGame(this);
        BallRemover removeBalls = new BallRemover(this, this.ballsCounter);
        deathBlock.addHitListener(removeBalls);
        createPaddle();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, information));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * @param c - the Collidabl
     *          remove the Collidabl.
     */
    public void removeCollidable(Collidable c) {
        this.environment.deleteCollidable(c);
    }

    /**
     * @param s - the Sprite
     *          remove the Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.deleteSprite(s);
    }

    /**
     * decrease ball counter.
     */
    public void countBalls() {
        this.ballsCounter.decrease(1);
    }

    /**
     * create Score Indicator.
     */
    public void createScoreIndicator() {
        Sprite scoreIndicator = new ScoreIndicator(this.score);
        this.sprites.addSprite(scoreIndicator);
    }

    /**
     * create the level name.
     */
    public void createLevelName() {
        Sprite levelName = new LevelName(this.information.levelName());
        this.sprites.addSprite(levelName);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (blocksCounter.getValue() == 0 || ballsCounter.getValue() == 0) {
            if (this.blocksCounter.getValue() == 0) {
                this.score.increase(100);
            }
            this.running = false;
        }
        information.drawBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            PauseScreen pauseScreen = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", pauseScreen));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return the number of balls in the game.
     */
    public int ballsNum() {
        return ballsCounter.getValue();
    }

    /**
     * @return the number of blocks in the game.
     */
    public int blocksNum() {
        return blocksCounter.getValue();
    }
}