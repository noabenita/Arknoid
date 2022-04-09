package score;

/**
 * @author noa benita
 * a class for counting
 */
public class Counter {
    private int count;

    /**
     * Constructor.
     *
     * @param num - count of something in the game
     */
    public Counter(int num) {
        this.count = num;
    }

    /**
     * @param number add number to current count.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * @param number subtract number from current count.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * @return the value
     * get current count.
     */
    public int getValue() {
        return this.count;
    }
}