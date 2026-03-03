package it.unibo.pps.e2;

public interface Pieces {

    Pair<Integer, Integer> getPosition();
    boolean moveAllowed(final int row, final int col);

    /**
     * @throws IllegalStateException if Position is not an Allowed Move
     */
    void setPosition(final Pair<Integer, Integer> position);

    boolean positionEquals(final Pair<Integer, Integer> position);
}
