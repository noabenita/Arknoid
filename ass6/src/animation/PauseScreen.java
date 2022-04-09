package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author noa benita
 * pause screen in case of pressing the key "p".
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k  - KeyboardSensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused! - press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}