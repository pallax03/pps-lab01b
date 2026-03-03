package it.unibo.pps.e2;

public interface Board {

    boolean isPositionOutOfBoard(final Pair<Integer, Integer> position);

    /**
     * @return true if the position was empty, otherwise false if the piece overlaps another one.
     */
    boolean placePieceInBoard(final Pieces piece);

    boolean isPositionEmpty(final Pair<Integer, Integer> position);

    Pair<Integer, Integer> randomEmptyPosition();
}
