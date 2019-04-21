package source;

import java.awt.*;

import static source.Constants.*;

public class Player {
  private Board board;
  private int x, y;
  private Inventory inventory;
  private Direction facing;
  private Image image;
  private int countMoves;
  private int countBarricadesOpened;

  /**
   * This constructor creates an instance and sets the properties of this object. This object has a relation to Board.
   * This object contains an instance of inventory.
   * This constructor also sets the facing direction of the Object to 'EAST'
   *
   * @param board
   *
   * @see Constants#consoleLog(String)
   */
  public Player(Board board) {
    consoleLog("creating player..");

    this.board = board;
    this.x = 0;
    this.y = 0;
    this.inventory = new Inventory(this);
    this.image = board.rescaleImageIconToImage(IMAGE_ICON_DEFAULT_PLAYER_RIGHT);
    this.facing = Direction.EAST;
    this.countMoves = 0;
    this.countBarricadesOpened = 0;

    consoleLog("created player");
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
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

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public Direction getFacing() {
    return facing;
  }

  public void setFacing(Direction facing) {
    this.facing = facing;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public int getCountMoves() {
    return countMoves;
  }

  public void setCountMoves(int countMoves) {
    this.countMoves = countMoves;
  }

  public int getCountBarricadesOpened() {
    return countBarricadesOpened;
  }

  public void setCountBarricadesOpened(int countBarricadesOpened) {
    this.countBarricadesOpened = countBarricadesOpened;
  }

  /**
   * This method increases the 'countBarricadesOpened' variable by 1
   */
  public void addCountBarricadeOpened() {
    countBarricadesOpened++;
  }

  /**
   * This method increases the 'countMoves' variable by 1
   */
  public void addCountMoves() {
    countMoves++;
  }

  /**
   * This method returns an array of integers indicating the position of the player
   *
   * @return an array of integers indicating the position of the player
   */
  public int[] getPosition() {
    return new int[] { x, y };
  }

  /**
   * This method sets the position of the player
   *
   * @param position an array of integers indicating to set the position of the player
   */
  public void setPosition(int[] position) {
    x = position[0];
    y = position[1];
  }

  /**
   * This method sets the x and y position of the player.
   *
   * @param x an integers indicating to set the X position of the player
   * @param y an integers indicating to set the Y position of the player
   */
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * This method is meant for changing the players image.
   *
   * It also looks at the current textures enabled and changes the image corresponding to the texture.
   * Then it repaints the board so the image updates
   *
   * @param direction is a Direction related to which direction the player is going to be facing
   */
  public void setPlayerFacingDirectionImage(Direction direction) {
    if(direction == Direction.EAST && facing != direction) {
      if(board.getMap().getTexture() == Texture.SNOW)
        image = board.rescaleImageIconToImage(IMAGE_ICON_SNOW_PLAYER_RIGHT);
      else if(board.getMap().getTexture() == Texture.HELL)
        image = board.rescaleImageIconToImage(IMAGE_ICON_HELL_PLAYER_RIGHT);
      else
        image = board.rescaleImageIconToImage(IMAGE_ICON_DEFAULT_PLAYER_RIGHT);
    }
    else if(direction == Direction.WEST && facing != direction) {
      if(board.getMap().getTexture() == Texture.SNOW)
        image = board.rescaleImageIconToImage(IMAGE_ICON_SNOW_PLAYER_LEFT);
      else if(board.getMap().getTexture() == Texture.HELL)
        image = board.rescaleImageIconToImage(IMAGE_ICON_HELL_PLAYER_LEFT);
      else
        image = board.rescaleImageIconToImage(IMAGE_ICON_DEFAULT_PLAYER_LEFT);
    }
    else
      return;

    facing = direction;
    board.repaint();
  }

  /**
   * This method checks which direction the player is going to be moving to, then changes the facing angle of the player
   * if necessary. This method then checks if the player wishes to move outside of the map, then checks whether the
   * player is trying to move onto a non-walkable tile. Afterwards if everything went well, the position of the player
   * is set to the destination tile. The count of the amount of player moves increases by 1 followed by a JLabel update
   * onto the Menu GUI. The board will then be repainted and then the last check to see if the player is currently
   * standing on a special tile where an event can occur. Such as picking up a key or reaching the Finish tile.
   *
   * @param direction is a Direction related to where the player wishes to move to
   *
   * @see Constants.Direction
   * @see #setPlayerFacingDirectionImage(Direction)
   * @see Board#getSizeGrid()
   * @see Constants#consoleLog(String)
   * @see Map#getTile(int, int)
   * @see #addCountMoves()
   * @see Menu#updateCountMoves()
   * @see Tile#specialEvent()
   */
  public void move(Direction direction) {
    int destinationX = x;
    int destinationY = y;

    if(direction == Direction.NORTH)
      destinationY--;
    else if(direction == Direction.SOUTH)
      destinationY++;
    else if(direction == Direction.EAST)
      destinationX++;
    else if(direction == Direction.WEST)
      destinationX--;

    setPlayerFacingDirectionImage(direction);

    if(destinationX < 0 || destinationY < 0 || destinationX == board.getSizeGrid() || destinationY == board.getSizeGrid()) {
      consoleLog("You cannot move out of the map");
      return;
    }
    else if(!board.getMap().getTile(destinationX, destinationY).navigable()) {
      consoleLog("You cannot move onto a non-walkable tile");
      return;
    }

    x = destinationX;
    y = destinationY;

    addCountMoves();

    board.getMenu().updateCountMoves();

    board.repaint();

    board.getMap().getTile(x, y).specialEvent();
  }

  /**
   * This method calls a method to remove the tile of an item. Then checks whether the boolean of Inventory#isLimitOneKey
   * is true, if this is the case then another method is called to see if the inventory already contains a key whereat
   * this key is first destroyed before calling another method to place another key into the inventory of the player.
   * This method then calls a method to remove an item from a tile. The repaint method of the Inventory in Menu will
   * then be called and executed.
   *
   * @see Constants#consoleLog(String)
   * @see Map#getTile(int, int)
   * @see Empty#getItem()
   * @see Item#setTile(Tile)
   * @see Inventory#isLimitOneKey()
   * @see Inventory#containsKey()
   * @see Inventory#destroyKeys()
   * @see Inventory#addItem(Item)
   * @see Empty#removeItem()
   * @see Menu#repaintInventory()
   */
  public void pickupItem() {
    final Empty empty = ((Empty) board.getMap().getTile(x, y));
    final Item item = empty.getItem();
    item.setTile(null);

    if(inventory.isLimitOneKey()) {
      if(inventory.containsKey())
        inventory.destroyKeys();
    }

    inventory.addItem(item);
    empty.removeItem();
    board.getMenu().repaintInventory();
  }
}