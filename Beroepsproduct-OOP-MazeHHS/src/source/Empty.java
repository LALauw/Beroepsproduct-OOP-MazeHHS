package source;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static source.Constants.*;

public class Empty extends Tile {
  private Item item;
  private boolean placeable;

  /**
   * This constructor creates an instance and sets the properties of this object. This object is walkable by default
   * which makes it possible for the player walk onto this tile. This constructor sets the placeable variable to true
   * so it becomes possible to place items onto this object. This constructor also adds an image to the 'icon' variable.
   *
   * @param map is a Map where this tile is related to.
   * @param x is an integer that is used to indicate the x point location.
   * @param y is an integer that is used to indicate the y point location.
   *
   * @see super#Tile(Map, int, int)
   * @see #setIcon(Image)
   * @see Board#rescaleImageIconToImage(ImageIcon)
   * @see Constants#IMAGE_ICON_DEFAULT_TILE_EMPTY
   */
  public Empty(Map map, int x, int y) {
    super(map, x, y);
    this.item = null;
    this.placeable = true;
    this.setIcon(map.getBoard().rescaleImageIconToImage(IMAGE_ICON_DEFAULT_TILE_EMPTY));
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public boolean isPlaceable() {
    return placeable;
  }

  public void setPlaceable(boolean placeable) {
    this.placeable = placeable;
  }

  /**
   * This method places the specified instance of an object 'item' onto this tile
   *
   * @param item is an Item that is being placed
   * @see Item#setTile(Tile)
   */
  public void placeItem(Item item) {
    this.item = item;
    item.setTile(this);
  }

  /**
   * This method can be used to remove an object from this tile and sets 'item' as null
   */
  public void removeItem() {
    item = null;
  }

  /**
   * This method overrides the method from the abstract class Tile.
   *
   * It checks if the tile contains an item, if so it calls the pickupItem() method from the Player class.
   *
   * @see Player#pickupItem()
   */
  @Override
  public void specialEvent() {
    if(item != null) {
      getMap().getBoard().getPlayer().pickupItem();
    }
  }

  /**
   * This method is meant for painting an item onto the empty tile. It also gets the item value and draws the value
   * on top of the empty tile.
   *
   * @param g is Graphics that is used to access methods for painting purposes
   * @param i is an integer that has the image size per square
   * @param p is an integer that has the padding/margin of the panel for border management
   * @param observer is an ImageObserver from another class
   *
   * @see Item#getImageTile()
   * @see Item#getValue()
   * @see Board#DrawStringOutline(Graphics, Font, int, int, String, Color, Color)
   * @see Map#getTexture()
   * @see Constants.Texture
   */
  @Override
  public void paintTile(Graphics g, int i, int p, ImageObserver observer) {
    super.paintTile(g, i, p, observer);

    if(item != null) {
      g.drawImage(item.getImageTile(), getX() * i + p, getY() * i + p, observer);

      if(item.getValue() != -1) {
        final Font font = new Font("Verdana", Font.BOLD, 13);
        Board.DrawStringOutline(g, font, getX() * i + p, getY() * i + p, String.valueOf(item.getValue()), getMap().getTexture() == Texture.SNOW ? Color.CYAN : Color.ORANGE, Color.BLACK);
      }
    }
  }
}
