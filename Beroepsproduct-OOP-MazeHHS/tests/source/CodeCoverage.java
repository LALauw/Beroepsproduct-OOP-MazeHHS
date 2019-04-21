package source;

import org.junit.Test;

import static org.junit.Assert.*;

public class CodeCoverage {
  @Test
  public void containsKey() {
    Menu menu = new Menu();
    Board board = new Board(menu);
    Player player = board.getPlayer();
    Inventory inventory = player.getInventory();

    System.out.println("The next return statement should return false, due to the inventory being empty without items");
    System.out.println(inventory.containsKey());
    assertFalse(inventory.containsKey());

    System.out.println("We will now create an instance of an Item named 'sword' and add this to our current inventory.");
    Item itemSword = new Item("sword");
    inventory.addItem(itemSword);

    System.out.println("The next return statement should still return false, due to the inventory not containing any items named 'key'");
    System.out.println(inventory.containsKey());
    assertFalse(inventory.containsKey());

    System.out.println("We will now create an instance of an Item named 'key' and add this to our current inventory.");
    Item itemKey = new Item("key");
    inventory.addItem(itemKey);

    System.out.println("The next return statement should now return true, due to the inventory having a key added to it");
    System.out.println(inventory.containsKey());
    assertTrue(inventory.containsKey());
  }
}