package it.unibo.pps.e3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class BoundsImpl implements Bounds {

    private int size;
    private Random random = new Random();

    public BoundsImpl(final int size) {
        this.size = size;
    }

    @Override
    public boolean isPositionOutOfBounds(Pair<Integer, Integer> position) {
        return position.getX() < 0 || position.getX() >= this.size ||
                position.getY() < 0 || position.getY() >= this.size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Pair<Integer, Integer> randomPosition() {
        return new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
    }

    @Override
    public Set<Pair<Integer, Integer>> getAdjacentPositions(Pair<Integer, Integer> startingPosition) {
        Set<Pair<Integer, Integer>> adjacentPositions = new HashSet<>();
        int from = -1;
        int to = 2;
        for (int i = from; i < to; i++) {
            for (int j = from; j < to; j++) {
                Pair<Integer, Integer> adjacentPosition =  new Pair<>(startingPosition.getX() + i, startingPosition.getY() + j);
                if (!adjacentPosition.equals(startingPosition) && !this.isPositionOutOfBounds(adjacentPosition)) {
                    adjacentPositions.add(adjacentPosition);
                }
            }
        }
        return adjacentPositions;
    }
}
