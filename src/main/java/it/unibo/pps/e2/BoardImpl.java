package it.unibo.pps.e2;

import java.util.Random;

public class BoardImpl implements Board {
    private final int size;
    private final Random random = new Random();

    public BoardImpl(final int size) {
        this.size = size;
    }

    @Override
    public boolean isPositionOutOfBoard(final Pair<Integer, Integer> position) {
        return (position.getX() < 0 || position.getY() < 0) ||
                (position.getX() >= this.size || position.getY() >= this.size);
    }


    @Override
    public Pair<Integer, Integer> randomPosition() {
        Pair<Integer, Integer> newPosition = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
        if (isPositionOutOfBoard(newPosition)) {
            return this.randomPosition();
        }
        return newPosition;
    }
}
