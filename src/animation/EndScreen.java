package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import score.Counter;

import java.awt.Polygon;
import java.awt.Color;


/**
 * @author noa benita
 * end screen in case of winning or losing.
 */
public class EndScreen implements Animation {
    private Counter score;
    private boolean win;
    private KeyboardSensor keyboard;
    private boolean spaceIsPressed;

    /**
     * Constructor.
     *
     * @param score - the current score.
     * @param k     - keyboard sensor.
     * @param win   - did the game end with a winning or losing.
     */
    public EndScreen(Counter score, KeyboardSensor k, boolean win) {
        this.score = score;
        this.keyboard = k;
        this.win = win;
        this.spaceIsPressed = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.win) {
            d.drawText(d.getWidth() / 8, d.getHeight() / 2, "Game Over. Your score is "
                    + this.score.getValue(), 50);
        } else {
            d.drawText(d.getWidth() / 8, d.getHeight() / 2, "You Win! Your score is "
                    + this.score.getValue(), 50);
            // drawing a crown
            Color color1 = new Color(255, 235, 58);
            d.setColor(color1);
            int[] x = {250, 230, 400};
            int[] y = {350, 550, 550};
            Polygon p = new Polygon(x, y, 3);
            d.fillPolygon(p);
            int[] x2 = {400, 300, 500};
            int[] y2 = {350, 550, 550};
            Polygon p2 = new Polygon(x2, y2, 3);
            d.fillPolygon(p2);
            int[] x3 = {550, 400, 570};
            int[] y3 = {350, 550, 550};
            Polygon p3 = new Polygon(x3, y3, 3);
            d.fillPolygon(p3);
            Color color2 = new Color(255, 213, 0, 255);
            d.setColor(color2);
            d.fillCircle(250, 350, 10);
            d.fillCircle(400, 350, 10);
            d.fillCircle(550, 350, 10);
            d.setColor(Color.black);
            d.fillCircle(250, 350, 5);
            d.fillCircle(400, 350, 5);
            d.fillCircle(550, 350, 5);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.spaceIsPressed = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
