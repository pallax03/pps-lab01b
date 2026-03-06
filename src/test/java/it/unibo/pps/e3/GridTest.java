package it.unibo.pps.e3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    private final int defaultSize = 5;
    private Bounds bounds;
    private Grid grid;

    private void initFixedGrid() {
        this.bounds = new BoundsImpl(this.defaultSize);
        this.grid = new GridBuilder()
                .withBounds(bounds)
                .withStrategy(MinePlacementStrategy.random())
                .build();
    }

    @Test
    public void getOutOfBoundsCell() {
        initFixedGrid();
        Pair<Integer, Integer> cellOutOfBounds = new Pair<>(this.defaultSize, -2);
        assertThrows(IndexOutOfBoundsException.class, () -> this.grid.getCell(cellOutOfBounds.getX(), cellOutOfBounds.getY()));
    }

    @Test
    public void checkTotalCellsRevealed() {
        initFixedGrid();
        int totalGridCells = this.defaultSize*this.defaultSize;
        for (int i = 0; i < this.defaultSize; i++) {
            for (int j = 0; j < this.defaultSize; j++) {
                this.grid.revealCell(new Pair<>(i, j));
            }
        }
        assertEquals(totalGridCells, this.grid.getTotalRevealed());
    }
}
