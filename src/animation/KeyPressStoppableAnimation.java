package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author noa benita
 * KeyPressStoppableAnimation class - in charg of what to do in case of pressed key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean stop;
    private boolean isAlreadyPressed;
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;

    /**
     * Constructor.
     *
     * @param sensor    - KeyboardSensor.
     * @param key       - String - the key pressed in the keyboard.
     * @param animation - Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.stop = false;
        this.isAlreadyPressed = true;
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key)) {
            if (this.isAlreadyPressed) {
                this.isAlreadyPressed = false;
            } else {
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}