package source;

import java.awt.*;
import java.awt.image.ImageObserver;

import static source.Constants.*;

public abstract class Tile {
  private static int tileCounter = 0;
  private Map map;
  private int x, y;
  private int id;
  private Image icon;
  private boolean walkable;

  /**
   * This constructor creates an instance and sets the properties of this object. This object sets walkable to true
   * which is passed on as default to sub classes which makes it possible for the player walk onto this tile until this
   * variable is changed.
   *
   * @param map is a Map where this tile is related to.
   * @param x is an integer that is used to indicate the x point location.
   * @param y is an integer that is used to indicate the y point location.
   */
  public Tile(Map map, int x, int y) {
    this.id = tileCounter++;
    this.map = map;
    this.x = x;
    this.y = y;
    this.walkable = true;
    this.icon = null;
  }

  public static int getTileCounter() {
    return tileCounter;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getId() {
    return id;
  }

  public Image getIcon() {
    return icon;
  }

  public void setIcon(Image icon) {
    this.icon = icon;
  }

  public boolean isWalkable() {
    return walkable;
  }

  public void setWalkable(boolean walkable) {
    this.walkable = walkable;
  }

  /**
   * This method is meant for getting the position of an int[]
   * with 2 elements
   *
   * @return new int[] with 2 elements
   */
  public int[] getPosition() {
    return new int[] { x, y };
  }

  /**
   * This method returns a boolean whether or not a player is able to navigate towards this tile. This method can be
   * overwritten in subclasses.
   *
   * @return a boolean whether or not a player is able to navigate towards this tile
   */
  public boolean navigable() {
    return walkable;
  }

  /**
   * If the subclass object does not have an overwrite method then this method will do absolutely nothing :)
   */
  public void specialEvent() {}

  /**
   * This method is meant to paint every subclass of this 'Tile' class, as it gets the icon from the tile and draws them
   * onto the board.
   *
   * @param g is Graphics that is used to access methods for painting purposes
   * @param i is an integer that has the image size per square
   * @param p is an integer that has the padding/margin of the panel for border management
   * @param observer is an ImageObserver from another class
   */
  public void paintTile(Graphics g, int i, int p, ImageObserver observer) {
    g.drawImage(map.getTile(x, y).getIcon(), x * i + p, y * i + p, observer);
  }
}
