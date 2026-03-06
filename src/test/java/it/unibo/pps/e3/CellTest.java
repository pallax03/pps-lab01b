package it.unibo.pps.e3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    private Cell cell;

    private void initMine() {
        this.cell = new CellImpl(CellType.MINE);
    }

    private void initEmptyCell() {
        this.cell = new CellImpl(CellType.SAFE);
    }

    @Test
    public void cellIsAMine() {
        initMine();
        assertTrue(this.cell.isMine());
    }

    @Test
    public void cellIsSafeWithNoAdjacentMines() {
        initEmptyCell();
        assertFalse(this.cell.isMine());
        assertEquals(0, this.cell.getAdjacentMines());
    }

    @Test
    public void mineCannotHaveAdjacentMines() {
        initMine();
        assertThrows(IllegalStateException.class, () -> this.cell.getAdjacentMines());
    }

    @Test
    public void revealIsIdempotent() {
        initEmptyCell();
        this.cell.reveal();
        this.cell.reveal();
        assertTrue(this.cell.isRevealed());
    }

    @Test
    public void cellToggleFlag() {
        initEmptyCell();
        this.cell.toggleFlag();
        assertTrue(this.cell.isFlagged());
    }

    @Test
    public void cellCannotRevealedIfFlagged() {
        initEmptyCell();
        this.cell.toggleFlag();
        this.cell.reveal();
        assertFalse(this.cell.isRevealed());
    }

    @Test
    public void cellCannotFlaggedIfRevealed() {
        initEmptyCell();
        this.cell.reveal();
        this.cell.toggleFlag();
        assertFalse(this.cell.isFlagged());
    }
}
