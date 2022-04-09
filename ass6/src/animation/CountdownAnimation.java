package animation;

import spritesandvelocity.SpriteCollection;
import levels.LevelInformation;
import score.Counter;
import biuoop.DrawSurface;
import java.awt.Color;
import biuoop.Sleeper;

/**
 * @author noa benita
 * count down from 3 to 0.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private Counter countFrom;
    private SpriteCollection gameScreen;
    private LevelInformation information;

    /**
     * Constructor.
     *
     * @param numOfSeconds - seconds.
     * @param countFrom - count down from the number to 0.
     * @param gameScreen - display the count on the screen.
     * @param info - information of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, LevelInformation info) {
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds * 500;
        this.countFrom = new Counter(countFrom);
        this.information = info;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int startCount = 3;
        information.drawBackground(d);
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.YELLOW.brighter());
        // draw the text in the middle of the screen
        d.drawText(370, 350, String.valueOf(this.countFrom.getValue()), 100);
        if (countFrom.getValue() != startCount) {
            sleeper.sleepFor((long) numOfSeconds);
        }
        this.countFrom.decrease(1);
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom.getValue() == -1;
    }
}