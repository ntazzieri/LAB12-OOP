package it.unibo.es3;

public interface Logics {

    void hitNext();

    String getCell(Pair<Integer, Integer> pos);

    boolean quit();
}