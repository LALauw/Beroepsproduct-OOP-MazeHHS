package source;

import java.util.ArrayList;

import static source.Constants.*;

public class Inventory {
  private ArrayList<Item> items;
  private Player player;
  private boolean limitOneKey;
  private int countPickedUpKeys;

  /**
   * This constructor creates an instance and sets the properties of this object. This object has a relation to Player.
   * This object contains an collection of the type Item to be able to add or remove items such as keys.
   *
   * @param player is a Player to which object is related to.
   *
   * @see Constants#consoleLog(String)
   */
  public Inventory(Player player) {
    consoleLog("creating inventory..");

    this.items = new ArrayList<>();
    this.player = player;
    this.limitOneKey = true;
    this.countPickedUpKeys = 0;

    consoleLog("created inventory");
  }

  public ArrayList<Item> getItems() {
    return items;
  }

  public void setItems(ArrayList<Item> items) {
    this.items = items;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public boolean isLimitOneKey() {
    return limitOneKey;
  }

  public void setLimitOneKey(boolean limitOneKey) {
    this.limitOneKey = limitOneKey;
  }

  public int getCountPickedUpKeys() {
    return countPickedUpKeys;
  }

  public void setCountPickedUpKeys(int countPickedUpKeys) {
    this.countPickedUpKeys = countPickedUpKeys;
  }

  /**
   * This method increases the 'countPickedUpKeys' variable by 1
   */
  public void addCountPickedUpKeys() {
    countPickedUpKeys++;
  }

  /**
   * This method returns the amount size of items in this inventory.
   *
   * @return an integer from the size of the items variable.
   */
  public int getItemsCount() {
    return items.size();
  }

  /**
   * This method removes all instances Item that was in this inventory.
   */
  public void empty() {
    items.clear();
  }

  /**
   * This method checks if the value of the items in the inventory corresponds to the value given in the parameter.
   *
   * @param value is an integer
   * @return a boolean true if the value is the same as in the parameter, false if not.
   */
  public boolean containsKeyValue(int value) {
    for(int i = 0; i < items.size(); i++) {
      if(items.get(i).getValue() == value)
        return true;
    }
    return false;
  }

  /**
   * This method is meant to remove/destroy all the Objects in the ArrayList<Item> items that are named "key"
   *
   * @return a boolean
   */
  public boolean containsKey() {
    for(int i = 0; i < items.size(); i++) {
      if(items.get(i).getName().contains("key"))
        return true;
    }
    return false;
  }

  /**
   * This method is meant to remove/destroy all the Objects in the ArrayList<Item> items that are named "key"
   */
  public void destroyKeys() {
    ArrayList<Item> index = new ArrayList<>();

    for(int i = 0; i < items.size(); i++) {
      if(items.get(i).getName().contains("key"))
        index.add(items.get(i));
    }

    for(int i = 0; i < index.size(); i++) {
      items.remove(index.get(i));
    }
  }

  /**
   * This method adds an specified item to the current inventory
   *
   * @param item is an Item which is put into the current inventory
   *
   * @see #addCountPickedUpKeys()
   * @see Menu#updateCountKeys()
   */
  public void addItem(Item item) {
    items.add(item);

    if(item.getName().startsWith("key")) {
      addCountPickedUpKeys();
      player.getBoard().getMenu().updateCountKeys();
    }
  }
}
