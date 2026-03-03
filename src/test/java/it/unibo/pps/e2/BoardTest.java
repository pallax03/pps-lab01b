package it.unibo.pps.e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

  private final int defaultSize = 5;
  private Board board;
  private final Pair<Integer, Integer> startingPosition = new Pair<>(0, 0);
  private Pieces pawn;

  @BeforeEach
  public void initBoard() {
    this.board = new BoardImpl(this.defaultSize);
    this.pawn = new Pawn(this.startingPosition);
  }

  @Test
  public void checkBoardBounds() {
    assertFalse(this.board.isPositionOutOfBoard(new Pair<>(1, 0)));
    assertTrue(this.board.isPositionOutOfBoard(new Pair<>(this.defaultSize, 0)));
    assertTrue(this.board.isPositionOutOfBoard(new Pair<>(this.defaultSize-1, -1)));
  }

  @Test
  public void test() {

  }

  @Test
  public void positionIsNotEmpty() {
    this.board.placePieceInBoard(this.pawn);
    assertFalse(this.board.isPositionEmpty(this.startingPosition));
  }

  @Test
  public void checkIfPositionsOverlapped() {
    assertTrue(this.board.placePieceInBoard(this.pawn));
    assertFalse(this.board.placePieceInBoard(this.pawn));
  }

  @Test
  public void placePieceInBoardOutOfBoard() {
    Pair<Integer, Integer> outOfBoardPosition = new Pair<>(this.defaultSize, 0);
    Pieces pawnOutOfBoard = new Pawn(outOfBoardPosition);
    assertThrows(IndexOutOfBoundsException.class, () -> this.board.placePieceInBoard(pawnOutOfBoard));
  }
}
