package it.unibo.pps.e3;

import java.util.Set;

public class GridBuilder {

    private Bounds bounds;
    private Set<Pair<Integer, Integer>> mines;
    private Cell[][] grid;

    public GridBuilder withBounds(final Bounds bounds) {
        this.bounds = bounds;
        this.grid = new Cell[this.bounds.getSize()][this.bounds.getSize()];
        return this;
    }

    public GridBuilder withStrategy(final MinePlacementStrategy strategy) {
        this.mines = strategy.generateMines(this.bounds);
        return this;
    }

    private void initGrid() {
        for (int i = 0; i < this.bounds.getSize(); i++) {
            for (int j = 0; j <this.bounds.getSize(); j++) {
                this.grid[i][j] = new CellImpl(CellType.SAFE);
            }
        }
    }

    private void placeMine(final Pair<Integer, Integer> position) {
        this.grid[position.getX()][position.getY()] = new CellImpl(CellType.MINE);
    }

    private void placeMines() {
        this.mines.forEach(this::placeMine);
    }

    private int countNumberOfMines(Set<Pair<Integer, Integer>> cells) {
        return cells.stream().filter((p) -> this.mines.stream().anyMatch(p::equals)).toList().size();
    }

    private void updateCellWithAdjacentMines(final Pair<Integer, Integer> position) {
        this.grid[position.getX()][position.getY()] = new CellImpl(
                CellType.SAFE,
                this.countNumberOfMines(this.bounds.getAdjacentPositions(position))
        );
    }

    private void updateAdjacentCells() {
        this.mines.forEach((minePosition) -> {
            this.bounds.getAdjacentPositions(minePosition).stream()
                    .filter((p) -> this.mines.stream().noneMatch(p::equals))
                    .filter((p) -> this.grid[p.getX()][p.getY()].getAdjacentMines() == 0) // do not recheck already updated cells
                    .forEach(this::updateCellWithAdjacentMines);
        });
    }

    public Grid build() {
        initGrid();
        placeMines();
        updateAdjacentCells();
        return new GridImpl(this.bounds, this.grid);
    }
}
