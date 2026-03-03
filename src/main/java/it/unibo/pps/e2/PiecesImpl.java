package it.unibo.pps.e2;

public abstract class PiecesImpl implements Pieces {
    protected Pair<Integer, Integer> position;

    public PiecesImpl(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public abstract boolean moveAllowed(final int row, final int col);

    @Override
    public void setPosition(final Pair<Integer, Integer> position) {
        if (!this.moveAllowed(position.getX(), position.getY())) {
            throw new IllegalStateException();
        }
        this.position = position;
    }

    @Override
    public boolean positionEquals(final Pair<Integer, Integer> position) {
        return this.getPosition().getX().equals(position.getX()) &&
                this.getPosition().getY().equals(position.getY());
    }
}
