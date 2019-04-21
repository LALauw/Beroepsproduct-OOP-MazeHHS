package source;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static source.Constants.*;

public class Board extends JComponent {
  private Menu menu;
  private Map map;
  private Player player;
  private int sizeGrid;
  private int sizeImage;

  /**
   * This constructor creates an instance and sets the properties of this object. This object has a relation to Menu.
   *
   * @param menu is a Menu to which object is related to.
   *
   * @see Constants#TILES_MEDIUM
   * @see Constants#SIZE_OF_IMAGE_MEDIUM
   * @see Player#Player(Board)
   * @see Map#Map(Board)
   * @see ArrowKeyListener#ArrowKeyListener(Board)
   * @see Constants#consoleLog(String)
   */
  public Board(Menu menu) {
    consoleLog("creating board..");

    this.menu = menu;
    this.sizeGrid = TILES_MEDIUM;
    this.sizeImage = SIZE_OF_IMAGE_MEDIUM;
    this.player = new Player(this);
    this.map = new Map(this);
    setPreferredSize(new Dimension(482, 482));
    setBackground(Color.GRAY);
    setFocusable(true);
    setDoubleBuffered(true);
    addKeyListener(new ArrowKeyListener(this));

    consoleLog("created board");
  }

  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public int getSizeGrid() {
    return sizeGrid;
  }

  public void setSizeGrid(int sizeGrid) {
    this.sizeGrid = sizeGrid;
  }

  public int getSizeImage() {
    return sizeImage;
  }

  public void setSizeImage(int sizeImage) {
    this.sizeImage = sizeImage;
  }

  /**
   * This method scales an 'ImageIcon' and returns an 'Image' based on the 'sizeImage' of this class.
   *
   * @param imageIcon is an ImageIcon
   * @return an 'Image' instead of the 'ImageIcon'
   *
   * @see #rescaleImageIconToImage(ImageIcon)
   * @see #sizeImage
   */
  public Image rescaleImageIconToImage(ImageIcon imageIcon) {
    return imageIcon.getImage().getScaledInstance(sizeImage, sizeImage, Image.SCALE_SMOOTH);
  }

  /**
   * This method is meant for setting up a new Map with the given values of the comboboxes
   * it also repaints the entire Map and updates the menu labels and counts.
   *
   * @param mapSize is an integer that indicates the mapSize
   * @param level is an integer that indicates the level
   * @param texture is an integer that indicates the texture
   * @param maxOneKey is a boolean that indicates whether the player can have one key in their inventory
   *
   * @see Constants.MapSize
   * @see Player#Player(Board)
   * @see Inventory#setLimitOneKey(boolean)
   * @see Constants.Texture
   * @see #rescaleImageIconToImage(ImageIcon)
   * @see Constants#IMAGE_ICON_SNOW_PLAYER_RIGHT
   * @see Constants#IMAGE_ICON_HELL_PLAYER_RIGHT
   * @see Map#Map(Board, int, int, Texture)
   * @see Menu#updateLabelMapSize()
   * @see Menu#updateLabelLevel()
   * @see Menu#updateCountMoves()
   * @see Menu#updateCountKeys()
   * @see Menu#updateCountBarricades()
   * @see Menu#openBoardPanel(String)
   * @see Menu#repaintInventory()
   */
  public void setupNextMap(int mapSize, int level, int texture, boolean maxOneKey) {
    if(MapSize.values()[mapSize] == MapSize.SMALL) {
      sizeGrid = TILES_SMALL;
      sizeImage = SIZE_OF_IMAGE_SMALL;
    }
    else if(MapSize.values()[mapSize] == MapSize.MEDIUM) {
      sizeGrid = TILES_MEDIUM;
      sizeImage = SIZE_OF_IMAGE_MEDIUM;
    }
    else if(MapSize.values()[mapSize] == MapSize.LARGE) {
      sizeGrid = TILES_LARGE;
      sizeImage = SIZE_OF_IMAGE_LARGE;
    }

    player = new Player(this);
    player.getInventory().setLimitOneKey(maxOneKey);
    if(Texture.values()[texture] == Texture.SNOW)
      player.setImage(rescaleImageIconToImage(IMAGE_ICON_SNOW_PLAYER_RIGHT));
    else if(Texture.values()[texture] == Texture.HELL)
      player.setImage(rescaleImageIconToImage(IMAGE_ICON_HELL_PLAYER_RIGHT));
    map = new Map(this, mapSize, level, Texture.values()[texture]);
    menu.updateLabelMapSize();
    menu.updateLabelLevel();
    menu.updateCountMoves();
    menu.updateCountKeys();
    menu.updateCountBarricades();
    menu.openBoardPanel("game");
    menu.repaintInventory();

    repaint();
  }

  /**
   * This method restarts the current map with the same values as it started then prints out the reason of the repaint
   *
   * @see #setupNextMap(int, int, int, boolean)
   * @see Map#getSizeMap()
   * @see Map#getLevel()
   * @see Constants.Texture
   * @see Map#getTexture()
   * @see Inventory#isLimitOneKey()
   * @see Constants#consoleLog(String)
   */
  public void setupRestartMap() {
    setupNextMap(map.getSizeMap(), map.getLevel(), Texture.valueOf(map.getTexture().toString()).ordinal(), player.getInventory().isLimitOneKey());
    consoleLog("Current map restarted!");
  }

  /**
   * This method is meant to paint every subclass of the abstract class 'tile', as well as 'player' class and items
   * on top of tiles onto the board. It also draws text as string with the values of barricades and keys onto the board.
   *
   * @param g is Graphics that is used to access methods for painting purposes
   *
   * @see super#paintComponent(Graphics)
   * @see Constants#consoleLog(String)
   * @see Tile#paintTile(Graphics, int, int, ImageObserver)
   * @see Player#getImage()
   * @see Player#getX()
   * @see Player#getY()
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    final int p = 1; //set the padding/margin of the frame for border management
    final int i = sizeImage; //set image size per square
    final int s = sizeGrid; //set tile grid size per columns and rows

    g.setFont(new Font("Tahoma", Font.BOLD, 36));
    g.setColor(COLOR_GRAY140);
    g.drawString("Loading", getSize().width / 2 - 65, getSize().height / 2 + 10);

    final Font font = new Font("Verdana", Font.BOLD, 13); //calibri.13 - tahoma.13 - verdana.13

    g.setFont(font);

    for(int y = 0; y < s; y++) {
      for(int x = 0; x < s; x++) {
        map.getTile(x, y).paintTile(g, i, p, this);
      }
    }

    g.drawImage(player.getImage(), player.getX() * i, player.getY() * i, this);
  }

  /**
   * This method draws text onto the specified location with a font and outline and inline colours.
   *
   * @param g is Graphics that is used to access methods for (re)painting purposes
   * @param font is a Font used for drawing purposes
   * @param x is an integer that is used to indicate the location on screen
   * @param y is an integer that is used to indicate the location on screen
   * @param text is a String of text that will be painted onto the screen
   * @param outline is a Color which is used for painting the outline of the text
   * @param inline is a Color which is used for painting the inline of the text
   */
  public static void DrawStringOutline(Graphics g, Font font, int x, int y, String text, Color outline, Color inline) {
    final int derive = (int) (g.getFont().getSize() * .9d);
    final int a = 2;

    g.setFont(font);

    g.setColor(outline);
    g.drawString(text, x + 1 + a, y - 1 + a + derive);
    g.drawString(text, x + 1 + a, y + 1 + a + derive);
    g.drawString(text, x - 1 + a, y - 1 + a + derive);
    g.drawString(text, x - 1 + a, y + 1 + a + derive);

    g.setColor(inline);
    g.drawString(text, x + a, y + a + derive);
  }
}
