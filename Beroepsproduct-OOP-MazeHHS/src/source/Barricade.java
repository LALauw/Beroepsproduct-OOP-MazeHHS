package source;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static source.Constants.*;

public class Barricade extends Tile {
  private int value;
  private Image backgroundIcon;

  /**
   * This constructor creates an instance and sets the properties of this object. This object is walkable by default
   * which makes it possible for the player walk onto this tile. This constructor sets the placeable variable to true
   * so it becomes possible to place items onto this object. This constructor also adds an image to the 'icon' variable.
   *
   * @param map is a Map where this tile is related to.
   * @param x is an integer that is used to indicate the x point location.
   * @param y is an integer that is used to indicate the y point location.
   * @param value is an integer that is used to indicate the required key value to open this object.
   *
   * @see super#Tile(Map, int, int)
   * @see Tile#setWalkable(boolean)
   * @see #setIcon(Image)
   * @see Board#rescaleImageIconToImage(ImageIcon)
   * @see Constants#IMAGE_ICON_DEFAULT_TILE_EMPTY
   * @see Constants#IMAGE_ICON_DEFAULT_TILE_BARRICADE_CLOSED
   */
  public Barricade(Map map, int x, int y, int value) {
    super(map, x, y);
    this.value = value;
    this.setWalkable(false);
    this.setIcon(map.getBoard().rescaleImageIconToImage(IMAGE_ICON_DEFAULT_TILE_BARRICADE_CLOSED));
    this.backgroundIcon = map.getBoard().rescaleImageIconToImage(IMAGE_ICON_DEFAULT_TILE_EMPTY);
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Image getBackgroundIcon() {
    return backgroundIcon;
  }

  public void setBackgroundIcon(Image backgroundIcon) {
    this.backgroundIcon = backgroundIcon;
  }

  /**
   * This method sets the 'value' to `-1`, it also replaces the current Image of 'icon' with another Image
   * This would indicate that the barricade has opened based on the Texture/theme.
   *
   * @see #setWalkable(boolean)
   * @see Board#rescaleImageIconToImage(ImageIcon)
   */
  public void openBarricade() {
    this.setWalkable(true);
    this.value = -1;
    if(getMap().getTexture() == Texture.SNOW)
      this.setIcon(getMap().getBoard().rescaleImageIconToImage(IMAGE_ICON_SNOW_TILE_BARRICADE_OPEN));
    else if(getMap().getTexture() == Texture.HELL)
      this.setIcon(getMap().getBoard().rescaleImageIconToImage(IMAGE_ICON_HELL_TILE_BARRICADE_OPEN));
    else
      this.setIcon(getMap().getBoard().rescaleImageIconToImage(IMAGE_ICON_DEFAULT_TILE_BARRICADE_OPEN));
  }

  /**
   * This method returns a boolean whether or not a player is able to navigate towards this tile. This method is
   * overwritten from the abstract class 'Tile'.
   *
   * @return a boolean whether or not a player is able to navigate towards this tile
   */
  @Override
  public boolean navigable() {
    if(super.navigable())
      return true;
    else if(!getMap().getBoard().getPlayer().getInventory().containsKeyValue(value)) {
      consoleLog("we do NOT have a key value of " + value);
      return false;
    }

    openBarricade();
    getMap().getBoard().getPlayer().addCountBarricadeOpened();
    getMap().getBoard().getMenu().updateCountBarricades();
    return true;
  }

  /**
   * This method paints a background image (usually an image of an empty tile) then paints the icon (usually a
   * transparent closed/opened barricade) which is then placed onto the background image. This will show as one image
   * draws them onto the board. If the value of this object doesn't equal -1 then it will also paint text as a String
   * onto the board on top of the image(s).
   *
   * @param g is Graphics that is used to access methods for painting purposes
   * @param i is an integer that has the image size per square
   * @param p is an integer that has the padding/margin of the panel for border management
   * @param observer is an ImageObserver from another class
   *
   *
   * @see Tile#getX()
   * @see Tile#getY()
   * @see Board#DrawStringOutline(Graphics, Font, int, int, String, Color, Color)
   * @see Inventory#containsKeyValue(int)
   */
  @Override
  public void paintTile(Graphics g, int i, int p, ImageObserver observer) {
    g.drawImage(backgroundIcon, getX() * i + p, getY() * i + p, observer);

    super.paintTile(g, i, p, observer);

    if(value != -1) {
      final Font font = new Font("Verdana", Font.BOLD, 13);
      Board.DrawStringOutline(g, font, getX() * i + p, getY() * i + p, String.valueOf(value), getMap().getBoard().getPlayer().getInventory().containsKeyValue(value) ? Color.GREEN : Color.RED, Color.BLACK);
    }
  }
}
