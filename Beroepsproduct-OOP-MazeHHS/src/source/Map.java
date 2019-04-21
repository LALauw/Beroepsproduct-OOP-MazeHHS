package source;

import static source.Constants.*;

public class Map {
  private Tile[][] tiles;
  private Board board;
  private Texture texture;
  private int sizeMap;
  private int level;
  private long timestampMapCreated;

  /**
   * This constructor creates an instance and sets the properties of this object. This object has a relation to Board.
   *
   * @param board is a Board to which object is related to.
   */
  public Map(Board board) {
    consoleLog("creating map..");

    this.board = board;
    this.tiles = new Tile[board.getSizeGrid()][board.getSizeGrid()];
    this.texture = Texture.DEFAULT;
    this.timestampMapCreated = System.currentTimeMillis();
    this.sizeMap = 1;
    this.level = MAP_CODE.length;

    generateTiles();

    consoleLog("created map");
  }

  /**
   * This constructor creates an instance and sets the properties of this object. This object has a relation to Board.
   * Including parameters to generate a custom map.
   *
   * @param board is a Board to which object is related to.
   * @param sizeMap is an integer that defines which
   * @param level
   * @param texture
   */
  public Map(Board board, int sizeMap, int level, Texture texture) {
    consoleLog("creating map..");

    this.board = board;
    this.texture = texture;
    this.tiles = new Tile[board.getSizeGrid()][board.getSizeGrid()];
    this.timestampMapCreated = System.currentTimeMillis();
    this.sizeMap = sizeMap;
    this.level = level;

    generateTiles();

    consoleLog("created map");
  }

  public Tile[][] getTiles() {
    return tiles;
  }

  public void setTiles(Tile[][] tiles) {
    this.tiles = tiles;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public Texture getTexture() {
    return texture;
  }

  public void setTexture(Texture texture) {
    this.texture = texture;
  }

  public int getSizeMap() {
    return sizeMap;
  }

  public void setSizeMap(int sizeMap) {
    this.sizeMap = sizeMap;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public long getTimestampMapCreated() {
    return timestampMapCreated;
  }

  public void setTimestampMapCreated(long timestampMapCreated) {
    this.timestampMapCreated = timestampMapCreated;
  }

  /**
   * This method returns a tile based on the specified location.
   *
   * @param x is an integer that indicates the x position of the tile.
   * @param y is an integer that indicates the y position of the tile.
   * @return a Tile based on the specified location.
   */
  public Tile getTile(int x, int y) {
    return tiles[x][y];
  }

  /**
   * This method returns a tile based on the specified location.
   *
   * @param position is an array of integers that indicates the x and y position of the tile.
   * @return a Tile based on the specified location.
   */
  public Tile getTile(int[] position) {
    return tiles[position[0]][position[1]];
  }

  /**
   * This method gets the MAP_CODE from the Constants class and uses the parameter sizeMap.
   *
   * @param sizeMap is an integer that indicates map size
   * @return an array[] MAP_CODE with an integer indicating the element in MAP_CODE
   *
   * @see Constants#MAP_CODE
   */
  public String[] getMapCodes(int sizeMap) {
    return MAP_CODE[sizeMap];
  }

  /**
   * This method is used to display the current Level you're on.
   *
   * When the sizeMap is 1 and the level is MAP_CODE[1].length (4)
   * then it returns "Tutorial", which makes the fourth level in the map selector say tutorial
   * by adding more maps please raise the length required for tutorial to display.
   * If the 'level' is less than 10 the first character of the String is "0" + ('level'  + 1)
   *
   * @param sizeMap map size
   * @param level the level number which indicates map size 0 (small) / 1 (medium) / 2 (large)
   * @return a String with the current level name
   */
  public String getLevelName(int sizeMap, int level) {
    if(sizeMap == 1 && level == MAP_CODE[sizeMap].length-1)
      return "Tutorial";

    return level < 10 ? "0" + (level + 1) : String.valueOf(level);
  }

  /**
   * This method returns all the names of each level of a specified map size
   *
   * @param sizeMap is an integer that indicates the map size
   * @return an array[] of Strings of level names of a specified map size
   */
  public String[] getLevelNames(int sizeMap) {
    String[] arrayStringLevels = new String[getMapCodes(sizeMap).length];

    for(int k = 0; k < arrayStringLevels.length; k++) {
      arrayStringLevels[k] = getLevelName(sizeMap, k);
    }

    return arrayStringLevels;
  }

  /**
   * This method is meant for generating the tiles and loading them from a String in constants containing the mapCode and the playerPosition
   * e or i or h stands for new empty tile, it then checks the texture currently selected and replaces the image with the corresponding texture
   * i stand for item, it subtracts i from the code and gives the item the name 'key' then it parses the String to an integer giving the item a value
   * h stands for hint, it is meant for the tutorial map only. 'hn, he, hs, hw' is meant for the arrowImages. the h gets subtracted leaving 'n,e,s,w' that shows which way the arrow turns
   * w stand for wall, it also takes into consideration which texture is currently enabled
   * b stand for barricade, it also takes into consideration which texture is currently enabled and then parses the remaining String to an integer giving the barricade a value
   * f stands for finish, it also takes into consideration which texture is currenly enabled
   *
   * @see Item#Item(String, Tile)
   */
  public void generateTiles() {
    consoleLog("generating tiles..");

    String mapCode;

    mapCode = MAP_CODE[sizeMap][level];
    board.getPlayer().setPosition(MAP_PLAYER_POSITION[sizeMap][level]);

    final String[] rows = mapCode.split("\n");

    for(int y = 0; y < tiles.length; y++) {
      final String[] columns = rows[y].split(" ");

      for(int x = 0; x < tiles.length; x++) {
        final String currentTile = columns[x];

        if(currentTile.equals("e") || currentTile.startsWith("i") || currentTile.startsWith("h")) {
          tiles[x][y] = new Empty(this, x, y);
          if(texture != Texture.DEFAULT) {
            if(texture == Texture.SNOW)
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_SNOW_TILE_EMPTY));
            else if(texture == Texture.HELL)
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_HELL_TILE_EMPTY));
          }

          if(currentTile.startsWith("i")) {
            Item item = new Item(currentTile.substring(1).replaceAll("\\d", ""), tiles[x][y]);
            ((Empty) tiles[x][y]).placeItem(item);
            item.setImage(IMAGE_ICON_ITEM_KEY);
            item.setValue(Integer.parseInt(currentTile.replaceAll("\\D+","")));
          }
          else if(currentTile.startsWith("h")) {
            if(currentTile.substring(1).equals("n"))
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_HINT_ARROW_NORTH));
            else if(currentTile.substring(1).equals("e"))
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_HINT_ARROW_EAST));
            else if(currentTile.substring(1).equals("s"))
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_HINT_ARROW_SOUTH));
            else if(currentTile.substring(1).equals("w"))
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_HINT_ARROW_WEST));
          }
        }
        else if(currentTile.equals("w")) {
          tiles[x][y] = new Wall(this, x, y);
          if(texture != Texture.DEFAULT) {
            if(texture == Texture.SNOW)
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_SNOW_TILE_WALL));
            if(texture == Texture.HELL)
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_HELL_TILE_WALL));
          }
        }
        else if(currentTile.startsWith("b")) {
          tiles[x][y] = new Barricade(this, x, y, Integer.parseInt(currentTile.replaceAll("\\D+","")));
          if(texture != Texture.DEFAULT) {
            if(texture == Texture.SNOW) {
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_SNOW_TILE_BARRICADE_CLOSED));
              ((Barricade) tiles[x][y]).setBackgroundIcon(board.rescaleImageIconToImage(IMAGE_ICON_SNOW_TILE_EMPTY));
            }
            else if(texture == Texture.HELL) {
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_HELL_TILE_BARRICADE_CLOSED));
              ((Barricade) tiles[x][y]).setBackgroundIcon(board.rescaleImageIconToImage(IMAGE_ICON_HELL_TILE_EMPTY));
            }
          }
        }
        else if(currentTile.equals("f")) {
          tiles[x][y] = new Finish(this, x, y);
          if(texture != Texture.DEFAULT) {
            if(texture == Texture.SNOW)
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_SNOW_TILE_FINISH));
            else if(texture == Texture.HELL)
              tiles[x][y].setIcon(board.rescaleImageIconToImage(IMAGE_ICON_HELL_TILE_FINISH));
          }
        }
      }
    }

    consoleLog("generated tiles");
  }
}