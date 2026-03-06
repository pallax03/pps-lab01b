package it.unibo.pps.e3;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BoundsTest {

    private int defaultSize = 5;
    private Bounds bounds;

    private void initBounds() {
        this.bounds = new BoundsImpl(this.defaultSize);
    }

    @Test
    public void checkPositionsOutOfBounds() {
        initBounds();
        assertTrue(this.bounds.isPositionOutOfBounds(new Pair<>(this.defaultSize, 0)));
        assertTrue(this.bounds.isPositionOutOfBounds(new Pair<>(-3, -4)));
        assertTrue(this.bounds.isPositionOutOfBounds(new Pair<>(-1, this.defaultSize)));
        assertTrue(this.bounds.isPositionOutOfBounds(new Pair<>(0, this.defaultSize)));
    }

    @Test
    public void checkAdjacentPositions() {
        initBounds();
        Pair<Integer, Integer> startingPosition = new Pair<>(0, 0);
        Set<Pair<Integer, Integer>> adjacentPositions = Set.of(
                new Pair<>(0, 1),
                new Pair<>(1, 0),
                new Pair<>(1, 1)
        );
        assertEquals(adjacentPositions, this.bounds.getAdjacentPositions(startingPosition));
    }
}
