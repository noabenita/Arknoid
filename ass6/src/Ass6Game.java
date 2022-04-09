import animation.AnimationRunner;
import biuoop.GUI;
import gameclasses.GameFlow;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author noa benita
 */
public class Ass6Game {
    /**
     * @param args main function to create a game object, initializes and runs it.
     */
    public static void main(String[] args) {
        int numOfLevels = 4;
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation[] nameLevels = new LevelInformation[4];

        nameLevels[0] = new Level1();
        nameLevels[1] = new Level2();
        nameLevels[2] = new Level3();
        nameLevels[3] = new Level4();

        if (args.length == 0) {
            levels.addAll(Arrays.asList(nameLevels).subList(0, numOfLevels));
        } else {
            for (String arg : args) {
                if (arg.equals("1")) {
                    levels.add(new Level1());
                }
                if (arg.equals("2")) {
                    levels.add(new Level2());
                }
                if (arg.equals("3")) {
                    levels.add(new Level3());
                }
                if (arg.equals("4")) {
                    levels.add(new Level4());
                }
            }
            if (levels.isEmpty()) {
                levels.addAll(Arrays.asList(nameLevels).subList(0, numOfLevels));
            }
        }

        GUI gui = new GUI("game", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 50);
        GameFlow gameflow = new GameFlow(runner, gui.getKeyboardSensor(), gui);
        gameflow.runLevels(levels);
        System.exit(0);
    }
}
