package it.unibo.pps.e2;

public class Knight extends PiecesImpl {

    public Knight(final Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public boolean moveAllowed(final int row, final int col) {
        int x = row - super.getPosition().getX();
        int y = col - super.getPosition().getY();
        return x!=0 && y!=0 && Math.abs(x) + Math.abs(y) == 3;
    }
}
