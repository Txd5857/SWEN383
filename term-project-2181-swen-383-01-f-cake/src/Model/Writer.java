package Model;
import java.util.*;
import java.io.*;
import java.lang.Exception;

public class Writer
{
    public String logCSVPath = "src/Data/log.csv";
    public String foodCSVPath = "src/Data/foods.csv";
    public String exerciseCSVPath = "src/Data/exercise.csv";

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public String weight, calorie, foodName, count, name, foodcalorie, fat, carb, protein, exerciseName, exerciseCalorie,mintues;
    public Date date;
    FoodCollection fc;
    DailyLog dl;


    String year = "";
    String month = "";
    String day = "";
    public Writer(FoodCollection fc, DailyLog dl){
        this.fc =fc ;
        this.dl = dl;
    };

    /**
     * addBasicFood() method
     * adding basic food to food.csv
     * @param foodObj BasicFood object
     */


    public void addBasicFood(BasicFood foodObj)
    {
        this.name = foodObj.getName();
        this.foodcalorie = Double.toString(foodObj.getCalorieCount());
        this.fat = Double.toString(foodObj.getFat());
        this.carb = Double.toString(foodObj.getCarb());
        this.protein = Double.toString(foodObj.getProtein());

        FileWriter fileWriter = null;
        try
        {

            //fileWriter = new FileWriter(foodCSVPath,true);
            fileWriter = new FileWriter(foodCSVPath,true);
            fileWriter.append("b");
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(name);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(foodcalorie);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(fat);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(carb);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(protein);
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e){e.printStackTrace();}
        }

    }
    public void foodsave ( ) {
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(foodCSVPath));

            for (Food f : fc.getFoodCollectionList()) {
                if (f instanceof BasicFood) {
                    bf.append("b");
                    bf.append(COMMA_DELIMITER);
                    bf.append(f.getName());
                    bf.append(COMMA_DELIMITER);
                    bf.append(Double.toString(f.getCalorieCount()));
                    bf.append(COMMA_DELIMITER);
                    bf.append(Double.toString(f.getFat()));
                    bf.append(COMMA_DELIMITER);
                    bf.append(Double.toString(f.getCarb()));
                    bf.append(COMMA_DELIMITER);
                    bf.append(Double.toString(f.getProtein()));
                    bf.append(NEW_LINE_SEPARATOR);
                }

                if (f instanceof Recipe) {
                    Map<Food, Double> arr = new HashMap<Food, Double>();
                    arr = f.getFoodItems();
                    bf.append("r" + COMMA_DELIMITER + f.getName());
                    for (Food subFood : arr.keySet()) {
                        bf.append(COMMA_DELIMITER + subFood.getName() + COMMA_DELIMITER);
                        bf.append("" + subFood.getServings());
                    }
                    bf.append(NEW_LINE_SEPARATOR);
                }
            }

        }

        catch (Exception e) { }
        finally
        {
            try
            {
                bf.flush();
                bf.close();
            }
            catch (IOException e){e.printStackTrace();}
        }
    }

    /**
     * createCSV() method
     * Create foods.csv, log.csv and exercise.csv
     */
    public void createCSV()
    {
        File logfile = new File(logCSVPath);
        File foodfile = new File(foodCSVPath);
        File exercisefile = new File(exerciseCSVPath);
        try
        {
            if (!logfile.exists())
            {
                logfile.createNewFile();
            }
            if (!foodfile.exists())
            {
                foodfile.createNewFile();
            }
            if(!exercisefile.exists()){
                exercisefile.createNewFile();
            }
        }
        catch(Exception e){ e.printStackTrace();}
    }

    /**
     * addWeight() method
     * Adding user weight to log.csv
     * @param _recordedWeight weight object
     */
    public void addWeight(Date date, Double _recordedWeight)
    {
        this.weight = Double.toString(dl.getRecordedWeight(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = Integer.toString(cal.get(Calendar.YEAR));
        month = Integer.toString(cal.get(Calendar.MONTH) +1 );
        day = Integer.toString(cal.get(Calendar.DATE));

        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(logCSVPath,true);
            fileWriter.append(year);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(month);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(day);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append("w");
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(weight);
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e){e.printStackTrace();}
        }

    }

    /**
     * addFoodIntake() method
     * Adding user food intake to log.cvs
     * @param foodIntakeObj foodIntake object
     */
    public void addFoodIntake(Date date ,ConsumedFood foodIntakeObj)
    {
        this.foodName = foodIntakeObj.getName();
        this.count = Double.toString(foodIntakeObj.getServings());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = Integer.toString(cal.get(Calendar.YEAR));
        month = Integer.toString(cal.get(Calendar.MONTH) +1 );
        day = Integer.toString(cal.get(Calendar.DATE));

        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(logCSVPath,true);
            fileWriter.append(year);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(month);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(day);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append("f");
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(foodName);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(count);
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e){e.printStackTrace();}
        }

    }

//    public void logsave () {
//        BufferedWriter bf = null;
//        try {
//            //bf = new BufferedWriter(new FileWriter(foodCSVPath));
//            for (Map.Entry<Date,List<ConsumedFood>> entry : dl.getConsumedMap().entrySet())
//                String date = "" + entry.getKey()
//                List<ConsumedFood> cf =  dl.getConsumedFoods();
//
//            System.out.println("Date = " + entry.getKey() +
//                        ", Value = " + entry.getValue());
//
//        }
//
//        catch (Exception e) { }
//        finally
//        {
//            try
//            {
//                bf.flush();
//                bf.close();
//            }
//            catch (IOException e){e.printStackTrace();}
//        }
//    }

    /**
     * addCalorieLimit() method
     * Adding user calorie limit to log.cvs
     * @param calorieObj CaloricIntakeLimit object
     */
    public void addCalorieLimit(Double CaloricIntakeLimit , Date date)
    {
        this.calorie = Double.toString(CaloricIntakeLimit);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = Integer.toString(cal.get(Calendar.YEAR));
        month = Integer.toString(cal.get(Calendar.MONTH) +1 );
        day = Integer.toString(cal.get(Calendar.DATE));

        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(logCSVPath,true);
            fileWriter.append(year);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(month);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(day);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append("c");
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(calorie);
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e){e.printStackTrace();}
        }
    }



    /**
     * addRecipe() method
     * Adding recipe to food.csv
     * @param recipeObj Recipe object
     *                  Recipe recipeObj)
     */
    public void addRecipe(Recipe recipeObj)
    {
        FileWriter fileWriter = null;

        ArrayList<Food> arr = new ArrayList();
        try {

            //fileWriter = new FileWriter(foodCSVPath,true);
            fileWriter = new FileWriter(foodCSVPath, true);
            fileWriter.append("r" + COMMA_DELIMITER + recipeObj.getName());
            for (Food subFood : recipeObj.getFoodItems().keySet()) {
                fileWriter.append(COMMA_DELIMITER + subFood.getName() + COMMA_DELIMITER);
                fileWriter.append("" + subFood.getServings());
            }

            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e){e.printStackTrace();}
        }
    }

    /**
     * addExercise() method
     * Adding Exercise to exercise.csv
     * @param exerciseObj
     */
    public void addExercise(Exercise exerciseObj)
    {
        this.exerciseName = exerciseObj.getName();
        this.exerciseCalorie = Double.toString(exerciseObj.getCalories());

        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(exerciseCSVPath,true);
            fileWriter.append("e");
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(exerciseName);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(exerciseCalorie);
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e){e.printStackTrace();}
        }
    }

    public void recordExercise(RecordExercise exerciseObj, Date date)
    {
        this.exerciseName = exerciseObj.getName();
        this.mintues = Double.toString(exerciseObj.getMinutes());
        this.date = date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = Integer.toString(cal.get(Calendar.YEAR));
        month = Integer.toString(cal.get(Calendar.MONTH) +1 );
        day = Integer.toString(cal.get(Calendar.DATE));

        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(logCSVPath,true);
            fileWriter.append(year);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(month);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(day);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append("e");
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(exerciseName);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(mintues);
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        catch(Exception e){e.printStackTrace();}
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e){e.printStackTrace();}
        }
    }
}
