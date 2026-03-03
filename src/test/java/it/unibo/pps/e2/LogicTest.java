package it.unibo.pps.e2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  private static final int DEFAULT_SIZE = 5;
  private Pair<Integer, Integer> knightStartPos;
  private Pair<Integer, Integer> pawnStartPos;
  private Logics logic;

    /**
     * Chose a pawn position where the knight can move.
     */
  @BeforeEach
  public void initBoardWithFixedKnightAndPawn() {
    this.knightStartPos = new Pair<>(0, 0);
    this.pawnStartPos = new Pair<>(1, 2);
    this.logic = new LogicsImpl(DEFAULT_SIZE, this.knightStartPos, this.pawnStartPos);
  }

  @Test
  public void knightInsideBoard() {
    assertTrue(this.logic.hasKnight(this.knightStartPos.getX(), this.knightStartPos.getY()));
  }

  @Test
  public void knightMoveOutsideBoard() {
    assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(DEFAULT_SIZE, 0));
  }

  @Test
  public void knightMoveNotAllowedWillNotMoveTheKnight() {
    Pair<Integer, Integer> knightWrongNewPosition = new Pair<>(0, 1);
    this.logic.hit(knightWrongNewPosition.getX(), knightWrongNewPosition.getY());
    assertFalse(this.logic.hasKnight(knightWrongNewPosition.getX(), knightWrongNewPosition.getY()));
  }

  @Test
  public void knightHitPawnSuccessfully() {
    assertTrue(this.logic.hit(this.pawnStartPos.getX(), this.pawnStartPos.getY()));
  }
}
