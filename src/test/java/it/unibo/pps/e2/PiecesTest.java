package it.unibo.pps.e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.ObjectName;

import static org.junit.jupiter.api.Assertions.*;

public class PiecesTest {

  @Test
  public void pawnCanNotMove() {
    Pair<Integer, Integer> startingPos = new Pair<>(0, 0);
    Pieces pawn = new Pawn(startingPos);
    assertFalse(pawn.moveAllowed(0, 0));
  }

  @Test
  public void knightAllowedMove() {
    Pair<Integer, Integer> startingPos = new Pair<>(2, 2);
    Pieces knight = new Knight(startingPos);
    assertTrue(knight.moveAllowed(startingPos.getX() - 2, startingPos.getY()+1));
    assertTrue(knight.moveAllowed(startingPos.getX() + 1, startingPos.getY()-2));
  }

  @Test
  public void TwoPiecesInSamePosition() {
    Pair<Integer, Integer> position = new Pair<>(0, 0);
    Pieces piece1 = new Pawn(position);
    Pieces piece2 = new Pawn(position);
    assertTrue(piece1.positionEquals(piece2.getPosition()));
  }
}