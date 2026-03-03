package it.unibo.pps.e2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  private final int defaultSize = 5;
  private Pieces knight;
  private Pieces pawn;
  private Logics logic;

    /**
     * Chose a pawn position where the knight can move.
     */
  @BeforeEach
  public void initBoardWithFixedKnightAndPawn() {
    this.knight = new Knight(new Pair<>(0, 0));
    this.pawn = new Pawn(new Pair<>(1, 2));
    Board board = new BoardImpl(this.defaultSize);
    this.logic = new LogicsImpl(board, this.knight, this.pawn);
  }

  @Test
  public void knightInsideBoard() {
    assertTrue(this.logic.hasKnight(this.knight.getPosition().getX(), this.knight.getPosition().getY()));
  }

  @Test
  public void knightMoveOutsideBoard() {
    assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(this.defaultSize, 0));
  }

  @Test
  public void knightMoveAllowed() {
    Pair<Integer, Integer> knightNewPosition = new Pair<>(this.knight.getPosition().getX()+1, this.knight.getPosition().getY()+2);
    this.logic.hit(knightNewPosition.getX(), knightNewPosition.getY());
    assertTrue(this.logic.hasKnight(knightNewPosition.getX(), knightNewPosition.getY()));
  }

  @Test
  public void knightMoveNotAllowedWillNotMoveTheKnight() {
    Pair<Integer, Integer> knightWrongNewPosition = new Pair<>(0, 1);
    this.logic.hit(knightWrongNewPosition.getX(), knightWrongNewPosition.getY());
    assertFalse(this.logic.hasKnight(knightWrongNewPosition.getX(), knightWrongNewPosition.getY()));
  }

  @Test
  public void knightHitPawnSuccessfully() {
    assertTrue(this.logic.hit(this.pawn.getPosition().getX(), this.pawn.getPosition().getY()));
  }
}
