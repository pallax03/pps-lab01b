package it.unibo.pps.e3;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GridImpl implements Grid {

    private final Bounds bounds;
    private final Cell[][] grid;
    private int totalRevealed = 0;

    public GridImpl(final Bounds bounds, final Cell[][] cellsGrid) {
        this.bounds = bounds;
        this.grid = cellsGrid;
    }

    private Set<Cell> getAdjacentCells(final int row, final int col) {
        return this.bounds.getAdjacentPositions(new Pair<>(row, col)).stream()
                .map((p) -> this.getCell(p.getX(), p.getY()))
                .collect(Collectors.toSet());
    }

    @Override
    public int numberOfAdjacentMines(final int row, final int col) {
        return this.getAdjacentCells(row, col).stream()
                .mapToInt((c) -> c.isMine() ? 1 : 0)
                .sum();
    }

    @Override
    public void revealCell(final Pair<Integer, Integer> cellPosition) {
        Cell cell = this.getCell(cellPosition.getX(), cellPosition.getY());
        if (cell.isRevealed()) {
            return;
        }
        cell.reveal();
        this.totalRevealed++;
    }

    @Override
    public Cell getCell(final int row, final int col) {
        return this.grid[row][col];
    }

    @Override
    public int getTotalRevealed() {
        return this.totalRevealed;
    }

    @Override
    public int getTotalMines() {
        return this.getMines().size();
    }

    @Override
    public Set<Pair<Integer, Integer>> getMines() {
        Set<Pair<Integer, Integer>> mines = new HashSet<>();
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid.length; j++) {
                if (this.getCell(i, j).isMine()) {
                    mines.add(new Pair<>(i, j));
                }
            }
        }
        return mines;
    }
}
