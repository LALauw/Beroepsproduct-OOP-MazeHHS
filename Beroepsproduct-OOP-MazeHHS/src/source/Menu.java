package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TOP;
import static source.Constants.*;

public class Menu {
  private JFrame frame;

  private String gameName = "MazeGame JLR";
  private double version = 1.0;
  private String versionRelease = "Beta";
  private String versionName = "v" + version + " " + versionRelease;
  private String currentYear = new SimpleDateFormat("YYYY").format(new Date());

  private String[] developerName = { "J. van der Niet", "L. Lauw", "R. Dovidenas" };
  private String[] developerMail = { "18069681@student.hhs.nl", "18151167@student.hhs.nl", "18090257@student.hhs.nl" };

  private boolean darkMode = true;
  private boolean animation = true;

  private final int[] INVENTORY_GRID_SIZE = { 5, 6 };

  private JPanel panelMain = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
  private JPanel panelMainTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
  private JPanelMain panelMainMenu = new JPanelMain(new FlowLayout(FlowLayout.CENTER, 0, 0));
  private JPanel panelMainSide = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
  private JPanel panelMainSideMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
  private JPanel panelMainSideMenuTabs = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 0));
  private JPanelInvent panelMainSideMenuTabsPanelInventory = new JPanelInvent(new FlowLayout(FlowLayout.CENTER, 3, 3));
  private JPanel panelMainSideMenuTabsPanelInventoryGrid = new JPanel(new GridLayout(INVENTORY_GRID_SIZE[0], INVENTORY_GRID_SIZE[1], 4, 4));

  private String[] parentPanelNames = { "main", "game" };
  private JPanel[] parentPanel = { panelMainMenu, panelMainSide };
  private int parentPanelCurrentOpen;

  private JPanel panelBoard = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
  private JPanelEnd panelEnd = new JPanelEnd(new FlowLayout(FlowLayout.CENTER, 0, 0));
  private String[] boardPanelNames = { "game", "end" };
  private JPanel[] boardPanel = { panelBoard, panelEnd };
  private int boardPanelCurrentOpen;

  private final String[] MAIN_MENU_PANEL_NAMES = { "main", "options", "credits" };
  private JPanel[] panelMainMenuPanels = new JPanel[MAIN_MENU_PANEL_NAMES.length];
  private int panelMainMenuPanelCurrentOpen;

  private final String[] MAIN_SIDE_MENU_TAB_NAMES = { "game", "settings", "guide" };
  private JPanel[] panelMainSideMenuTabsPanel = new JPanel[MAIN_SIDE_MENU_TAB_NAMES.length];
  private JLabel[] panelMainSideMenuTabsLabels = new JLabel[MAIN_SIDE_MENU_TAB_NAMES.length];
  private JPanel[] panelMainSideMenuTabsPanelContainer = new JPanel[MAIN_SIDE_MENU_TAB_NAMES.length];
  private int panelMainSideMenuTabsCurrentOpen = 0;

  private final String[] MAIN_SIDE_TAB_GAME_PANEL_NAMES = { "stats", "create" };
  private JPanel[] panelMainSideMenuTabsGamePanel = new JPanel[MAIN_SIDE_TAB_GAME_PANEL_NAMES.length];
  private JPanel panelMainSideMenuTabsGamePanelSeparate;
  private int panelMainSideMenuTabsGameCurrentOpen = 0;

  private JLabel panelMainSideMenuTabsPanelGameLabelCurrentMapSize = new JLabel("Current map size: ...");
  private JLabel panelMainSideMenuTabsPanelGameLabelCurrentLvl = new JLabel("Current level: ...");
  private JLabel panelMainSideMenuTabsPanelGameLabelCounterMoves = new JLabel("Amount of moves: 0");
  private JLabel panelMainSideMenuTabsPanelGameLabelCounterKeys = new JLabel("Amount of keys picked up: 0");
  private JLabel panelMainSideMenuTabsPanelGameLabelCounterBarricades = new JLabel("Amount of barricades opened: 0");
  private JLabel panelMainSideMenuTabsPanelSettingLabelUseArrowKeys = new JLabel("Use arrow keys to move:");

  private JButton panelMainSideMenuTabsPanelGameButtonNewMap = new JButton("New Map");

  private JCheckBox panelMainSideMenuTabsPanelSettingCheckboxDarkMode = new JCheckBox("Dark Mode");

  private JLabel labelMapSize = new JLabel("Choose map size:");
  private JComboBox<String> comboboxMapSize;
  private JLabel labelLevel = new JLabel("Choose level:");
  private JComboBox<String> comboboxLevel;
  private JLabel labelTexture = new JLabel("Choose textures:");
  private JComboBox<String> comboboxTexture;
  private JCheckBox checkboxLimitedKeys = new JCheckBox("Limit 1 key per inventory");
  private JLabel labelInformation = new JLabel();
  private JButton[] buttonMainMenu = new JButton[panelMainSideMenuTabsPanel.length];
  private JButton[] buttonRestartGame = new JButton[panelMainSideMenuTabsPanel.length];
  private JButton buttonGenerate = new JButton("Generate");
  private JButton buttonReset = new JButton("Reset");
  private JButton buttonReturn = new JButton("Return");
  private JButton buttonOptionsDark = new JButton();

  private JLabel panelMainTopLong = new JLabel();
  private JLabel panelMainTopMinimize = new JLabel();
  private JLabel panelMainTopClose = new JLabel();
  private int mouseStartDragPositionX;
  private int mouseStartDragPositionY;

  private JLabel labelEnd = new JLabel("YOU SOLVED THE MAZE!");
  private JLabel[] endLabel = new JLabel[7];

  private Board board = new Board(this);

  /********************************************************************************************************************
   * DISCLAIMER: MazeGameJLR started as a project assignment for java programming during our study at The Hague
   * University of Applied Sciences. This program contains images that are taken from various different sources. We do
   * not own and do not claim to own any of the images appearing in our program. The images belong to their respective
   * owners, who have copyright over them. If you feel that any image violates your copyright, please write to the
   * developers mentioned below to have your images taken down.
   *
   * 18069681@student.hhs.nl (J. van der Niet)
   * 18151167@student.hhs.nl (L. Lauw)
   * 18090257@student.hhs.nl (R. Dovidenas)
   *
   * One or several images were used from a game named RuneScape. RuneScape is a registered trademark of JaGeX Limited.
   * We do not claim, or have any, affiliation with JaGeX Ltd.
   * Consider this, free advertising http://www.runescape.com/
   *
   * One or several images were used from a game named Minecraft. Minecraft is a sandbox construction game created by
   * Mojang AB. We do not claim, or have any, affiliation with Mojang AB.
   * Consider this, free advertising https://www.minecraft.net/
   * ------------------------------------------------------------------------------------------------------------------
   *
   * MazeGameJLR © Copyright 2019
   *
   * @author Juliën van der Niet (18069681)
   * @author Leslie Lauw (18151167)
   * @author Roland Dovidenas (18090257)
   *
   * version 1.0 since 05/04/2019
   *
   * Changelog:
   * - First official release (05/04/2019)
   *
   * ------------------------------------------------------------------------------------------------------------------
   *
   * This constructor creates an instance and sets the properties of this object.
   *
   *******************************************************************************************************************/
  public Menu() {
    consoleLog("creating menu..");

    this.frame = new JFrame("Maze Game");
    this.frame.setMinimumSize(new Dimension(784, 551));
    this.frame.setLocationRelativeTo(null);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setUndecorated(true);
    this.frame.setResizable(false);

    consoleLog("created menu");
  }

  /**
   * This method creates all the required JPanels, JLabels, JButtons, JCheckBox and
   * other JComponents.. for the JFrame to display the window as a complete package.
   */
  public void start() {
    consoleLog("starting start()..");

    JPanel panelFrame = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelFrame.setPreferredSize(new Dimension(784, 551));
    panelFrame.setBackground(COLOR_GRAY125);

    panelMain.setPreferredSize(new Dimension(784, 551));

    panelMainTop.setPreferredSize(new Dimension(784, 49));
    panelMainTop.setBackground(Color.BLACK);

    panelMainTopLong.setPreferredSize(new Dimension(692,49));
    panelMainTopLong.setIcon(IMAGE_ICON_CUSTOM_FRAME);

    panelMainTopMinimize.setPreferredSize(new Dimension(46,49));
    panelMainTopMinimize.setIcon(IMAGE_ICON_MINIMIZE);

    panelMainTopClose.setPreferredSize(new Dimension(46,49));
    panelMainTopClose.setIcon(IMAGE_ICON_EXIT);

    panelMainMenu.setPreferredSize(new Dimension(784, 489));
    panelMainMenu.setBackground(Color.DARK_GRAY);

    JPanel panelMainMenuFiller = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuFiller.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 150));
    panelMainMenuFiller.setBackground(COLOR_TRANSPARENT);

    JButton buttonPlay = new JButton();
    buttonPlay.setPreferredSize(new Dimension(100, 48));
    buttonPlay.setIcon(IMAGE_ICON_MAIN_MENU_PLAY);
    buttonPlay.setBorderPainted(false);
    buttonPlay.setFocusable(false);
    buttonPlay.addActionListener(e -> openParentPanel("game"));

    JPanel panelMainMenuPlay = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuPlay.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 48));
    panelMainMenuPlay.setBackground(COLOR_TRANSPARENT);
    panelMainMenuPlay.add(buttonPlay);

    JButton buttonOptions = new JButton();
    buttonOptions.setPreferredSize(new Dimension(100, 48));
    buttonOptions.setIcon(IMAGE_ICON_MAIN_MENU_OPTIONS);
    buttonOptions.setBorderPainted(false);
    buttonOptions.setFocusable(false);
    buttonOptions.addActionListener(e -> openMainMenuPanel("options"));

    JPanel panelMainMenuOptions = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuOptions.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 48));
    panelMainMenuOptions.setBackground(COLOR_TRANSPARENT);
    panelMainMenuOptions.add(buttonOptions);

    JButton buttonCredits = new JButton();
    buttonCredits.setPreferredSize(new Dimension(100, 48));
    buttonCredits.setIcon(IMAGE_ICON_MAIN_MENU_CREDITS);
    buttonCredits.setBorderPainted(false);
    buttonCredits.setFocusable(false);
    buttonCredits.addActionListener(e -> openMainMenuPanel("credits"));

    JPanel panelMainMenuCredits = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuCredits.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 48));
    panelMainMenuCredits.setBackground(COLOR_TRANSPARENT);
    panelMainMenuCredits.add(buttonCredits);

    JButton buttonQuit = new JButton();
    buttonQuit.setPreferredSize(new Dimension(100, 48));
    buttonQuit.setIcon(IMAGE_ICON_MAIN_MENU_QUIT);
    buttonQuit.setBorderPainted(false);
    buttonQuit.setFocusable(false);
    buttonQuit.addActionListener(e -> closeFrame());

    JPanel panelMainMenuQuit = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuQuit.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 48));
    panelMainMenuQuit.setBackground(COLOR_TRANSPARENT);
    panelMainMenuQuit.add(buttonQuit);

    JPanel panelMainMenuOptionsFiller = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuOptionsFiller.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 150));
    panelMainMenuOptionsFiller.setBackground(COLOR_TRANSPARENT);

    JButton buttonOptionsBack = new JButton();
    buttonOptionsBack.setPreferredSize(new Dimension(100, 48));
    buttonOptionsBack.setIcon(IMAGE_ICON_MAIN_MENU_BACK);
    buttonOptionsBack.setBorderPainted(false);
    buttonOptionsBack.setFocusable(false);
    buttonOptionsBack.addActionListener(e -> openMainMenuPanel("main"));

    JPanel panelMainMenuOptionsBack = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuOptionsBack.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 48));
    panelMainMenuOptionsBack.setBackground(COLOR_TRANSPARENT);
    panelMainMenuOptionsBack.add(buttonOptionsBack);

    JButton buttonOptionsAnim = new JButton();
    buttonOptionsAnim.setFocusable(false);
    buttonOptionsAnim.setPreferredSize(new Dimension(100, 48));
    buttonOptionsAnim.setIcon(animation ? IMAGE_ICON_MAIN_MENU_ANIM_ON : IMAGE_ICON_MAIN_MENU_DARK_OFF);
    buttonOptionsAnim.setBorderPainted(false);
    buttonOptionsAnim.addActionListener(e -> {
      animation = ! animation;
      buttonOptionsAnim.setIcon(animation ? IMAGE_ICON_MAIN_MENU_ANIM_ON : IMAGE_ICON_MAIN_MENU_ANIM_OFF);
    });

    JPanel panelMainMenuOptionsAnim = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuOptionsAnim.setPreferredSize(new Dimension(250, 48));
    panelMainMenuOptionsAnim.setBackground(COLOR_TRANSPARENT);
    panelMainMenuOptionsAnim.add(buttonOptionsAnim);

    buttonOptionsDark.setFocusable(false);
    buttonOptionsDark.setPreferredSize(new Dimension(100, 48));
    buttonOptionsDark.setIcon(darkMode ? IMAGE_ICON_MAIN_MENU_DARK_ON : IMAGE_ICON_MAIN_MENU_DARK_OFF);
    buttonOptionsDark.setBorderPainted(false);
    buttonOptionsDark.addActionListener(e -> setDarkMode(!darkMode));

    JPanel panelMainMenuOptionsDark = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuOptionsDark.setPreferredSize(new Dimension(250, 48));
    panelMainMenuOptionsDark.setBackground(COLOR_TRANSPARENT);
    panelMainMenuOptionsDark.add(buttonOptionsDark);

    JPanel panelMainMenuCreditsFiller = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuCreditsFiller.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 100));
    panelMainMenuCreditsFiller.setBackground(COLOR_TRANSPARENT);

    JPanel panelMainMenuCreditsInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuCreditsInfo.setPreferredSize(new Dimension(680, 305));
    panelMainMenuCreditsInfo.setBackground(new Color(128, 128, 128, 150));

    final int appInfo = 4;
    final Dimension dimensionSplit = new Dimension(panelMainMenuCreditsInfo.getPreferredSize().width / 3 + 1, 13);
    final Dimension dimensionTitle = new Dimension(panelMainMenuCreditsInfo.getPreferredSize().width, 50);
    final Font fontSplit = new Font("Courier New", Font.BOLD, 14);
    final Font fontTitle = new Font("Courier New", Font.BOLD, 18);
    final Color colorSplit = new Color(0, 80, 80);

    JLabel labelAppInfo = new JLabel("Application Information:", CENTER);
    labelAppInfo.setPreferredSize(new Dimension(panelMainMenuCreditsInfo.getPreferredSize().width, 30));
    labelAppInfo.setFont(fontTitle);
    labelAppInfo.setForeground(colorSplit);
    labelAppInfo.setVerticalAlignment(TOP);
    panelMainMenuCreditsInfo.add(labelAppInfo);

    JLabel labelLeft, labelRight;

    for(int i = 0; i < appInfo; i++) {
      labelLeft = new JLabel();
      labelLeft.setPreferredSize(dimensionSplit);
      labelLeft.setFont(fontSplit);
      labelLeft.setForeground(colorSplit);

      labelRight = new JLabel();
      labelRight.setPreferredSize(dimensionSplit);
      labelRight.setFont(fontSplit);
      labelRight.setForeground(colorSplit);

      if(i == 0) {
        labelLeft.setText("Application Name:");
        labelRight.setText(gameName);
      }
      else if(i == 1) {
        labelLeft.setText("Application Version:");
        labelRight.setText(versionName);
      }
      else if(i == 2) {
        labelLeft.setText("Application Creation Date:");
        labelRight.setText("18/03/2019");
      }
      else {
        labelLeft.setText("Application Release Date:");
        labelRight.setText("05/04/2019");
      }

      panelMainMenuCreditsInfo.add(labelLeft);
      panelMainMenuCreditsInfo.add(labelRight);
    }

    JLabel labelDevInfo = new JLabel("Developers:", CENTER);
    labelDevInfo.setPreferredSize(dimensionTitle);
    labelDevInfo.setFont(fontTitle);
    labelDevInfo.setForeground(colorSplit);
    panelMainMenuCreditsInfo.add(labelDevInfo);

    for(int i = 0; i < developerName.length; i++) {
      labelLeft = new JLabel();
      labelLeft.setPreferredSize(dimensionSplit);
      labelLeft.setFont(fontSplit);
      labelLeft.setForeground(colorSplit);

      labelRight = new JLabel();
      labelRight.setPreferredSize(dimensionSplit);
      labelRight.setFont(fontSplit);
      labelRight.setForeground(colorSplit);

      labelLeft.setText(developerName[i]);
      labelRight.setText(developerMail[i]);

      panelMainMenuCreditsInfo.add(labelLeft);
      panelMainMenuCreditsInfo.add(labelRight);
    }

    JLabel labelDisclaimer = new JLabel("Disclaimer:", CENTER);
    labelDisclaimer.setPreferredSize(dimensionTitle);
    labelDisclaimer.setFont(fontTitle);
    labelDisclaimer.setForeground(Color.BLACK);
    panelMainMenuCreditsInfo.add(labelDisclaimer);

    for(int i = 0; i < 6; i++) {
      JLabel label = new JLabel();
      label.setPreferredSize(new Dimension(panelMainMenuCreditsInfo.getPreferredSize().width, 13));
      label.setFont(fontSplit);
      label.setHorizontalAlignment(CENTER);
      label.setForeground(Color.BLACK);

      if(i == 0)
        label.setText(gameName + " started as a project assignment for java programming during our study");
      else if(i == 1)
        label.setText("at The Hague University of Applied Sciences. This program contains images that are");
      else if(i == 2)
        label.setText("taken from various different sources. We do not own and do not claim to own any of");
      else if(i == 3)
        label.setText("the images appearing in our program. The images belong to their respective owners, ");
      else if(i == 4)
        label.setText("who have copyright over them. If you feel that any image violates your copyright,");
      else
        label.setText("please write to the developers mentioned above to have your images taken down.");

      panelMainMenuCreditsInfo.add(label);
    }

    JButton buttonCreditsBack = new JButton();
    buttonCreditsBack.setFocusable(false);
    buttonCreditsBack.setPreferredSize(new Dimension(100, 48));
    buttonCreditsBack.setIcon(IMAGE_ICON_MAIN_MENU_BACK);
    buttonCreditsBack.setBorderPainted(false);
    buttonCreditsBack.addActionListener(e -> openMainMenuPanel("main"));

    JPanel panelMainMenuCreditsBack = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panelMainMenuCreditsBack.setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, 50));
    panelMainMenuCreditsBack.setBackground(COLOR_TRANSPARENT);
    panelMainMenuCreditsBack.add(buttonCreditsBack);

    panelMainSide.setPreferredSize(new Dimension(784, 489));
    panelMainSide.setBackground(Color.LIGHT_GRAY);

    labelInformation.setPreferredSize(new Dimension(panelMain.getPreferredSize().width, 12));
    labelInformation.setFont(new Font("Courier New", Font.PLAIN, 11));
    labelInformation.setHorizontalAlignment(CENTER);
    labelInformation.setText(gameName + " © Copyright 2019" + (currentYear.equals("2019") ? "" : "-" + currentYear));

    panelMainSideMenu.setPreferredSize(new Dimension(287, 482));
    panelMainSideMenu.setBackground(Color.GRAY);

    panelMainSideMenuTabs.setPreferredSize(new Dimension(285, 20));
    panelMainSideMenuTabs.setBackground(Color.DARK_GRAY);

    panelMainSideMenuTabsPanelInventory.setPreferredSize(new Dimension(275, 229));

    panelMainSideMenuTabsPanelInventoryGrid.setPreferredSize(new Dimension(268, 223));

    panelMainSideMenuTabsPanelGameButtonNewMap.setPreferredSize(new Dimension(95,30));
    panelMainSideMenuTabsPanelGameButtonNewMap.setFocusable(false);
    panelMainSideMenuTabsPanelGameButtonNewMap.addActionListener(e -> openSideGamePanel(1));

    panelBoard.setPreferredSize(new Dimension(482, 482));
    panelBoard.setBackground(Color.DARK_GRAY);

    panelEnd.setPreferredSize(new Dimension(482, 482));

    JLabel labelEmpty = new JLabel();
    labelEmpty.setPreferredSize(new Dimension(panelEnd.getPreferredSize().width, 280));

    labelEnd.setPreferredSize(new Dimension(panelEnd.getPreferredSize().width, 45));
    labelEnd.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    labelEnd.setHorizontalAlignment(CENTER);
    labelEnd.setVerticalAlignment(TOP);

    panelEnd.add(labelEmpty);
    panelEnd.add(labelEnd);

    for(int i = 0; i < endLabel.length; i++) {
      endLabel[i] = new JLabel();
      endLabel[i].setPreferredSize(new Dimension(panelEnd.getPreferredSize().width, 17));
      endLabel[i].setFont(new Font("Courier New", Font.BOLD, 12));
      endLabel[i].setHorizontalAlignment(CENTER);

      panelEnd.add(endLabel[i]);
    }

    panelMainTopMinimize.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        minimizeFrame();
        panelMainTopMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        panelMainTopMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelMainTopMinimize.setIcon(IMAGE_ICON_MINIMIZE_HOVER);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        super.mouseEntered(e);
        panelMainTopMinimize.setIcon(IMAGE_ICON_MINIMIZE);
      }
    });

    panelMainTopClose.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        closeFrame();
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        panelMainTopClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelMainTopClose.setIcon(IMAGE_ICON_EXIT_HOVER);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        panelMainTopClose.setIcon(IMAGE_ICON_EXIT);
      }
    });

    for(int i = 0; i < panelMainMenuPanels.length; i++) {
      panelMainMenuPanels[i] = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
      panelMainMenuPanels[i].setPreferredSize(new Dimension(panelMainMenu.getPreferredSize().width, panelMainMenu.getPreferredSize().height));
      panelMainMenuPanels[i].setOpaque(false);

      if(MAIN_MENU_PANEL_NAMES[i].equals("main")) {
        panelMainMenuPanels[i].add(panelMainMenuFiller);
        panelMainMenuPanels[i].add(panelMainMenuPlay);
        panelMainMenuPanels[i].add(panelMainMenuOptions);
        panelMainMenuPanels[i].add(panelMainMenuCredits);
        panelMainMenuPanels[i].add(panelMainMenuQuit);
      }
      else if(MAIN_MENU_PANEL_NAMES[i].equals("options")) {
        panelMainMenuPanels[i].add(panelMainMenuOptionsFiller);
        panelMainMenuPanels[i].add(panelMainMenuOptionsAnim);
        panelMainMenuPanels[i].add(panelMainMenuOptionsDark);
        panelMainMenuPanels[i].add(panelMainMenuOptionsBack);
      }
      else if(MAIN_MENU_PANEL_NAMES[i].equals("credits")) {
        panelMainMenuPanels[i].add(panelMainMenuCreditsFiller);
        panelMainMenuPanels[i].add(panelMainMenuCreditsInfo);
        panelMainMenuPanels[i].add(panelMainMenuCreditsBack);

//        JLabel labeltje = new JLabel("Credits");
//        labeltje.setFont(new Font("Tahoma", Font.BOLD, 24));
//        labeltje.setForeground(Color.CYAN);
//        panelMainMenuPanels[i].add(labeltje);
      }

      panelMainMenu.add(panelMainMenuPanels[i]);
    }

    for(int i = 0; i < panelMainSideMenuTabsLabels.length; i++) {
      final int x = panelMainSideMenuTabs.getPreferredSize().width / panelMainSideMenuTabsLabels.length;
      final int fi = i;
      panelMainSideMenuTabsLabels[i] = new JLabel(MAIN_SIDE_MENU_TAB_NAMES[i].substring(0, 1).toUpperCase() + MAIN_SIDE_MENU_TAB_NAMES[i].substring(1).toLowerCase());
      panelMainSideMenuTabsLabels[i].setPreferredSize(new Dimension(x - 1, 20));
      panelMainSideMenuTabsLabels[i].setHorizontalAlignment(CENTER);
      panelMainSideMenuTabsLabels[i].setOpaque(true);
      panelMainSideMenuTabsLabels[i].addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
          if(fi == panelMainSideMenuTabsCurrentOpen)
            panelMainSideMenuTabsLabels[fi].setBackground(darkMode ? COLOR_TAB_DARK_SELECTED_HIGHLIGHT : COLOR_TAB_LIGHT_SELECTED_HIGHLIGHT);
          else
            panelMainSideMenuTabsLabels[fi].setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED_HIGHLIGHT : COLOR_TAB_LIGHT_UNSELECTED_HIGHLIGHT);
        }

        @Override
        public void mousePressed(MouseEvent e) {
          openSideTab(fi);
          recolorEntireFrame();
          mouseEntered(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
          recolorEntireFrame();
        }
      });

      panelMainSideMenuTabs.add(panelMainSideMenuTabsLabels[i]);
    }

    panelMainSideMenu.add(panelMainSideMenuTabs);

    for(int i = 0; i < panelMainSideMenuTabsPanelContainer.length; i++) {
      panelMainSideMenuTabsPanel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
      panelMainSideMenuTabsPanel[i].setPreferredSize(new Dimension(285, 460));
      panelMainSideMenuTabsPanelContainer[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 3));
      panelMainSideMenuTabsPanelContainer[i].setPreferredSize(new Dimension(panelMainSideMenuTabsPanel[i].getPreferredSize().width - 10, panelMainSideMenuTabsPanel[i].getPreferredSize().height - 35 - 10));

      if(MAIN_SIDE_MENU_TAB_NAMES[i].equals("game")) {
        panelMainSideMenuTabsGamePanelSeparate = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelMainSideMenuTabsGamePanelSeparate.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width, panelMainSideMenuTabsPanelContainer[i].getPreferredSize().height));

        for(int j = 0; j < panelMainSideMenuTabsGamePanel.length; j++) {
          panelMainSideMenuTabsGamePanel[j] = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
          panelMainSideMenuTabsGamePanel[j].setPreferredSize(new Dimension(panelMainSideMenuTabsPanel[i].getPreferredSize().width - 10, panelMainSideMenuTabsPanel[i].getPreferredSize().height - 10));

          if(MAIN_SIDE_TAB_GAME_PANEL_NAMES[j].equals("stats")) {
            panelMainSideMenuTabsPanelContainer[i].setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width,
              panelMainSideMenuTabsPanelContainer[i].getPreferredSize().height - panelMainSideMenuTabsPanelInventory.getPreferredSize().height));
            panelMainSideMenuTabsPanelGameLabelCurrentMapSize.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width, 16));
            panelMainSideMenuTabsPanelGameLabelCurrentLvl.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width, 16));
            panelMainSideMenuTabsPanelGameLabelCounterMoves.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width, 16));
            panelMainSideMenuTabsPanelGameLabelCounterKeys.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width, 16));
            panelMainSideMenuTabsPanelGameLabelCounterBarricades.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width, 16));

            for(int k = 0; k < INVENTORY_GRID_SIZE[0] * INVENTORY_GRID_SIZE[1]; k++) {
              JLabel label = new JLabel();
              label.setHorizontalAlignment(JLabel.CENTER);
              label.setBackground(new Color(0, true));
              label.setOpaque(true);
//              endLabel.setToolTipText("inventory slot " + k);
              panelMainSideMenuTabsPanelInventoryGrid.add(label);
            }

            panelMainSideMenuTabsPanelContainer[i].add(panelMainSideMenuTabsPanelGameLabelCurrentMapSize);
            panelMainSideMenuTabsPanelContainer[i].add(panelMainSideMenuTabsPanelGameLabelCurrentLvl);
            panelMainSideMenuTabsPanelContainer[i].add(panelMainSideMenuTabsPanelGameLabelCounterMoves);
            panelMainSideMenuTabsPanelContainer[i].add(panelMainSideMenuTabsPanelGameLabelCounterKeys);
            panelMainSideMenuTabsPanelContainer[i].add(panelMainSideMenuTabsPanelGameLabelCounterBarricades);
            panelMainSideMenuTabsPanelContainer[i].add(panelMainSideMenuTabsPanelGameButtonNewMap);
          }
          else if(MAIN_SIDE_TAB_GAME_PANEL_NAMES[j].equals("create")) {
            panelMainSideMenuTabsGamePanel[j].setLayout(new FlowLayout(FlowLayout.LEFT, 3, 5));
            labelMapSize.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width - 90, 20));

            String[] arrayStringMapSize = new String[MapSize.values().length];

            for(int k = 0; k < arrayStringMapSize.length; k++) {
              arrayStringMapSize[k] = MapSize.values()[k].toString().replaceAll("_", " ");
              arrayStringMapSize[k] = arrayStringMapSize[k].substring(0, 1).toUpperCase() + arrayStringMapSize[k].substring(1).toLowerCase();
            }

            comboboxMapSize = new JComboBox<>(arrayStringMapSize);
            comboboxMapSize.setPreferredSize(new Dimension(80,20));
            comboboxMapSize.setFocusable(false);
            comboboxMapSize.addActionListener(e -> resetLevelSelection());

            labelLevel.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width - 90, 20));

            comboboxLevel = new JComboBox<>(new String[] { "loading" });
            comboboxLevel.setPreferredSize(new Dimension(80,20));
            comboboxLevel.setFocusable(false);

            labelTexture.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width - 90, 20));

            String[] arrayStringTexture = new String[Texture.values().length];

            for(int k = 0; k < arrayStringTexture.length; k++) {
              arrayStringTexture[k] = Texture.values()[k].toString().replaceAll("_", " ");
              arrayStringTexture[k] = arrayStringTexture[k].substring(0, 1).toUpperCase() + arrayStringTexture[k].substring(1).toLowerCase();
            }

            comboboxTexture = new JComboBox<>(arrayStringTexture);
            comboboxTexture.setPreferredSize(new Dimension(80,20));
            comboboxTexture.setFocusable(false);

            checkboxLimitedKeys.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width, 18));
            checkboxLimitedKeys.setFocusable(false);

            buttonGenerate.setPreferredSize(new Dimension(150,25));
            buttonGenerate.setFocusable(false);
            buttonGenerate.addActionListener(e -> {
              consoleLog("Generating..");
              board.setupNextMap(comboboxMapSize.getSelectedIndex(), comboboxLevel.getSelectedIndex(), comboboxTexture.getSelectedIndex(), checkboxLimitedKeys.isSelected());
              openSideGamePanel(0);
              openBoardPanel("game");
            });

            buttonReset.setPreferredSize(new Dimension(150,25));
            buttonReset.setFocusable(false);
            buttonReset.addActionListener(e -> {
              consoleLog("Resetting..");
              setDefaultGameOptions();
            });

            buttonReturn.setPreferredSize(new Dimension(150,25));
            buttonReturn.setFocusable(false);
            buttonReturn.addActionListener(e -> {
              consoleLog("Returning to main tab");
              openSideGamePanel(0);
            });

            panelMainSideMenuTabsGamePanel[j].add(labelMapSize);
            panelMainSideMenuTabsGamePanel[j].add(comboboxMapSize);
            panelMainSideMenuTabsGamePanel[j].add(labelLevel);
            panelMainSideMenuTabsGamePanel[j].add(comboboxLevel);
            panelMainSideMenuTabsGamePanel[j].add(labelTexture);
            panelMainSideMenuTabsGamePanel[j].add(comboboxTexture);
            panelMainSideMenuTabsGamePanel[j].add(checkboxLimitedKeys);
            panelMainSideMenuTabsGamePanel[j].add(buttonGenerate);
            panelMainSideMenuTabsGamePanel[j].add(buttonReset);
            panelMainSideMenuTabsGamePanel[j].add(buttonReturn);
          }
        }
      }
      else if(MAIN_SIDE_MENU_TAB_NAMES[i].equals("settings")) {
        panelMainSideMenuTabsPanelSettingCheckboxDarkMode.setSelected(darkMode);

        panelMainSideMenuTabsPanelSettingCheckboxDarkMode.setPreferredSize(new Dimension(90, 20));

        panelMainSideMenuTabsPanelSettingCheckboxDarkMode.setFocusable(false); //disable the use of arrow keys

        panelMainSideMenuTabsPanelContainer[i].add(panelMainSideMenuTabsPanelSettingCheckboxDarkMode);

        panelMainSideMenuTabsPanelSettingCheckboxDarkMode.addItemListener(e -> setDarkMode(e.getStateChange() == ItemEvent.SELECTED));
      }
      else if(MAIN_SIDE_MENU_TAB_NAMES[i].equals("guide")) {
        JLabel labelKeys = new JLabel();
        labelKeys.setIcon(new ImageIcon(IMAGE_ICON_GUIDE_KEYS.getImage().getScaledInstance(145, 100, Image.SCALE_SMOOTH)));

        JLabel labelCode = new JLabel();
        labelCode.setIcon(new ImageIcon(IMAGE_ICON_GUIDE_CODE.getImage()));

        panelMainSideMenuTabsPanelSettingLabelUseArrowKeys.setPreferredSize(new Dimension(panelMainSideMenuTabsPanelContainer[i].getPreferredSize().width, 20));

        panelMainSideMenuTabsPanelContainer[i].add(panelMainSideMenuTabsPanelSettingLabelUseArrowKeys);
        panelMainSideMenuTabsPanelContainer[i].add(labelKeys);
        panelMainSideMenuTabsPanelContainer[i].add(labelCode);
      }

      buttonMainMenu[i] = new JButton("Main Menu");
      buttonMainMenu[i].setPreferredSize(new Dimension(135,30));
      buttonMainMenu[i].setFocusable(false);
      buttonMainMenu[i].addActionListener(e -> openParentPanel("main"));

      buttonRestartGame[i] = new JButton("Restart Game");
      buttonRestartGame[i].setPreferredSize(new Dimension(buttonMainMenu[i].getPreferredSize().width,buttonMainMenu[i].getPreferredSize().height));
      buttonRestartGame[i].setFocusable(false);
      buttonRestartGame[i].addActionListener(e -> board.setupRestartMap());

      if(MAIN_SIDE_MENU_TAB_NAMES[i].equals("game")) {
        for(int j = 0; j < panelMainSideMenuTabsGamePanel.length; j++) {

          if(MAIN_SIDE_TAB_GAME_PANEL_NAMES[j].equals("stats")) {
            panelMainSideMenuTabsPanelInventory.add(panelMainSideMenuTabsPanelInventoryGrid);
            panelMainSideMenuTabsGamePanel[j].add(panelMainSideMenuTabsPanelContainer[i]);
            panelMainSideMenuTabsGamePanel[j].add(panelMainSideMenuTabsPanelInventory);
          }

          panelMainSideMenuTabsGamePanelSeparate.add(panelMainSideMenuTabsGamePanel[j]);
        }

        panelMainSideMenuTabsPanel[i].add(panelMainSideMenuTabsGamePanelSeparate);
      }
      else
        panelMainSideMenuTabsPanel[i].add(panelMainSideMenuTabsPanelContainer[i]);

      panelMainSideMenuTabsPanel[i].add(buttonMainMenu[i]);
      panelMainSideMenuTabsPanel[i].add(buttonRestartGame[i]);

      panelMainSideMenu.add(panelMainSideMenuTabsPanel[i]);
    }

    panelMainTop.add(panelMainTopLong);
    panelMainTop.add(panelMainTopMinimize);
    panelMainTop.add(panelMainTopClose);

    panelMainTopLong.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        frame.setLocation(e.getXOnScreen() - mouseStartDragPositionX, e.getYOnScreen() - mouseStartDragPositionY);
      }
    });

    panelMainTopLong.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        mouseStartDragPositionX = frame.getMousePosition().x;
        mouseStartDragPositionY = frame.getMousePosition().y;
      }
    });

    panelBoard.add(board);

    panelMainSide.add(panelMainSideMenu);
    panelMainSide.add(panelBoard);
    panelMainSide.add(panelEnd);

    panelMain.add(panelMainTop);
    panelMain.add(panelMainSide);
    panelMain.add(panelMainMenu);
    panelMain.add(labelInformation);

    panelFrame.add(panelMain);

    openParentPanel("main");
    openBoardPanel("game");
    openMainMenuPanel("main");
    recolorEntireFrame();
    setDefaultGameOptions();
    resetLevelSelection();
    openSideGamePanel(panelMainSideMenuTabsGameCurrentOpen);
    openSideTab(panelMainSideMenuTabsCurrentOpen);

    updateLabelLevel();
    updateLabelMapSize();
    updateEndLabels();

    frame.setContentPane(panelFrame);
    frame.setVisible(true);

    consoleLog("started start()");
  }

  /**
   * Sets the selection/checkbox marked of the JComboBox and JCheckBox to the default settings.
   *
   * @see #comboboxMapSize
   * @see #comboboxTexture
   * @see #checkboxLimitedKeys
   */
  public void setDefaultGameOptions() {
    comboboxMapSize.setSelectedIndex(1);
    comboboxTexture.setSelectedIndex(0);
    checkboxLimitedKeys.setSelected(true);
  }

  /**
   * This method removes all of the current items that are added to comboboxLevel
   * It will then add the items that are returned from Map#getLevelNames(int)
   *
   * @see Map#getLevelNames(int)
   */
  public void resetLevelSelection() {
    final String[] strings = board.getMap().getLevelNames(comboboxMapSize.getSelectedIndex());

    comboboxLevel.removeAllItems();
    for(int i = 0; i < strings.length; i++) {
      comboboxLevel.addItem(strings[i]);
    }
  }

  /**
   * This method is meant for setting the state of the darkMode function.
   *
   * When darkMode is active (true), it calls another method called 'recolorEntireFrame()'
   * it then checks the checkbox DarkMode and sets another image for the Main Menu button darkMode.
   *
   * @param active
   * @see Menu#recolorEntireFrame()
   */
  public void setDarkMode(boolean active) {
    darkMode = active;
    recolorEntireFrame();
    panelMainSideMenuTabsPanelSettingCheckboxDarkMode.setSelected(darkMode);
    buttonOptionsDark.setIcon(darkMode ? IMAGE_ICON_MAIN_MENU_DARK_ON : IMAGE_ICON_MAIN_MENU_DARK_OFF);
  }

  /**
   * This method is meant to update the Label CurrentMapSize, which is shown in the game panel.
   *
   * It then takes the MapSize enum from the constants class and converts the first character of the String to an UppderCase
   * and the remaining character to LowerCase.
   *
   * After that it changes the text in the label.
   */
  public void updateLabelMapSize() {
    String nameMapSize = MapSize.values()[board.getMap().getSizeMap()].toString().replaceAll("_", " ");
    nameMapSize = nameMapSize.substring(0, 1).toUpperCase() + nameMapSize.substring(1).toLowerCase();

    panelMainSideMenuTabsPanelGameLabelCurrentMapSize.setText("Current map size: " + nameMapSize);
  }

  /**
   * This method is meant to update the Label CurrentLvl, which is shown in the game panel.
   *
   * It changes the text in the label and also calls the method in the Map class getLevelName.
   *
   * @see Map#getLevelName(int, int)
   */
  public void updateLabelLevel() {
    panelMainSideMenuTabsPanelGameLabelCurrentLvl.setText("Current level: " + board.getMap().getLevelName(board.getMap().getSizeMap(), board.getMap().getLevel()));
  }

  /**
   * This method is meant for updating the Label CounterMoves, which is shown in the game panel.
   *
   * It calls the getCountMoves() method from the Player class and changes the text of the CounterMoves Label.
   *
   * @see Player#getCountMoves()
   */
  public void updateCountMoves() {
    panelMainSideMenuTabsPanelGameLabelCounterMoves.setText("Amount of moves: " + board.getPlayer().getCountMoves());
  }

  /**
   * This method is meant for updating the Label CounterKeys, which is shown in the game panel.
   * It calls the getCountPickedUpKeys() method from the Inventory class and changes the text of the CounterKeys Label.
   *
   * @see Inventory#getCountPickedUpKeys()
   */
  public void updateCountKeys() {
    panelMainSideMenuTabsPanelGameLabelCounterKeys.setText("Amount of keys picked up: " + board.getPlayer().getInventory().getCountPickedUpKeys());
  }

  /**
   * This method is meant for updating the Label CounterBarricades, which is shown in the game panel.
   *
   * It calls the getCountBarricadesOpened() method from the Player class and changes the text of the CounterBarricades Label.
   *
   * @see Player#getCountBarricadesOpened()
   */
  public void updateCountBarricades() {
    panelMainSideMenuTabsPanelGameLabelCounterBarricades.setText("Amount of barricades opened: " + board.getPlayer().getCountBarricadesOpened());
  }

  /**
   * This method is meant for updating the endLabel[7] in the victory screen.
   *
   * This whole method updates 7 labels and changes it's text correspondingly to the values of the Level Statistics.
   *
   * @see Map#getSizeMap()
   * @see Map#getTimestampMapCreated()
   * @see Map#getLevel()
   * @see Inventory#getCountPickedUpKeys()
   * @see Player#getCountBarricadesOpened()
   * @see Player#getCountMoves()
   */
  public void updateEndLabels() {
    for(int i = 0; i < endLabel.length; i++) {
      if(i == 0)
        endLabel[i].setText("You finished a " + MapSize.values()[board.getMap().getSizeMap()].toString().toLowerCase() +
          " sized map named [" + board.getMap().getLevelName(board.getMap().getSizeMap(), board.getMap().getLevel()) + "].");
      else if(i == 1) {
        final int seconds = (int) ((System.currentTimeMillis() - board.getMap().getTimestampMapCreated()) / 1000);
        String time = seconds % 60 + " second" + (seconds % 60 == 1 ? "" : "s");

        if(seconds >= 60)
          time = seconds / 60 + " minute" + (seconds / 60 == 1 ? "" : "s") + " and " + time;

        endLabel[i].setText("You took roughly " + time + " to find your path.");
      }
      else if(i == 2)
        endLabel[i].setText("You picked up " + board.getPlayer().getInventory().getCountPickedUpKeys() + " keys and opened " + board.getPlayer().getCountBarricadesOpened() + " barricades.");
      else if(i == 3)
        endLabel[i].setText("Which took you total of " + board.getPlayer().getCountMoves() + " moves to complete this game.");
      else if(i == 4) {
        endLabel[i].setText("");
        endLabel[i].setPreferredSize(new Dimension(panelEnd.getPreferredSize().width, endLabel[endLabel.length-1].getPreferredSize().height * 2));
      }
      else if(i == 5)
        endLabel[i].setText("If you would like to restart or try a different map, open");
      else {
        endLabel[i].setText("the \"Game\" tab and select a button to start another!");
      }
    }
  }

  /**
   * This method is meant to give focus to the game panel. If they both equal "game" the method calls another method from the Board class, requestFocus().
   *
   * @see Board#requestFocus()
   */
  public void checkFocus() {
    board.setFocusable(boardPanelNames[boardPanelCurrentOpen].equals("game") && parentPanelNames[parentPanelCurrentOpen].equals("game"));

    if(boardPanelNames[boardPanelCurrentOpen].equals("game") && parentPanelNames[parentPanelCurrentOpen].equals("game"))
      board.requestFocus();
  }

  /**
   * This method is meant to opens the the panel with the corresponding parameter name.
   *
   * It then calls the checkFocus method from Menu
   *
   * @param name
   *
   * @see Menu#checkFocus()
   */
  public void openParentPanel(String name) {
    for(int i = 0; i < parentPanel.length; i++) {
      if(parentPanelNames[i].equals(name)) {
        parentPanelCurrentOpen = i;
      }
    }

    for(int i = 0; i < parentPanel.length; i++)
      parentPanel[i].setVisible(parentPanelCurrentOpen == i);

    checkFocus();
  }

  /**
   * This method is meant for opening the board panel with the corresponding parameter name {"game", "end"}.
   *
   * It then sets the boar panel visible and calls the checkFocus() method from Menu
   *
   * @param name
   *
   * @see Menu#checkFocus()
   */
  public void openBoardPanel(String name) {
    for(int i = 0; i < boardPanel.length; i++) {
      if(boardPanelNames[i].equals(name)) {
        boardPanelCurrentOpen = i;
      }
    }

    for(int i = 0; i < boardPanel.length; i++)
      boardPanel[i].setVisible(boardPanelCurrentOpen == i);

    checkFocus();
  }

  /**
   * This method opens the correct panel inside the main menu with the corresponding parameter name {"main", "options", "credits"}
   *
   * It then sets the panel visible.
   *
   * @param name
   */
  public void openMainMenuPanel(String name) {
    for(int i = 0; i < MAIN_MENU_PANEL_NAMES.length; i++) {
      if(MAIN_MENU_PANEL_NAMES[i].equals(name)) {
        panelMainMenuPanelCurrentOpen = i;
      }
    }

    for(int i = 0; i < panelMainMenuPanels.length; i++)
      panelMainMenuPanels[i].setVisible(panelMainMenuPanelCurrentOpen == i);
  }

  /**
   * This method is meant for opening the correct sidePanel corresponding to the given parameter index.
   *
   * It then sets the sidePanel visible.
   *
   * @param index
   */
  public void openSideTab(int index) {
    panelMainSideMenuTabsCurrentOpen = index;

    for(int i = 0; i < panelMainSideMenuTabsPanel.length; i++)
      panelMainSideMenuTabsPanel[i].setVisible(index == i);
  }

   /**
   * This method is meant for opening the correct gamePanel tab in the "Game" tab corresponding to the given parameter index.
   *
   * It the sets the gamePanel visible.
   *
   * @param index
   */
  public void openSideGamePanel(int index) {
    panelMainSideMenuTabsGameCurrentOpen = index;

    for(int i = 0; i < panelMainSideMenuTabsGamePanel.length; i++)
      panelMainSideMenuTabsGamePanel[i].setVisible(index == i);
  }

  /**
   * This method is meant for minimizing the frame.
   *
   * We are using a custom made frame, therefore we have to give the Labels methods that minimize the frame.
   */
  public void minimizeFrame() {
    frame.setState(Frame.ICONIFIED);
  }

  /**
   * This method is meant closing the game. We are using a custom made frame, therefor we have to give the Labels
   * methods that close the frame.
   */
  public void closeFrame() {
    consoleLog("Closing game");
    System.exit(0);
  }

  /**
   * This method repaints the inventory when board.setupNextMap is called and when the player picks up a new key. (pickedupItem)
   * This method repaints the inventory when is called and when the player picks up a new key.
   */
  public void repaintInventory() {
    panelMainSideMenuTabsPanelInventory.repaint();
  }

  /**
   * This method repaints the entire frame, when darkmode is turned on or off the frame needs another color, when the
   * finish line is reached by the player there is a celebration message and when another tab(game, settings, guide is
   * selected).
   */
  public void recolorEntireFrame() {
    final boolean darkMode = this.darkMode;
    final Color textColor = darkMode ? COLOR_TEXT_DARK : COLOR_TEXT_LIGHT;

    panelMain.setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_TAB_LIGHT_SELECTED);
    panelMainTop.setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_TAB_LIGHT_SELECTED);
    panelMainSide.setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_TAB_LIGHT_SELECTED);
    board.setBorder(BorderFactory.createLineBorder(darkMode ? COLOR_GRAY165 : COLOR_GRAY60, 1));
    panelEnd.setBorder(BorderFactory.createLineBorder(darkMode ? COLOR_GRAY165 : COLOR_GRAY60, 1));
    panelMainSideMenu.setBorder(BorderFactory.createLineBorder(darkMode ? COLOR_GRAY165 : COLOR_GRAY60, 1));
    panelMainSideMenuTabs.setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_TAB_LIGHT_SELECTED);
    panelMainSideMenuTabsPanelInventory.setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);
    panelMainSideMenuTabsPanelInventoryGrid.setBackground(darkMode ? COLOR_TRANSPARENT : COLOR_TRANSPARENT);
    panelMainSideMenuTabsPanelGameLabelCurrentMapSize.setForeground(textColor);
    panelMainSideMenuTabsPanelGameLabelCurrentLvl.setForeground(textColor);
    panelMainSideMenuTabsPanelGameLabelCounterMoves.setForeground(textColor);
    panelMainSideMenuTabsPanelGameLabelCounterKeys.setForeground(textColor);
    panelMainSideMenuTabsPanelGameLabelCounterBarricades.setForeground(textColor);
    panelMainSideMenuTabsPanelGameButtonNewMap.setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_GRAY195);
    panelMainSideMenuTabsPanelGameButtonNewMap.setForeground(textColor);
    panelMainSideMenuTabsPanelSettingCheckboxDarkMode.setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);
    panelMainSideMenuTabsPanelSettingCheckboxDarkMode.setForeground(textColor);
    panelMainSideMenuTabsPanelSettingLabelUseArrowKeys.setForeground(textColor);
    panelEnd.setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);

    labelMapSize.setForeground(textColor);
    comboboxMapSize.setBackground(darkMode ? COLOR_GRAY70 : COLOR_TAB_LIGHT_SELECTED);
    comboboxMapSize.setForeground(textColor);
    labelLevel.setForeground(textColor);
    comboboxLevel.setBackground(darkMode ? COLOR_GRAY70 : COLOR_TAB_LIGHT_SELECTED);
    comboboxLevel.setForeground(textColor);
    labelTexture.setForeground(textColor);
    comboboxTexture.setBackground(darkMode ? COLOR_GRAY70 : COLOR_TAB_LIGHT_SELECTED);
    comboboxTexture.setForeground(textColor);
    checkboxLimitedKeys.setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);
    checkboxLimitedKeys.setForeground(textColor);
    buttonGenerate.setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_GRAY195);
    buttonGenerate.setForeground(textColor);
    buttonReset.setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_GRAY195);
    buttonReset.setForeground(textColor);
    buttonReturn.setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_GRAY195);
    buttonReturn.setForeground(textColor);
    labelInformation.setForeground(COLOR_GRAY125);

    for(int i = 0; i < panelMainSideMenuTabsLabels.length; i++) {
      if(i == panelMainSideMenuTabsCurrentOpen)
        panelMainSideMenuTabsLabels[i].setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);
      else
        panelMainSideMenuTabsLabels[i].setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_TAB_LIGHT_UNSELECTED);

      panelMainSideMenuTabsLabels[i].setForeground(darkMode ? COLOR_TEXT_DARK : COLOR_TEXT_LIGHT);
      panelMainSideMenuTabsPanel[i].setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);
      panelMainSideMenuTabsPanelContainer[i].setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);

      buttonMainMenu[i].setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_GRAY195);
      buttonMainMenu[i].setForeground(textColor);
      buttonRestartGame[i].setBackground(darkMode ? COLOR_TAB_DARK_UNSELECTED : COLOR_GRAY195);
      buttonRestartGame[i].setForeground(textColor);
    }

    for(int i = 0; i < panelMainSideMenuTabsGamePanel.length; i++) {
      panelMainSideMenuTabsGamePanel[i].setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);
    }

    labelEnd.setForeground(textColor);
    for(int i = 0; i < endLabel.length; i++) {
      endLabel[i].setForeground(textColor);
    }

    panelMainSideMenuTabsGamePanelSeparate.setBackground(darkMode ? COLOR_TAB_DARK_SELECTED : COLOR_TAB_LIGHT_SELECTED);
  }

  public class JPanelInvent extends JPanel {
    public JPanelInvent(LayoutManager layout) {
      super(layout);
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      final int gap = 4;

      for(int col = 0; col < INVENTORY_GRID_SIZE[0]; col++) {
        for(int row = 0; row < INVENTORY_GRID_SIZE[1]; row++) {
          final int i = col * INVENTORY_GRID_SIZE[1] + row;
          final int startX = row * (41 + gap) + 4;
          final int startY = col * (41 + gap) + 4;

          if(board.getPlayer().getInventory().getItems().size() > i) {
            final Item item = board.getPlayer().getInventory().getItems().get(i);

            if(item != null) {
              g.drawImage(item.getImageInventory(), startX, startY, this);

              if(item.getValue() != -1) {
                g.setFont(new Font("Tahoma", Font.BOLD, 9));
                g.setColor(Color.ORANGE);
                g.drawString(String.valueOf(item.getValue()), startX + 2, startY + 10);
              }
            }
          }

          g.setColor(darkMode ? COLOR_GRAY140 : COLOR_GRAY80);
          g.drawRect(startX, startY, 40, 40);
        }
      }
    }
  }

  public class JPanelMain extends JPanel {
    public JPanelMain(LayoutManager layout) {
      super(layout);
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      if(MAIN_MENU_PANEL_NAMES[panelMainMenuPanelCurrentOpen].equals("main")) {
        if(animation)
          g.drawImage(darkMode ? IMAGE_GIF_MAIN_MENU_DARK : IMAGE_GIF_MAIN_MENU_LIGHT, 0, 0, this);
        else
          g.drawImage(darkMode ? IMAGE_MAIN_MENU_DARK : IMAGE_MAIN_MENU_LIGHT, 0, 0, this);

        Board.DrawStringOutline(g, new Font("Tahoma", Font.BOLD, 24), 301, 64, gameName,
          darkMode ? Color.WHITE : Color.CYAN, darkMode ? Color.BLACK : Color.BLUE);
      }
      else if(MAIN_MENU_PANEL_NAMES[panelMainMenuPanelCurrentOpen].equals("options")) {
        if(animation)
          g.drawImage(darkMode ? IMAGE_GIF_MAIN_OPTIONS_DARK : IMAGE_GIF_MAIN_OPTIONS_LIGHT, 0, 0, this);
        else
          g.drawImage(darkMode ? IMAGE_MAIN_OPTIONS_DARK : IMAGE_MAIN_OPTIONS_LIGHT, 0, 0, this);

        Board.DrawStringOutline(g, new Font("Tahoma", Font.BOLD, 24), 343, 64, "Options",
          darkMode ? Color.WHITE : Color.CYAN, darkMode ? Color.BLACK : Color.BLUE);
      }
      else if(MAIN_MENU_PANEL_NAMES[panelMainMenuPanelCurrentOpen].equals("credits")) {
        if(animation)
          g.drawImage(darkMode ? IMAGE_GIF_MAIN_OPTIONS_DARK : IMAGE_GIF_MAIN_OPTIONS_LIGHT, 0, 0, this);
        else
          g.drawImage(darkMode ? IMAGE_MAIN_OPTIONS_DARK : IMAGE_MAIN_OPTIONS_LIGHT, 0, 0, this);

        Board.DrawStringOutline(g, new Font("Tahoma", Font.BOLD, 24), 348, 64, "Credits",
          darkMode ? Color.WHITE : Color.CYAN, darkMode ? Color.BLACK : Color.BLUE);
      }
    }
  }

  public class JPanelEnd extends JPanel {
    public JPanelEnd(LayoutManager layout) {
      super(layout);
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.drawImage(IMAGE_GIF_END_SCREEN, 0, 0, this);
    }
  }
}
