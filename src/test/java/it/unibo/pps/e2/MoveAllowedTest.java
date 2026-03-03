package it.unibo.pps.e2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveAllowedTest {

  @Test
  public void pawnCanNotMove() {
    MoveStrategy pawn = MoveStrategy.pawn();
    assertFalse(pawn.moveAllowed(new Pair<>(0, 0), new Pair<>(1, 0)));
  }

  @Test
  public void knightAllowedMove() {
    Pair<Integer, Integer> startingPos = new Pair<>(2, 2);
    Pair<Integer, Integer> upLMovePosition = new Pair<>(startingPos.getX() - 1, startingPos.getY() + 2);
    Pair<Integer, Integer> downLMovePosition = new Pair<>(startingPos.getX() + 1, startingPos.getY() - 2);
    MoveStrategy knight = MoveStrategy.knight();
    assertTrue(knight.moveAllowed(startingPos, upLMovePosition));
    assertTrue(knight.moveAllowed(startingPos, downLMovePosition));
  }
}