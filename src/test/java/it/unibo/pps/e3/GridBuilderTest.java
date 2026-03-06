package it.unibo.pps.e3;

import it.unibo.pps.e2.MoveStrategy;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridBuilderTest {

    public static final Map<Pair<Integer, Integer>, Integer> MINES_POSITION_AND_NUMBER_OF_ADJACENT_MINES = new HashMap<>(
            Map.of(
                    new Pair<>(0, 0), 1,
                    new Pair<>(0, 1), 1,
                    new Pair<>(2, 2), 1,
                    new Pair<>(2, 3), 1,
                    new Pair<>(4, 4), 0
            )
    );
    private Grid grid;

    public void initFixedGrid() {
        int size = 5;
        Bounds bounds = new BoundsImpl(size);
        MinePlacementStrategy fixedStrategy = MinePlacementStrategy.fixed(MINES_POSITION_AND_NUMBER_OF_ADJACENT_MINES.keySet());
        int totalMines = fixedStrategy.generateMines(bounds).size();
        this.grid = new GridBuilder().withBounds(bounds).withStrategy(fixedStrategy).build();
    }

    @Test
    public void checkTotalNumberOfMines() {
        initFixedGrid();
        int totalMines = MINES_POSITION_AND_NUMBER_OF_ADJACENT_MINES.size();
        assertEquals(totalMines, grid.getTotalMines());
    }

    @Test
    public void checkNumberOfAdjacentMinesOfCellsAdjacentMines() {
        initFixedGrid();
        for (Pair<Integer, Integer> minePosition : MINES_POSITION_AND_NUMBER_OF_ADJACENT_MINES.keySet()) {
            int numberOfAdjacentMines = this.grid.numberOfAdjacentMines(minePosition.getX(), minePosition.getY());
            assertEquals(MINES_POSITION_AND_NUMBER_OF_ADJACENT_MINES.get(minePosition), numberOfAdjacentMines);
        }
    }

    @Test
    public void checkCellsAdjacentOfAMine() {
        initFixedGrid();
        Map<Pair<Integer, Integer>, Integer> adjacentWithNumberOfAdjacentMines = Map.of(
                new Pair<>(1, 0), 2,
                new Pair<>(1, 1), 3
        );
        adjacentWithNumberOfAdjacentMines.keySet().forEach((position) -> {
            Cell cell = this.grid.getCell(position.getX(), position.getY());
            assertEquals(adjacentWithNumberOfAdjacentMines.get(position), cell.getAdjacentMines());
        });
    }

}
