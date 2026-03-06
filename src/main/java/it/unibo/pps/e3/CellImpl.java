package it.unibo.pps.e3;

import java.util.Optional;

public class CellImpl implements Cell {

    private final CellType type;
    private final int adjacentMines;
    private boolean isShowing = false;
    private boolean flagged = false;

    public CellImpl(final CellType type, final int adjacentMines) {
        this.type = type;
        this.adjacentMines = adjacentMines;
    }

    public CellImpl(final CellType type) {
        this(type, 0);
    }

    @Override
    public boolean isMine() {
        return this.type == CellType.MINE;
    }

    @Override
    public boolean isFlagged() {
        return this.flagged;
    }

    @Override
    public boolean isRevealed() {
        return this.isShowing;
    }

    @Override
    public void reveal() {
        if (!this.isFlagged()) {
            this.isShowing = true;
        }
    }

    @Override
    public void toggleFlag() {
        if (!this.isRevealed()) {
            this.flagged = !this.flagged;
        }
    }

    @Override
    public int getAdjacentMines() {
        if (this.isMine()) {
            throw new IllegalStateException();
        }
        return this.adjacentMines;
    }
}
