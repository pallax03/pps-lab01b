package it.unibo.pps.e3;

public class LogicsImpl implements Logics {

    private final Grid grid;
    private final Bounds bounds;

    public LogicsImpl(final int size) {
        this.bounds = new BoundsImpl(size);
        this.grid = new GridBuilder()
                .withBounds(this.bounds)
                .withStrategy(MinePlacementStrategy.random())
                .build();
    }

    public LogicsImpl(final Bounds bounds, final Grid grid) {
        this.bounds = bounds;
        this.grid = grid;
    }

    @Override
    public boolean hit(int row, int col) {
        Pair<Integer, Integer> cellPosition = new Pair<>(row, col);
        Cell cell = this.grid.getCell(row, col);
        if (cell.isRevealed()) {
            return false;
        }

         this.grid.revealCell(cellPosition);
        if (cell.isMine()) {
            return true;
        }

        if (cell.getAdjacentMines() == 0) {
            this.bounds.getAdjacentPositions(cellPosition).forEach((p) -> {
                this.hit(p.getX(), p.getY());
            });
        }
        return false;
    }

    @Override
    public void toggle(int row, int col) {
        this.grid.getCell(row, col).toggleFlag();
    }

    @Override
    public CellOutput get(int row, int col) {
        Cell cell = this.grid.getCell(row, col);
        if (cell.isFlagged()) {
            return new CellOutput.FlaggedCell();
        }
        if (cell.isRevealed() && cell.isMine()) {
            return new CellOutput.MineCell();
        }
        if (cell.isRevealed() && !cell.isMine()) {
            return new CellOutput.SafeCell(cell.getAdjacentMines());
        }
        return new CellOutput.HiddenCell();
    }

    @Override
    public boolean checkVictory() {
        int totalCells = this.bounds.getSize() * this.bounds.getSize();
        return totalCells - this.grid.getTotalMines() == this.grid.getTotalRevealed();
    }

    @Override
    public void showAll() {
        for (int i = 0; i <this.bounds.getSize(); i++) {
            for (int j = 0; j < this.bounds.getSize(); j++) {
                this.hit(i, j);
            }
        }
    }
}
