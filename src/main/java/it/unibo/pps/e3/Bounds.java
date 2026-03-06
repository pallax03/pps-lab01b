package it.unibo.pps.e3;

import java.util.Set;

public interface Bounds {
    boolean isPositionOutOfBounds(Pair<Integer, Integer> position);
    int getSize();
    Pair<Integer, Integer> randomPosition();
    Set<Pair<Integer, Integer>> getAdjacentPositions(Pair<Integer, Integer> startingPosition);
}
