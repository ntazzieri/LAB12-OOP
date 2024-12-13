package it.unibo.es3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogicsImpl implements Logics {

    private static final int DELTA = 1;
    private static final int RANDOM_ITEMS = 3;
    final private Map<Pair<Integer, Integer>, String> values = new HashMap<>();

    public LogicsImpl(int width) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                values.put(new Pair<Integer,Integer>(i, j), " ");
            } 
        }
        Random r = new Random();
        for (int i = 0; i < RANDOM_ITEMS; i++) {
            values.put(
                new Pair<Integer, Integer>(r.nextInt(0, width), r.nextInt(0, width)), "*");
        }
    }

    @Override
    public void hitNext() {
        values.entrySet().stream()
        .filter(e -> e.getValue().equals("*"))
        .flatMap(e1 -> values.entrySet().stream()
            .filter(e2 -> Math.abs(e1.getKey().getY() - e2.getKey().getY()) <= DELTA
            && Math.abs(e1.getKey().getX() - e2.getKey().getX()) <= DELTA)).toList()
        .forEach(l -> l.setValue("*"));
    }

    @Override
    public boolean quit() {
        return values.entrySet().stream().allMatch(e -> e.getValue().equals("*"));
    }

    @Override
    public String getCell(Pair<Integer, Integer> pos) {
        return values.get(pos);
    }

}
