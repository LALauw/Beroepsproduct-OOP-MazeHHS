package source;

import javax.swing.*;
import java.awt.*;

import static source.Constants.*;

public class Item {
  private static int itemCounter = 0;
  private int id;
  private String name;
  private Tile tile;
  private Inventory inventory;
  private Image imageTile;
  private Image imageInventory;
  private int value;

  /**
   * This constructor creates an instance and sets the properties of this object. This object has no relations to any
   * other object by default.
   *
   * @param name is a String which becomes the name of this object.
   */
  public Item(String name) {
    this.id = itemCounter++;
    this.name = name;
    this.tile = null;
    this.imageTile = null;
    this.imageInventory = null;
    this.inventory = null;
    this.value = -1;
  }

  /**
   * This constructor creates an instance and sets the properties of this object. This object has a relation to Tile.
   *
   * @param name is a String which becomes the name of this object.
   * @param tile is a Tile to which object is related to.
   */
  public Item(String name, Tile tile) {
    this.id = itemCounter++;
    this.name = name;
    this.tile = tile;
    this.imageTile = null;
    this.imageInventory = null;
    this.inventory = null;
    this.value = -1;
  }

  public static int getItemCounter() {
    return itemCounter;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Tile getTile() {
    return tile;
  }

  public void setTile(Tile tile) {
    this.tile = tile;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public Image getImageTile() {
    return imageTile;
  }

  public void setImageTile(Image imageTile) {
    this.imageTile = imageTile;
  }

  public Image getImageInventory() {
    return imageInventory;
  }

  public void setImageInventory(Image imageInventory) {
    this.imageInventory = imageInventory;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

/**
 * This method converts an 'ImageIcon' to an 'Image' using other methods within this class.
 *
 * @param imageIcon is an ImageIcon that is passed to other methods to set them appropriately
 *
 * @see #setImageTile(ImageIcon)
 * @see #setImageInventory(ImageIcon)
 */
  public void setImage(ImageIcon imageIcon) {
    setImageTile(imageIcon);
    setImageInventory(imageIcon);
  }

  /**
   * setImageTile(ImageIcon) converts the 'ImageIcon' to an 'Image' while resizing Image to the
   * appropriate image tile size based on the board tile size.
   *
   * @param imageIcon is an ImageIcon that is going to be converted to an Image and resized appropriately
   *
   * @see Board#rescaleImageIconToImage(ImageIcon)
   */
  public void setImageTile(ImageIcon imageIcon) {
    imageTile = tile.getMap().getBoard().rescaleImageIconToImage(imageIcon);
  }

  /**
   * This method converts the 'ImageIcon' to an 'Image' while resizing Image to the
   * appropriate image inventory size based on the given variable.
   *
   * @param imageIcon is an ImageIcon that is going to be converted to an Image and resized appropriately
   *
   * @see Constants#SIZE_OF_IMAGE_INVENTORY
   */
  public void setImageInventory(ImageIcon imageIcon) {
    imageInventory = imageIcon.getImage().getScaledInstance(SIZE_OF_IMAGE_INVENTORY, SIZE_OF_IMAGE_INVENTORY, Image.SCALE_SMOOTH);
  }
}
