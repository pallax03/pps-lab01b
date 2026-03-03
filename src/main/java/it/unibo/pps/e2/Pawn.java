package it.unibo.pps.e2;

public class Pawn extends PiecesImpl {

    public Pawn(final Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public boolean moveAllowed(final int row, final int col) {
        return false;
    }

}
