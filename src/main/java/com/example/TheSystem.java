package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TheSystem {
    //maintain the list of items and the main logic of the system that is similar in the app and cart classes.
    private HashMap<String, Item> itemCollection; // provides the list of items in the system

    TheSystem() throws FileNotFoundException {
        itemCollection = new HashMap<String, Item>();
        String path = "resources/sample.txt"; //this is the relative path
        if (getClass().getSimpleName().equals("AppSystem"))
        {
            FileReader fr = new FileReader(path);
            Scanner sc = new Scanner(fr);
            while (sc.hasNext())
            {
                String line = sc.nextLine();
                String[] itemInfo = line.split("\\s "); //"\s" didn't work on java8
                Item item = new Item(itemInfo[0],itemInfo[1],Double.parseDouble(itemInfo[2]),Integer.parseInt(itemInfo[3]));//pass the iteminfo into Item
                itemCollection.put(item.getItemName(), item);//put the item into the collection
            }
        }
    }

    public void setItemCollection(HashMap<String, Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public HashMap<String, Item> getItemCollection(){
        return this.itemCollection;
    }
    
    public Boolean checkAvailability(Item item) {
        if (item.getQuantity() >= item.getAvailableQuantity())
        {
            System.out.println("System is unable to add " + item.getItemName() + " to the card. System only has " + item.getAvailableQuantity() + " " + item.getItemName() + "s");
            return false;
        } else {
            return true;
        }
    }
    
    public Boolean add(Item item)
    {
        if (item == null)
        {
            return false;
        }
        else if (this.itemCollection.containsKey(item.getItemName()))
        {
            item.setQuantity(item.getQuantity() + 1);
            this.itemCollection.put(item.getItemName(), item);
            return true;
        }
        else if (!this.itemCollection.containsKey(item.getItemName()))
        {
            item.setQuantity(1);
            this.itemCollection.put(item.getItemName(), item);
            return true;
        }
        return false;
    }

    public Item remove(String itemName) {
        if (this.itemCollection.containsKey(itemName))
        {
            return this.itemCollection.remove(itemName); //will return it automatically
        }
        return null;
    }

    public abstract void display();
}
