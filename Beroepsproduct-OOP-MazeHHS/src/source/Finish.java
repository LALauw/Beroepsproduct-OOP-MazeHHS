package source;

import javax.swing.*;

import java.awt.*;

import static source.Constants.*;

public class Finish extends Tile {
  /**
   * This constructor creates an instance and sets the properties of this object. This object is walkable by default
   * which makes it possible for the player walk onto this tile.
   *
   * @param map is a Map where this tile is related to.
   * @param x is an integer that is used to indicate the x point location.
   * @param y is an integer that is used to indicate the y point location.
   *
   * @see super#Tile(Map, int, int)
   * @see #setIcon(Image)
   * @see Board#rescaleImageIconToImage(ImageIcon)
   * @see Constants#IMAGE_ICON_DEFAULT_TILE_FINISH
   */
  public Finish(Map map, int x, int y) {
    super(map, x, y);
    this.setIcon(map.getBoard().rescaleImageIconToImage(IMAGE_ICON_DEFAULT_TILE_FINISH));
  }

  /**
   * This method overrides the specialEvent() method from the abstract class Tile
   *
   * It is called when the player moves onto the finish tile.
   * It call several methods form the Menu class, opening the victory screen.
   *
   * @see Menu#openBoardPanel(String)
   * @see Menu#openSideTab(int)
   * @see Menu#recolorEntireFrame()
   * @see Menu#updateEndLabels()
   */
  @Override
  public void specialEvent() {
    getMap().getBoard().getMenu().openBoardPanel("end");
    getMap().getBoard().getMenu().openSideTab(0);
    getMap().getBoard().getMenu().recolorEntireFrame();
    getMap().getBoard().getMenu().updateEndLabels();
  }
}
