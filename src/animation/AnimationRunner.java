package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author noa benita
 * animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;
    private double dt;

    /**
     * @param gui - GUI.
     * @param framesPerSecond - number of frames in every second.
     */
    public AnimationRunner(GUI gui,  int framesPerSecond) {
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * @param animation - runs the animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeft = millisecondsPerFrame - usedTime;
            if (milliSecondLeft > 0) {
                this.sleeper.sleepFor(milliSecondLeft);
            }
        }
    }
}