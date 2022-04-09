package score;

import spritesandvelocity.Sprite;
import biuoop.DrawSurface;

/**
 * @author noa benita
 * holds a reference to the score counter, presented at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreOfGame;

    /**
     * Constructor.
     *
     * @param score score
     */
    public ScoreIndicator(Counter score) {
        this.scoreOfGame = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.white);
        d.setColor(java.awt.Color.black);
        d.drawText((730 / 2), (20), "Score:" + (Integer) (this.scoreOfGame.getValue()), 20);
    }

    @Override
    public void timePassed() {
    }
}