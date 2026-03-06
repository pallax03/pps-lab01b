package it.unibo.pps.e3;

public interface Logics {

    boolean hit(final int row, final int col);

    void toggle(final int row, final int col);

    CellOutput get(final int row, final int col);

    boolean checkVictory();

    void showAll();
}
