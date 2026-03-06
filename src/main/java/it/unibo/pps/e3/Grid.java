package it.unibo.pps.e3;

import java.util.Set;

public interface Grid {

    int numberOfAdjacentMines(final int row, final int col);

    void revealCell(final Pair<Integer, Integer> cellPosition);

    /**
     * @throws IndexOutOfBoundsException if the cell not exist.
     */
    Cell getCell(final int row, final int col);

    int getTotalRevealed();

    int getTotalMines();

    Set<Pair<Integer, Integer>> getMines();
}
