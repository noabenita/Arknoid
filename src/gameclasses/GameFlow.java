package gameclasses;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import score.Counter;

import java.util.List;

/**
 * @author noa benita
 * the gameFlow class - creats the different levels and move from one to the other
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private Counter score = new Counter(0);
    private boolean win = true;
    private GUI gui;

    /**
     * Constructor.
     * @param ar  - AnimationRunner.
     * @param ks  - KeyboardSensor.
     * @param gui - GUI.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.runner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
    }

    /**
     * moving from one level to the next.
     * @param levels - the list with the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.score, this.gui);
            level.initialize();
            while (level.ballsNum() != 0 && level.blocksNum() != 0) {
                level.run();
            }
            if (level.ballsNum() == 0) {
                this.win = false;
                break;
            }
        }
        EndScreen endScreen = new EndScreen(this.score, this.keyboardSensor, this.win);
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", endScreen));
    }
}