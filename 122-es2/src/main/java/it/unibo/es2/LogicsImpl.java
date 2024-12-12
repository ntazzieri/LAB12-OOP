package it.unibo.es2;

import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Collections;

public class LogicsImpl implements Logics {

    private final List<List<String>> values;

    public LogicsImpl(final int size) {
		if (size < 0) {
            throw new IllegalArgumentException("The size can't be less than 0");
        }
        values = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            values.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                values.get(i).add(" ");
            }
        }
	}

	@Override
    public int getSize() {
        return values.size();
    }

    @Override
    public List<List<String>> getValues() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public String hit(final Pair<Integer, Integer> pos) {
        values.get(pos.getY()).set(pos.getX(), values.get(pos.getY()).get(pos.getX()).equals(" ") ? "*" : " ");
        return values.get(pos.getY()).get(pos.getX());
    }

    @Override
    public boolean quit() {
        //rowWithStarExistsWithStreams();
        return values.stream().anyMatch(l -> l.stream().allMatch(s -> s.equals("*"))) || rowWithStarExists();
    }
    
    private boolean rowWithStarExists() {
        boolean exists = true;
        for (int i = 0; i < values.size(); i++) {
            exists = true;
            for (int j = 0; j < values.size(); j++) {
                if(values.get(j).get(i).equals(" ")) {
                    exists = false;
                }
            }
            if (exists) {
                return true;
            }
        }
        return exists;
    }

    /* private void rowWithStarExistsWithStreams() {
         System.out.println(values.stream().filter(l -> l.contains("*"))
            .flatMap(List::stream).toList());
    } */
}
