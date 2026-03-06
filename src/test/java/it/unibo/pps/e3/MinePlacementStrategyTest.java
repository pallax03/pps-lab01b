package it.unibo.pps.e3;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MinePlacementStrategyTest {

    private Bounds bounds;

    private void initBounds(final int size) {
        this.bounds = new BoundsImpl(size);
    }

    @Test
    public void generateRandomMinesEqualToBoundsSize() {
        int nMines = 5;
        initBounds(nMines);
        MinePlacementStrategy randomStrategy = MinePlacementStrategy.random();
        Set<Pair<Integer, Integer>> mines = randomStrategy.generateMines(bounds);
        assertEquals(nMines, mines.size());
    }

    @Test
    public void generateFixedMinesIndexOutOfBoards() {
        int boundsSize = 5;
        initBounds(boundsSize);
        Set<Pair<Integer, Integer>> minesPosition = Set.of(
                new Pair<>(boundsSize, 0)
        );
        MinePlacementStrategy fixedStrategy = MinePlacementStrategy.fixed(minesPosition);
        assertThrows(IndexOutOfBoundsException.class, ()-> fixedStrategy.generateMines(this.bounds));

    }

}
