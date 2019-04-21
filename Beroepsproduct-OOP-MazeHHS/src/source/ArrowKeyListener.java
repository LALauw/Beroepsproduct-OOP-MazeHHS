package source;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ArrowKeyListener extends KeyAdapter {
  private Board board;

  /**
   * This constructor creates an instance and sets the properties of this object. This object requires a Board to
   * access other objects and their methods.
   *
   * @param board is a Board where this tile is related to.
   */
  public ArrowKeyListener(Board board) {
    this.board = board;
  }

  /**
   * This method is called upon pressing in a key.
   *
   * It then calls the move() method in the Player class, but in order to get the Player we first have to call the
   * getPlayer() method in board.
   *
   * @param e is a KeyEvent
   */
  @Override
  public void keyPressed(KeyEvent e) {
    int c = e.getKeyCode();

    if(c == KeyEvent.VK_UP)         board.getPlayer().move(Constants.Direction.NORTH);
    else if(c == KeyEvent.VK_DOWN)  board.getPlayer().move(Constants.Direction.SOUTH);
    else if(c == KeyEvent.VK_LEFT)  board.getPlayer().move(Constants.Direction.WEST);
    else if(c == KeyEvent.VK_RIGHT) board.getPlayer().move(Constants.Direction.EAST);
  }
}