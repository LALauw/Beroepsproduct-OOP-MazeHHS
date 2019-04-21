package source;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DecisionCoverage {
  @Test
  public void move() {
    Menu menu = new Menu();
    Board board = new Board(menu);

    System.out.println(Arrays.toString(board.getPlayer().getPosition()));
    System.out.println("Player tries to move to an empty tile, should be possible. X should change");
  
    assertEquals(1, board.getPlayer().getX());
    board.getPlayer().move(Constants.Direction.EAST);
    assertEquals(2, board.getPlayer().getX());

    System.out.println(Arrays.toString(board.getPlayer().getPosition()));
    System.out.println("Player tries to move to a wall, should not be possible. Y should stay the same.");

    assertEquals(1, board.getPlayer().getY());
    board.getPlayer().move(Constants.Direction.NORTH);
    assertEquals(1, board.getPlayer().getY());

    System.out.println(Arrays.toString(board.getPlayer().getPosition()));
    System.out.println("Player tries to move to an empty tile, should be possible. X should change.");

    assertEquals(2, board.getPlayer().getX());
    board.getPlayer().move(Constants.Direction.WEST);
    assertEquals(1, board.getPlayer().getX());

    System.out.println(Arrays.toString(board.getPlayer().getPosition()));
    System.out.println("Player tries to move to a wall, should not be possible. Y should stay the same.");

    assertEquals(1, board.getPlayer().getY());
    board.getPlayer().move(Constants.Direction.SOUTH);
    assertEquals(1, board.getPlayer().getY());

    System.out.println(Arrays.toString(board.getPlayer().getPosition()));
  }
}