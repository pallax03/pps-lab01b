package it.unibo.pps.e3;

public interface Cell {
    boolean isRevealed();

    void reveal();

    boolean isMine();

    boolean isFlagged();

    void toggleFlag();

    int getAdjacentMines();
}
