package Model;

/**
 * FoodCollection is a class that stores an ArrayList of all the foods.
 */
import Controller.DietManagerController;

import java.util.*;

public class FoodCollection extends Observable{


    private Map<String,Food> foodCollection;

    public FoodCollection() {
        this.foodCollection = new HashMap<>();
    }

    /**
     * Method that adds the food into the foodCollection.
     * @param food object.
     */
    public void addFood(Food food) {
        if (!this.foodCollection.containsKey(food.getName())) {
            this.setChanged();
            this.notifyObservers(food);
            foodCollection.put(food.getName(), food);
           // System.out.println(food.getName());
           // System.out.println(foodCollection);
        } else {
            System.out.println("That food has already been entered");
        }
    }

    public boolean checkContains(String food) {
        return this.foodCollection.containsKey(food);
    }

    /**
     * Method that deletes the food from the foodCollection.
     * @param food object
     */
    public void deleteFood(Food food){
        foodCollection.remove(food.getName());
        this.setChanged();
        this.notifyObservers(food);
    }

    /**
     * Returns the entire food collection
     * @return arraylist of food items
     */
    public ArrayList<Food> getFoodCollectionList(){
        return new ArrayList<Food>(this.foodCollection.values());
    }

    public Food getFood(String name) {
        return foodCollection.get(name);
    }

    /*
    public static void main (String[]args){
        FoodCollection fc = new FoodCollection();
        BasicFood bf = new BasicFood("fdfdfood",3.8,5.4,5.5,4.4);
        fc.addFood(bf);
        BasicFood bf1 = new BasicFood("sdsd",3.28,5.4,5.5,4.4);
        fc.addFood(bf1);
        BasicFood bf2 = new BasicFood("dsd",33.8,5.4,5.5,4.4);
        fc.addFood(bf2);
        fc.printCollection();
    }
    */

}
