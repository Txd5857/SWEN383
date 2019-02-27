package Model;

import java.util.*;

public class Recipe implements Food {

    // Attributes
    private String recipeName; //Name of the recipe
    private double servings; //Number of servings portion
    private Map<Food,Double> foodItems; //Components that makes up RecipeIngredient
    //Initialise variables
    private double calories = 0;
    private double carb = 0;
    private double fat = 0;
    private double protein = 0;


    @Override
    public double getCarb() {
        getNutrDiet();
        return carb;
    }

    @Override
    public double getFat() {
        getNutrDiet();
        return fat;
    }

    @Override
    public double getProtein(){
        getNutrDiet();
        return protein;
    }

    @Override
    public Map<Food,Double> getFoodItems() {
        return foodItems;
    }

    /**
     * Recipe constructor
     * @param _recipeName String recipe name
     * @param _foodItems String array of basic food items
     */
    public Recipe(String _recipeName, Map<Food,Double> _foodItems) {
        recipeName = _recipeName;
        foodItems = _foodItems;
        servings = 1;
        getNutrDiet();
    }

    public void setIngServ(double ingServ, Food food){
        if (getFoodItems().containsKey(food)) {
            foodItems.put(food, ingServ);
        }
        }

     public void removeIng(Food food){
         System.out.println("DELETETED");
        if (getFoodItems().containsKey(food)){
            foodItems.remove(food);

        }
     }

     public void addIng(Food food,double ingServ){
        if (!getFoodItems().containsKey(food)){
            foodItems.put(food, ingServ);
        }
     }
    /**
     * Get name of the recipe
     * @return String recipe name
     */
    public String getName() {
        return recipeName;
    }

    /**
     * Gets the nutritional information of each food item in recipe and adds them together
     * @return double array with nutritional information (calories, fat, carb, protein)
     */
    public void getNutrDiet() {
        calories = 0;
        fat = 0;
        carb = 0;
        protein = 0;
        for (Food food : foodItems.keySet()) {
            double subServings = foodItems.get(food);
            calories += food.getCalorieCount()* subServings;
            fat += food.getFat() *  subServings;
            carb += food.getCarb() * subServings;
            protein += food.getProtein() * subServings;
        }
    }

    @Override
    public double getCalorieCount() {
        getNutrDiet();
        return this.calories;
    }

    /**
     * Sets servings size
     * @param _servings double
     */
    public void setServings(double _servings) {
        servings = _servings;
    }

    /**
     * Gets servings size
     * @return double
     */
    public double getServings() {
        return servings;
    }

    public void setFat(double fat){}

    public void setProtein(double protein){}

    public void setCarb(double carb){}

    public void setCalorieCount(double calorieCount){}

}
