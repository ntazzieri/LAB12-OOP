package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

	private final List<Integer> values;

	public LogicsImpl(final int size) {
		if (size < 0) {
			throw new IllegalArgumentException("The size is lower than 0");
		}
		values = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			values.add(0);
		}
	}

	@Override
	public int size() {
		return values.size();
	}

	@Override
	public List<Integer> values() {
		return List.copyOf(values);
	}

	@Override
	public List<Boolean> enablings() {
		return values.stream()
			.map(v -> v < size())
			.toList();
	}

	@Override
	public int hit(final int elem) {
		final Integer oldValue = values.remove(elem);
		values.add(elem, oldValue + 1);
		return values.get(elem);
	}

	@Override
	public String result() {
		return values.stream()
			.map(v -> Integer.toString(v))
			.collect(Collectors.joining("|", "<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		return values.stream().allMatch(v -> v == values.getFirst() && v != 0);
	}
}
