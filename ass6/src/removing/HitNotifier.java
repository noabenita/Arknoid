package removing;

/**
 * @author noa benita
 * the interface - HitNotifier.
 */
public interface HitNotifier {

    /**
     *  @param hl - HitListener
     *  Add hl as a listener to hit events.
     */
    void addHitListener(HitListener hl);

    /**
     *  @param hl - HitListener
     *  Remove hl from the list of listeners to hit events.
     */
    void removeHitListener(HitListener hl);
}
