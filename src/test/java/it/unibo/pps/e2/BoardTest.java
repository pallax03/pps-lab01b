package it.unibo.pps.e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

  private final int defaultSize = 5;
  private Board board;
  private final Pair<Integer, Integer> startingPosition = new Pair<>(0, 0);

  @BeforeEach
  public void initBoard() {
    this.board = new BoardImpl(this.defaultSize);
  }

  @Test
  public void checkBoardBounds() {
    assertFalse(this.board.isPositionOutOfBoard(new Pair<>(1, 0)));
    assertTrue(this.board.isPositionOutOfBoard(new Pair<>(this.defaultSize, 0)));
    assertTrue(this.board.isPositionOutOfBoard(new Pair<>(this.defaultSize-1, -1)));
  }

}
