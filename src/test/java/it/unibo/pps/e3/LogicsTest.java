package it.unibo.pps.e3;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LogicsTest {

    private Logics logics;

    private void initLogic(final int size, final Set<Pair<Integer, Integer>> mines) {
        Bounds bounds = new BoundsImpl(size);
        Grid grid = new GridBuilder()
                .withBounds(bounds)
                .withStrategy(MinePlacementStrategy.fixed(mines))
                .build();
        this.logics = new LogicsImpl(bounds, grid);
    }

    @Test
    public void hitMine() {
        Pair<Integer, Integer> mine = new Pair<>(0, 0);
        initLogic(1, Set.of(mine));
        assertTrue(this.logics.hit(mine.getX(), mine.getY()));
    }

    @Test
    public void toggleFlag() {
        Pair<Integer, Integer> startingCell = new Pair<>(0, 0);
        Logics logics = new LogicsImpl(5);
        logics.toggle(startingCell.getX(), startingCell.getY());
        assertInstanceOf(CellOutput.FlaggedCell.class, logics.get(startingCell.getX(), startingCell.getY()));
    }

    @Test
    public void hitEmptyCellWaterfallEffectExpected() {
        Pair<Integer, Integer> emptyCell = new Pair<>(0, 4);
        Map<Pair<Integer, Integer>, Integer> revealedCellsAndAdjacentMines = Map.of(
                emptyCell, 0,
                new Pair<>(0, 3),  0,
                new Pair<>(0, 2), 1,
                new Pair<>(1, 2), 3,
                new Pair<>(1, 3), 2,
                new Pair<>(1, 4), 1
        );
        initLogic(
                revealedCellsAndAdjacentMines.size(),
                GridBuilderTest.MINES_POSITION_AND_NUMBER_OF_ADJACENT_MINES.keySet()
        );
        this.logics.hit(emptyCell.getX(), emptyCell.getY());

        for (Pair<Integer, Integer> revealedCellPosition : revealedCellsAndAdjacentMines.keySet()) {
            CellOutput output = this.logics.get(revealedCellPosition.getX(), revealedCellPosition.getY());
            assertInstanceOf(CellOutput.SafeCell.class, output);
            CellOutput.SafeCell safeCell = (CellOutput.SafeCell) output;
            int value = safeCell.value();
            assertEquals(revealedCellsAndAdjacentMines.get(revealedCellPosition), value);
        }
    }

    @Test
    public void checkVictory() {
        initLogic(5, Set.of(new Pair<>(2, 2)));
        this.logics.hit(0, 0);
        assertTrue(this.logics.checkVictory());
    }
}
