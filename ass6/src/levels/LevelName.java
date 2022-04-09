package levels;

import spritesandvelocity.Sprite;
import biuoop.DrawSurface;

/**
 * @author Noa Benita
 * The type LevelName - the name of each level
 */
public class LevelName implements Sprite {
    private String name;

    /**
     * Constructor.
     *
     * @param name         - the game name
     */
    public LevelName(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.white);
        d.fillRectangle(0, 0, 800, 30);
        d.setColor(java.awt.Color.black);
        d.drawText(570, (20), "Level Name:" + this.name, 20);
    }

    @Override
    public void timePassed() {
    }
}
