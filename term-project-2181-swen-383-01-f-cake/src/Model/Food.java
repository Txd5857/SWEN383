package Model;

import java.util.Map;

public interface Food {

    /**
     * Gets name of recipe or food item
     * @return String with name of recipe or food item
     */
    public String getName();

    /**
     * @return int calorie count of Food object
     */
    public double getCalorieCount();

    /**
     * Gets an array of doubles with nutritional information - calories, fat, carb, protein
     * @return double array of [calories, fat, carbs, protein]
     */

    public double getProtein();

    public double getCarb();

    public double getFat();



    /**
     * Sets the number of servings for a food object
     * @param foodServings Number of servings.
     */
    public void setServings(double foodServings);

    /**
     * Gets the number of servings for a food object
     * @return Number of Servings.
     */
    public double getServings();

    public void setFat(double fat);

    public void setProtein(double protein);

    public void setCalorieCount(double calories);

    public void setCarb(double carb);

    public void removeIng(Food food);
    public Map<Food,Double> getFoodItems();








}
