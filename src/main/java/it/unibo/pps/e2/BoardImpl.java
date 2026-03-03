package it.unibo.pps.e2;

import java.util.*;

public class BoardImpl implements Board {
    private final int size;
    private final Set<Pieces> piecesInBoard;
    private final Random random = new Random();

    public BoardImpl(final int size) {
        this.size = size;
        this.piecesInBoard = new HashSet<>();
    }

    @Override
    public boolean isPositionOutOfBoard(final Pair<Integer, Integer> position) {
        return (position.getX() < 0 || position.getY() < 0) ||
                (position.getX() >= this.size || position.getY() >= this.size);
    }

    @Override
    public boolean placePieceInBoard(final Pieces piece) {
        if (this.isPositionOutOfBoard(piece.getPosition())) {
            throw new IndexOutOfBoundsException();
        }
        return piecesInBoard.add(piece);
    }

    @Override
    public boolean isPositionEmpty(final Pair<Integer, Integer> position) {
        return this.piecesInBoard.stream().map(Pieces::getPosition).noneMatch(position::equals);
    }

    @Override
    public Pair<Integer, Integer> randomEmptyPosition() {
        Pair<Integer, Integer> newPosition = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
        if (!isPositionEmpty(newPosition) || isPositionOutOfBoard(newPosition)) {
            return this.randomEmptyPosition();
        }
        return newPosition;
    }
}
