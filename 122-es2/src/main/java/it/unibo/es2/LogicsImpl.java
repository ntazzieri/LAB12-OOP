package it.unibo.es2;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LogicsImpl implements Logics {

    private final Map<Pair<Integer, Integer>, String> values;

    public LogicsImpl(final Pair<Integer, Integer> size) {
        if (size.getX() < 0 || size.getY() < 0) {
            throw new IllegalArgumentException("The size of x or y is less than 0");
        }
        values = new HashMap<>();
        for (int i = 0; i < size.getX(); i++) {
            for (int j = 0; j < size.getY(); j++) {
                values.put(new Pair<Integer,Integer>(i, j), " ");
            }
        }
    }
    @Override
    public Pair<Integer, Integer> getSize() {
        return values.keySet().stream()
            .max((k1, k2) -> Integer.compare(k1.getX() + k1.getY(), k2.getX() + k2.getY()))
            .orElse(new Pair<Integer,Integer>(0, 0));
    }

    @Override
    public Map<Pair<Integer, Integer>, String> getValues() {
        return Collections.unmodifiableMap(values);
    }

    @Override
    public String hit(Pair<Integer, Integer> elem) {
        values.put(elem, values.get(elem).equals(" ") ? "*" : " "); 
        return values.get(elem);
    }

    @Override
    public boolean quit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quit'");
    }
    
}
