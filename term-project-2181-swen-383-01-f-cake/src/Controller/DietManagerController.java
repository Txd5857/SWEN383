package Controller;

import java.util.*;

import Model.*;

import javax.sound.midi.Soundbank;
import java.util.Date;

public class DietManagerController {

    public Writer writer;
    FoodCollection fc;
    ExerciseCollection ec;
    DailyLog dl;
    Reader rd;

    public DietManagerController(FoodCollection fc, ExerciseCollection ec, DailyLog dl){
        writer = new Writer(fc,dl);
        writer.createCSV();
        this.fc = fc;
        this.ec = ec;
        this.dl = dl;
        this.rd = new Reader(fc,ec,dl);
        rd.readFood();
        rd.readExercise();
        rd.readDailyLog();

    }

    public void addWeight(Date date, double weightInput){
        dl.addRecordedWeight(weightInput,date);
        writer.addWeight(date,weightInput);
    }

    public void addFoodIntake(Date date, String name, double serving){
        ConsumedFood foodIntake = new ConsumedFood (name, serving);
        writer.addFoodIntake( date, foodIntake);
        dl.addConsumedFood(foodIntake, date);
    }

    public void addCaloricLimit(Date date, double limit){
        dl.addRecordedCaloricLimit(limit,date);
        writer.addCalorieLimit(limit,date);
    }

    public Food addFood(String name, double foodcalorie, double fat, double carb, double protein){
        if (fc.checkContains(name)){
            System.out.println("Already added");
        }
        BasicFood basicFood = new BasicFood(name, foodcalorie, fat, carb, protein);
        writer.addBasicFood(basicFood);
        fc.addFood(basicFood);
        return basicFood;
    }

    public void addRecipe(String name, Map<Food,Double> arr) {
        Recipe recipe = new Recipe(name,arr);
        fc.addFood(recipe);
        writer.addRecipe(recipe);
    }

    /*public void addFoodToCollection(BasicFood basicFood)
    {
        fc.addFood(basicFood);
    }
    */


    public Exercise addExercise(String name, double calorie)
    {
        if (ec.checkContains(name)){
            System.out.println("Already added");
        }
        Exercise exercise = new Exercise(name, calorie);
        writer.addExercise(exercise);
        ec.addExercise(exercise);

        //
        return exercise;
    }

    public void recordExercise(Date date, String name, double minutes)
    {
        RecordExercise recordExercise = new RecordExercise(name,minutes);
        dl.addExercise(recordExercise, date );
        writer.recordExercise(recordExercise,date);
    }

    public void printFoodCollection(){
        ArrayList<Food> foods = fc.getFoodCollectionList();
        for (Food f: foods){
            System.out.printf("[Name: %s] (Calories: %s) (Fat: %s) (Protein: %s) (Carbs: %s) \n", f.getName(), f.getCalorieCount(),
                    f.getProtein(),f.getCarb());
        }
    }

    public void printFoodInfo(String foodname){
        if (!fc.checkContains(foodname)){
            System.out.println("Try Again");
        }
        if (fc.checkContains(foodname)){
            Food food = fc.getFood(foodname);
            System.out.printf("(Calories: %s) (Fat: %s) (Protein: %s) (Carbs: %s) \n", food.getCalorieCount(),
                    food.getFat(), food.getProtein(),food.getCarb());
        }
    }


    public void printDailyLog(Date date){
        System.out.println("-----------------");
        System.out.println("Below is the weight of " + date);

        System.out.println(dl.getRecordedWeight(date));
        System.out.println("-----------------");

        System.out.println("Below are consumed Food of" + date);
        List<ConsumedFood> ar = dl.getConsumedFoods(date);

        for (ConsumedFood cf : ar) {
            Food foo = fc.getFood(cf.getName());
            Double calcount = foo.getCalorieCount() * cf.getServings();
            Double serv = cf.getServings();
            String conname = cf.getName();

            System.out.println( "ConsumedFood Name: " + conname + "-- Servings: " + serv + "-- Total Calories: " + calcount);
        }
        System.out.println("-----------------");
        System.out.println("Below are Recorded Exercises of" + date);
        List<RecordExercise> rel = dl.getRecordedExercise(date);
        for (RecordExercise re : rel){
            Exercise ex = ec.getExercise(re.getName());
            String exname = re.getName();
            Double minutes = re.getMinutes();

            System.out.println( exname + "  " + minutes );

        }

    }

    public void printExerciseCollection(){
        ArrayList<Exercise> exercise = ec.getExerciseCollectionList();
        for (Exercise e: exercise){
            System.out.printf("[Name: %s] (Calories: %s)\n", e.getName(), e.getCalories());
        }
    }


}
