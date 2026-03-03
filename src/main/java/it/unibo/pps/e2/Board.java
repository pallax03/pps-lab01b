package it.unibo.pps.e2;

public interface Board {

    boolean isPositionOutOfBoard(final Pair<Integer, Integer> position);

    Pair<Integer, Integer> randomPosition();
}
