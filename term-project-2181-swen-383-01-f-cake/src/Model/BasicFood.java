package Model;

import java.util.Map;

public class BasicFood implements Food{
    // Attributes
    private String name;
    private double calories;
    private double fat;
    private double carb;
    private double protein;
    private double servings;


    /**
     * BasicFood Constructor
     * @param _name String name of food
     * @param _calories double amount of calories
     * @param _fat double amount of fat
     * @param _carb double amount of carbs
     * @param _protein double amount of protein
     */
    public BasicFood(String _name, double _calories, double _fat, double _carb, double _protein) {
        name = _name;
        calories = _calories;
        fat = _fat;
        carb = _carb;
        protein = _protein;
        servings = 1.0;

    }

    /**
     * Gets name of basic food
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets calories for basic food item
     * @return double
     */
    public double getCalorieCount() {
        return calories;
    }


    /**
     * Gets fat content for basic food item
     * @return double
     */
    public double getFat() {
        return fat;
    }

    /**
     * Gets carb content for basic food item
     * @return double
     */
    public double getCarb() {
        return carb;
    }


    /**
     * Gets protein content for basic food item
     * @return double
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Gets nutritional info (calories, fat, carb, protein)
     * @return double array with nutritional info
     */

    /**
     * Gets the number of servings for a basic food.
     * @return Number of servings
     */
    public double getServings() {
        return servings;
    }



    public void setCalorieCount(double _calories) {
        calories = _calories;
    }

    /**
     * Sets the number of servings for a basic food
     * @param _servings Number of servings
     */
    public void setServings(double _servings){
        servings = _servings;
    }


    public void setFat(double _fat) {
        fat = _fat;
    }

    public void setCarb(double _carb) {
        carb = _carb;
    }

    @Override
    public void removeIng(Food food) {

    }

    @Override
    public Map<Food, Double> getFoodItems() {
        return null;
    }

    public void setProtein(double _protein) {
        protein = _protein;
    }
}
