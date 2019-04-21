package source;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
  public enum MapSize {
    SMALL,
    MEDIUM,
    LARGE,
  }

  public enum Texture {
    DEFAULT,
    SNOW,
    HELL,
  }

  public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
  }

  public static final int TILES_SMALL = 8;
  public static final int TILES_MEDIUM = 10;
  public static final int TILES_LARGE = 12;

  public static final int SIZE_OF_IMAGE_SMALL = 60;
  public static final int SIZE_OF_IMAGE_MEDIUM = 48;
  public static final int SIZE_OF_IMAGE_LARGE = 40;
  public static final int SIZE_OF_IMAGE_INVENTORY = 41;

  public static final ImageIcon IMAGE_ICON_CUSTOM_FRAME = new ImageIcon(Constants.class.getResource("/images/frame/longPanel.png"));
  public static final ImageIcon IMAGE_ICON_MINIMIZE = new ImageIcon(Constants.class.getResource("/images/frame/minimizePanel.png"));
  public static final ImageIcon IMAGE_ICON_MINIMIZE_HOVER = new ImageIcon(Constants.class.getResource("/images/frame/minimizeEntered.png"));
  public static final ImageIcon IMAGE_ICON_EXIT = new ImageIcon(Constants.class.getResource("/images/frame/exitPanel.png"));
  public static final ImageIcon IMAGE_ICON_EXIT_HOVER = new ImageIcon(Constants.class.getResource("/images/frame/exitEntered.png"));

  public static final Image IMAGE_GIF_MAIN_MENU_DARK = new ImageIcon(Constants.class.getResource("/images/menu/main_menu_dark.gif"))
    .getImage().getScaledInstance(784, 489, Image.SCALE_FAST);
  public static final Image IMAGE_GIF_MAIN_MENU_LIGHT = new ImageIcon(Constants.class.getResource("/images/menu/main_menu_light.gif"))
    .getImage().getScaledInstance(784, 489, Image.SCALE_FAST);
  public static final Image IMAGE_GIF_MAIN_OPTIONS_DARK = new ImageIcon(Constants.class.getResource("/images/menu/main_options_dark.gif"))
    .getImage().getScaledInstance(784, 489, Image.SCALE_FAST);
  public static final Image IMAGE_GIF_MAIN_OPTIONS_LIGHT = new ImageIcon(Constants.class.getResource("/images/menu/main_options_light.gif"))
    .getImage().getScaledInstance(784, 489, Image.SCALE_FAST);
  public static final Image IMAGE_MAIN_MENU_DARK = new ImageIcon(Constants.class.getResource("/images/menu/main_menu_dark_froze.png"))
    .getImage().getScaledInstance(784, 489, Image.SCALE_FAST);
  public static final Image IMAGE_MAIN_MENU_LIGHT = new ImageIcon(Constants.class.getResource("/images/menu/main_menu_light_froze.png"))
    .getImage().getScaledInstance(784, 489, Image.SCALE_FAST);
  public static final Image IMAGE_MAIN_OPTIONS_DARK = new ImageIcon(Constants.class.getResource("/images/menu/main_options_dark_froze.png"))
    .getImage().getScaledInstance(784, 489, Image.SCALE_FAST);
  public static final Image IMAGE_MAIN_OPTIONS_LIGHT = new ImageIcon(Constants.class.getResource("/images/menu/main_options_light_froze.png"))
    .getImage().getScaledInstance(784, 489, Image.SCALE_FAST);

  public static final Image IMAGE_GIF_END_SCREEN = new ImageIcon(Constants.class.getResource("/images/menu/end_solved.gif"))
    .getImage().getScaledInstance(482, 280, Image.SCALE_FAST);

  public static final ImageIcon IMAGE_ICON_MAIN_MENU_PLAY = new ImageIcon(Constants.class.getResource("/images/menu/play.png"));
  public static final ImageIcon IMAGE_ICON_MAIN_MENU_OPTIONS = new ImageIcon(Constants.class.getResource("/images/menu/options.png"));
  public static final ImageIcon IMAGE_ICON_MAIN_MENU_CREDITS = new ImageIcon(Constants.class.getResource("/images/menu/credits.png"));
  public static final ImageIcon IMAGE_ICON_MAIN_MENU_QUIT = new ImageIcon(Constants.class.getResource("/images/menu/quit.png"));
  public static final ImageIcon IMAGE_ICON_MAIN_MENU_BACK = new ImageIcon(Constants.class.getResource("/images/menu/back.png"));
  public static final ImageIcon IMAGE_ICON_MAIN_MENU_ANIM_ON = new ImageIcon(Constants.class.getResource("/images/menu/anim_on.png"));
  public static final ImageIcon IMAGE_ICON_MAIN_MENU_ANIM_OFF = new ImageIcon(Constants.class.getResource("/images/menu/anim_off.png"));
  public static final ImageIcon IMAGE_ICON_MAIN_MENU_DARK_ON = new ImageIcon(Constants.class.getResource("/images/menu/dark_on.png"));
  public static final ImageIcon IMAGE_ICON_MAIN_MENU_DARK_OFF = new ImageIcon(Constants.class.getResource("/images/menu/dark_off.png"));

  public static final ImageIcon IMAGE_ICON_DEFAULT_PLAYER_RIGHT = new ImageIcon(Constants.class.getResource("/images/theme/default/playerRight.png"));
  public static final ImageIcon IMAGE_ICON_DEFAULT_PLAYER_LEFT = new ImageIcon(Constants.class.getResource("/images/theme/default/playerLeft.png"));
  public static final ImageIcon IMAGE_ICON_DEFAULT_TILE_EMPTY = new ImageIcon(Constants.class.getResource("/images/theme/default/tile/empty.png"));
  public static final ImageIcon IMAGE_ICON_DEFAULT_TILE_FINISH = new ImageIcon(Constants.class.getResource("/images/theme/default/tile/finish.png"));
  public static final ImageIcon IMAGE_ICON_DEFAULT_TILE_WALL = new ImageIcon(Constants.class.getResource("/images/theme/default/tile/wall.png"));
  public static final ImageIcon IMAGE_ICON_DEFAULT_TILE_BARRICADE_CLOSED = new ImageIcon(Constants.class.getResource("/images/theme/default/tile/fence_closed.png"));
  public static final ImageIcon IMAGE_ICON_DEFAULT_TILE_BARRICADE_OPEN = new ImageIcon(Constants.class.getResource("/images/theme/default/tile/fence_open.png"));

  public static final ImageIcon IMAGE_ICON_SNOW_PLAYER_RIGHT = new ImageIcon(Constants.class.getResource("/images/theme/snow/playerRight.png"));
  public static final ImageIcon IMAGE_ICON_SNOW_PLAYER_LEFT = new ImageIcon(Constants.class.getResource("/images/theme/snow/playerLeft.png"));
  public static final ImageIcon IMAGE_ICON_SNOW_TILE_EMPTY = new ImageIcon(Constants.class.getResource("/images/theme/snow/tile/empty.png"));
  public static final ImageIcon IMAGE_ICON_SNOW_TILE_FINISH = new ImageIcon(Constants.class.getResource("/images/theme/snow/tile/finish.png"));
  public static final ImageIcon IMAGE_ICON_SNOW_TILE_WALL = new ImageIcon(Constants.class.getResource("/images/theme/snow/tile/wall.png"));
  public static final ImageIcon IMAGE_ICON_SNOW_TILE_BARRICADE_CLOSED = new ImageIcon(Constants.class.getResource("/images/theme/snow/tile/fence_closed.png"));
  public static final ImageIcon IMAGE_ICON_SNOW_TILE_BARRICADE_OPEN = new ImageIcon(Constants.class.getResource("/images/theme/snow/tile/fence_open.png"));

  public static final ImageIcon IMAGE_ICON_HELL_PLAYER_RIGHT = new ImageIcon(Constants.class.getResource("/images/theme/hell/playerRight.png"));
  public static final ImageIcon IMAGE_ICON_HELL_PLAYER_LEFT = new ImageIcon(Constants.class.getResource("/images/theme/hell/playerLeft.png"));
  public static final ImageIcon IMAGE_ICON_HELL_TILE_EMPTY = new ImageIcon(Constants.class.getResource("/images/theme/hell/tile/empty.png"));
  public static final ImageIcon IMAGE_ICON_HELL_TILE_FINISH = new ImageIcon(Constants.class.getResource("/images/theme/hell/tile/finish.png"));
  public static final ImageIcon IMAGE_ICON_HELL_TILE_WALL = new ImageIcon(Constants.class.getResource("/images/theme/hell/tile/wall.png"));
  public static final ImageIcon IMAGE_ICON_HELL_TILE_BARRICADE_CLOSED = new ImageIcon(Constants.class.getResource("/images/theme/hell/tile/fence_closed.png"));
  public static final ImageIcon IMAGE_ICON_HELL_TILE_BARRICADE_OPEN = new ImageIcon(Constants.class.getResource("/images/theme/hell/tile/fence_open.png"));

  public static final ImageIcon IMAGE_ICON_HINT_ARROW_NORTH = new ImageIcon(Constants.class.getResource("/images/tutorial/arrow_north.png"));
  public static final ImageIcon IMAGE_ICON_HINT_ARROW_EAST = new ImageIcon(Constants.class.getResource("/images/tutorial/arrow_east.png"));
  public static final ImageIcon IMAGE_ICON_HINT_ARROW_SOUTH = new ImageIcon(Constants.class.getResource("/images/tutorial/arrow_south.png"));
  public static final ImageIcon IMAGE_ICON_HINT_ARROW_WEST = new ImageIcon(Constants.class.getResource("/images/tutorial/arrow_west.png"));

  public static final ImageIcon IMAGE_ICON_ITEM_KEY = new ImageIcon(Constants.class.getResource("/images/item/key.png"));

  public static final ImageIcon IMAGE_ICON_GUIDE_KEYS = new ImageIcon(Constants.class.getResource("/images/guide/guide_keys.png"));
  public static final ImageIcon IMAGE_ICON_GUIDE_CODE = new ImageIcon(Constants.class.getResource("/images/guide/guide_code.png"));

  public static final Color COLOR_GRAY45 = new Color(45, 45, 45);
  public static final Color COLOR_GRAY55 = new Color(55, 55, 55);
  public static final Color COLOR_GRAY60 = new Color(60, 60, 60);
  public static final Color COLOR_GRAY65 = new Color(65, 65, 65);
  public static final Color COLOR_GRAY70 = new Color(70, 70, 70);
  public static final Color COLOR_GRAY75 = new Color(75, 75, 75);
  public static final Color COLOR_GRAY80 = new Color(80, 80, 80);
  public static final Color COLOR_GRAY125 = new Color(125, 125, 125);
  public static final Color COLOR_GRAY140 = new Color(140, 140, 140);
  public static final Color COLOR_GRAY145 = new Color(145, 145, 145);
  public static final Color COLOR_GRAY165 = new Color(165, 165, 165);
  public static final Color COLOR_GRAY175 = new Color(175, 175, 175);
  public static final Color COLOR_GRAY195 = new Color(195, 195, 195);

  public static final Color COLOR_TAB_DARK_UNSELECTED = COLOR_GRAY45;
  public static final Color COLOR_TAB_DARK_UNSELECTED_HIGHLIGHT = COLOR_GRAY65;
  public static final Color COLOR_TAB_DARK_SELECTED = COLOR_GRAY55;
  public static final Color COLOR_TAB_DARK_SELECTED_HIGHLIGHT = COLOR_GRAY75;

  public static final Color COLOR_TAB_LIGHT_UNSELECTED = COLOR_GRAY145;
  public static final Color COLOR_TAB_LIGHT_UNSELECTED_HIGHLIGHT = COLOR_GRAY165;
  public static final Color COLOR_TAB_LIGHT_SELECTED = COLOR_GRAY175;
  public static final Color COLOR_TAB_LIGHT_SELECTED_HIGHLIGHT = COLOR_GRAY195;

  public static final Color COLOR_TEXT_LIGHT = COLOR_GRAY45;
  public static final Color COLOR_TEXT_DARK = COLOR_GRAY195;

  public static final Color COLOR_TRANSPARENT = new Color(0, true);


  public static final int[] MAP_SMALL_01_PLAYER_POSITION = { 0, 0 };
  public static final String MAP_SMALL_01_CODE =
    "e w w w w w w w\n" +
    "e e e e e b150 ikey60 w\n" +
    "w e w e e e w w\n" +
    "ikey150 e w e e e e w\n" +
    "w ikey50 w ikey200 e e e w\n" +
    "w w e w w b200 w w\n" +
    "e e b200 e e e b100 b60\n" +
    "ikey100 e w w w e b50 f";

  public static final int[] MAP_SMALL_02_PLAYER_POSITION = { 6, 6 };
  public static final String MAP_SMALL_02_CODE =
    "ikey220 w f b400 ikey600 w w w\n" +
    "ikey200 w b300 ikey110 e e e w\n" +
    "b600 e b200 e e ikey700 w w\n" +
    "e e b220 b100 b50 b900 b220 ikey400\n" +
    "w e w b110 ikey110 b50 w w\n" +
    "w b600 w w ikey900 ikey50 e w\n" +
    "w e e w ikey110 e e w\n" +
    "ikey200 e e b900 ikey600 e e w";

  public static final int[] MAP_SMALL_03_PLAYER_POSITION = { 4, 4 };
  public static final String MAP_SMALL_03_CODE =
    "f b270 b300 ikey250 ikey300 ikey50 b40 ikey20\n" +
    "b20 b50 e e b80 ikey210 ikey10 w\n" +
    "w ikey30 b10 w b190 ikey130 ikey90 ikey80\n" +
    "e e e e e w ikey300 e\n" +
    "e ikey230 ikey150 ikey270 e w e ikey70\n" +
    "e e ikey300 ikey120 w ikey90 ikey180 ikey250\n" +
    "w b20 b30 e ikey300 ikey110 ikey300 e\n" +
    "ikey270 b130 b210 ikey140 e ikey220 ikey70 ikey140";

  public static final int[] MAP_MEDIUM_01_PLAYER_POSITION = { 0, 0 };
  public static final String MAP_MEDIUM_01_CODE =
    "e w b100 e e e e b30 b70 b90\n" +
    "e e e e ikey100 ikey300 e b90 b40 b90\n" +
    "e e b100 e e e e b300 b300 ikey100\n" +
    "e w b100 e e e e b190 b80 b90\n" +
    "e w b100 b100 w w w b220 b100 b50\n" +
    "e b200 b100 e e e w b100 b360 e\n" +
    "e w b100 w w b300 w w e e\n" +
    "e w b100 b900 b500 b100 e e e e\n" +
    "ikey200 w b100 b50 e e e w e e\n" +
    "e w b100 b150 e e e w e f";

  public static final int[] MAP_MEDIUM_02_PLAYER_POSITION = { 4, 0 };
  public static final String MAP_MEDIUM_02_CODE =
    "w ikey75 b100 w e w w w b40 f\n" +
    "w w e e e ikey24 w b65 e w\n" +
    "e b300 e w w b25 w e w w\n" +
    "e w e w w ikey40 b75 e e w\n" +
    "e w e ikey300 ikey65 w w w e b35\n" +
    "b55 e w b300 w e b24 ikey35 w e\n" +
    "w e w ikey55 b100 e w w w e\n" +
    "w e w w w e w e e e\n" +
    "w e e e w e ikey100 e w e\n" +
    "w w w e e e w w w w";

  public static final int[] MAP_MEDIUM_03_PLAYER_POSITION = { 4, 4 };
  public static final String MAP_MEDIUM_03_CODE =
    "ikey800 e b500 e b400 b600 e e w w\n" +
    "w w w w e e e e w w\n" +
    "ikey400 e w w b100 w w w w w\n" +
    "ikey500 e w w ikey50 w w e e ikey420\n" +
    "ikey600 e b200 e e ikey200 b300 e e ikey69\n" +
    "ikey700 e w w ikey300 w w ikey50 ikey110 ikey666\n" +
    "ikey800 e w w e w w b666 ikey600 w\n" +
    "w e w w w w b420 b69 w w\n" +
    "w b800 w w b800 b750 ikey100 b420 w w\n" +
    "w e ikey750 w f w e e e ikey100";

  public static final int[] MAP_MEDIUM_TUTORIAL_PLAYER_POSITION = { 1, 1 };
  public static final String MAP_MEDIUM_TUTORIAL_CODE =
    "w w w w w w w w w w w\n" +
    "w e he e e ikey100 e e hs w\n" +
    "w w w w w w w w b100 w\n" +
    "w e b50 hw e e e e e w\n" +
    "w e w w w ikey50 w w e w\n" +
    "w e ikey300 ikey200 w w w f b400 w\n" +
    "w e e e he b300 b200 w hn w\n" +
    "w w e w w w e e ikey200 w\n" +
    "w ikey200 e e ikey300 w ikey400 w ikey400 w\n" +
    "w w w w w w w w w w";

  public static final int[] MAP_LARGE_01_PLAYER_POSITION = { 0, 0 };
  public static final String MAP_LARGE_01_CODE =
    "e w w w w w ikey300 w ikey400 e w w\n" +
    "e e w ikey100 w w e e e e b300 ikey100\n" +
    "w e w b200 b100 e e e w w b300 b200\n" +
    "e e e e w b300 e e ikey200 w b200 ikey500\n" +
    "e e w w w b300 w e w w e w\n" +
    "w e w ikey50 w b200 b300 w w ikey400 e w\n" +
    "w e e e w e e e e e e w\n" +
    "w w w e w b40 e e e w e e\n" +
    "ikey100 e e e w b40 e e e e b100 w\n" +
    "w b100 w w w w w e e w e e\n" +
    "ikey200 b50 w w w e e w e w b400 b200\n" +
    "w w e ikey200 b500 ikey300 e b400 e w b300 f";

  public static final int[] MAP_LARGE_02_PLAYER_POSITION = { 6, 0 };
  public static final String MAP_LARGE_02_CODE =
    "w w w w w e e e e e ikey50 e\n" +
    "w w w w w w w w w w e ikey75\n" +
    "w e e e e e e e e w b50 b75\n" +
    "w e w e w w w b25 b75 w e e\n" +
    "w e w e w w w e e w e e\n" +
    "w e w e e b400 w ikey25 e w e e\n" +
    "w b600 e w w e w e e w e e\n" +
    "w w e w w e w e e b300 ikey300 e\n" +
    "w e e w w e w e b50 e e e\n" +
    "w e w e e e w w w w e w \n" +
    "w b25 w b300 w w ikey600 w w e e w\n" +
    "w ikey400 w f w w e e e e w w";

  public static final int[] MAP_LARGE_03_PLAYER_POSITION = { 4, 0 };
  public static final String MAP_LARGE_03_CODE =
    "ikey210 b70 b180 e e ikey170 ikey230 b290 ikey70 b210 ikey140 b150\n" +
    "w ikey290 w e w ikey240 w ikey6 w ikey40 w f\n" +
    "ikey30 b200 b90 ikey120 e ikey10 e b100 e ikey160 b140 b150\n" +
    "w b250 w ikey130 w e w e w ikey220 w b160\n" +
    "e e e e e e e e e b130 b40 b280\n" +
    "w e w e w e w e w ikey60 w b60\n" +
    "e e e ikey20 ikey170 e e b260 ikey100 b30 e b220\n" +
    "w e w e w e w ikey270 w ikey200 w b190\n" +
    "e ikey110 e ikey80 e ikey190 e ikey280 e ikey150 b290 b10\n" +
    "w e w b20 w b120 w b270 w e w b240\n" +
    "e e b110 e e e e e e b230 b170 b80\n" +
    "w ikey180 w ikey250 w e w e w ikey90 w ikey260";


  public static final String[] MAP_SMALL_CODE = { MAP_SMALL_01_CODE, MAP_SMALL_02_CODE, MAP_SMALL_03_CODE };
  public static final int[][] MAP_SMALL_PLAYER_POSITION = { MAP_SMALL_01_PLAYER_POSITION, MAP_SMALL_02_PLAYER_POSITION, MAP_SMALL_03_PLAYER_POSITION };

  public static final String[] MAP_MEDIUM_CODE = { MAP_MEDIUM_01_CODE, MAP_MEDIUM_02_CODE, MAP_MEDIUM_03_CODE, MAP_MEDIUM_TUTORIAL_CODE };
  public static final int[][] MAP_MEDIUM_PLAYER_POSITION = { MAP_MEDIUM_01_PLAYER_POSITION, MAP_MEDIUM_02_PLAYER_POSITION, MAP_MEDIUM_03_PLAYER_POSITION, MAP_MEDIUM_TUTORIAL_PLAYER_POSITION };

  public static final String[] MAP_LARGE_CODE = { MAP_LARGE_01_CODE, MAP_LARGE_02_CODE, MAP_LARGE_03_CODE };
  public static final int[][] MAP_LARGE_PLAYER_POSITION = { MAP_LARGE_01_PLAYER_POSITION, MAP_LARGE_02_PLAYER_POSITION, MAP_LARGE_03_PLAYER_POSITION };

  public static final String[][] MAP_CODE = { MAP_SMALL_CODE, MAP_MEDIUM_CODE, MAP_LARGE_CODE };
  public static final int[][][] MAP_PLAYER_POSITION = { MAP_SMALL_PLAYER_POSITION, MAP_MEDIUM_PLAYER_POSITION, MAP_LARGE_PLAYER_POSITION };


  public static void consoleLog(String text) {
    System.out.println(readableTimestamp() + text);
  }

  public static void consoleLog(int text) {
    System.out.println(readableTimestamp() + text);
  }

  public static void consoleLog(long text) {
    System.out.println(readableTimestamp() + text);
  }

  public static void consoleLog(boolean text) {
    System.out.println(readableTimestamp() + text);
  }

  public static void consoleLog(double text) {
    System.out.println(readableTimestamp() + text);
  }

  public static void consoleLog(float text) {
    System.out.println(readableTimestamp() + text);
  }

  private static String readableTimestamp() {
    return new SimpleDateFormat("[dd-MM HH:mm.ss,SSS] ").format(new Date());
  }
}
