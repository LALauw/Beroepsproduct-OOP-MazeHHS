package source;

import javax.swing.*;
import java.awt.*;

import static source.Constants.*;

public class Wall extends Tile {
  /**
   * This constructor creates an instance and sets the properties of this object. This constructor puts walkable
   * variable to false (as walkable is true by default) to avoid the player walking over this object. This constructor
   * also adds an image to the 'icon' variable.
   *
   * @param map is a Map where this tile is related to.
   * @param x is an integer that is used to indicate the x point location.
   * @param y is an integer that is used to indicate the y point location.
   *
   * @see super#Tile(Map, int, int)
   * @see #setWalkable(boolean)
   * @see #setIcon(Image)
   * @see Board#rescaleImageIconToImage(ImageIcon)
   * @see Constants#IMAGE_ICON_DEFAULT_TILE_WALL
   */
  public Wall(Map map, int x, int y) {
    super(map, x, y);
    this.setWalkable(false);
    this.setIcon(map.getBoard().rescaleImageIconToImage(IMAGE_ICON_DEFAULT_TILE_WALL));
  }
}
