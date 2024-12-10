package it.unibo.es2;

import java.util.Map;

public interface Logics {
    
    /**
     * 
     * @return size of the button matrix
     */
    Pair<Integer, Integer> getSize();

    /**
     * 
     * @return values to appear in button
     */
    Map<Pair<Integer, Integer>, String> getValues();

    /**
     * 
     * @param elem  button position
     * @return the new value a button should show after being pressed
     */
    String hit(Pair<Integer, Integer> elem);

    /**
     * 
     * @return wether it's time to quit
     */
    boolean quit();

}
