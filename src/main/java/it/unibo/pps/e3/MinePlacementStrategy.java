package it.unibo.pps.e3;

import java.util.HashSet;
import java.util.Set;

@FunctionalInterface
public interface MinePlacementStrategy {
    Set<Pair<Integer, Integer>> generateMines(final Bounds bounds);

    static MinePlacementStrategy random() {
        return (bounds) -> {
            Set<Pair<Integer, Integer>> mines = new HashSet<>();
            for (int i = 0; i < bounds.getSize(); i++) {
                Pair<Integer, Integer> newPosition = bounds.randomPosition();
                if (!mines.add(newPosition)) {
                    i--;
                }
            }
            return mines;
        };
    }

    static MinePlacementStrategy fixed(Set<Pair<Integer, Integer>> minesPosition) {
        return (bounds) -> {
            for (Pair<Integer, Integer> minePosition : minesPosition) {
                if (bounds.isPositionOutOfBounds(minePosition)) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return minesPosition;
        };
    }
}
