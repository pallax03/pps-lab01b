package it.unibo.pps.e2;

@FunctionalInterface
public interface MoveStrategy {

    boolean moveAllowed(Pair<Integer, Integer> from, Pair<Integer, Integer> to);

    static MoveStrategy knight() {
        return (from, to) -> {
            int x = from.getX() - to.getX();
            int y = from.getY() - to.getY();
            return x!=0 && y!=0 && Math.abs(x) + Math.abs(y) == 3;
        };
    }

    static MoveStrategy pawn() {
        return (from, to) -> false;
    }
}
