package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reader  {

    public Reader(FoodCollection fc, ExerciseCollection ec, DailyLog dl){
        this.fc = fc;
        this.ec = ec;
        this.dl = dl;
    }

    Scanner scanner;
    public String foodFilePath = "src/Data/foods.csv";
    public String exerciseFilePath = "src/Data/exercise.csv";
    public String dlFilePath = "src/Data/log.csv";

    FoodCollection fc;
    DailyLog dl;
    ExerciseCollection ec;

    public void readFood()
    {
        try {
            this.scanner = new Scanner(new File(foodFilePath));

            while (scanner.hasNextLine()){
                String [] oneLine = scanner.nextLine().split(",");
                String type = oneLine[0];
                if (type.equals("b")) {
                    String foodName = oneLine[1];
                    Double calories = Double.parseDouble(oneLine[2]);
                    Double fat = Double.parseDouble(oneLine[3]);
                    Double carb = Double.parseDouble(oneLine[4]);
                    Double prot = Double.parseDouble(oneLine[5]);

                    BasicFood bF = new BasicFood(foodName,calories,fat,carb,prot);
                    fc.addFood(bF);

                }
                if (type.equals("r")) {
                    String foodName = oneLine[1];
                    Map<Food,Double> values = new HashMap<>();

                    for (int i = 2; i< oneLine.length;i+=2)
                    {
                        String subFood = oneLine[i];
                        Double subServe = Double.parseDouble(oneLine[i+1]);

                        if (fc.checkContains(subFood)) {
                            values.put(fc.getFood(subFood),subServe);
                        }
                    }

                    Recipe rP = new Recipe(foodName, values);
                    fc.addFood(rP);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void readDailyLog() {
        try {
            this.scanner = new Scanner(new File(dlFilePath));

            while(scanner.hasNextLine()){
                String[] oneLine = scanner.nextLine().split(",");
                String newDate = oneLine[0] + '/' + oneLine[1] + '/' + oneLine[2];
                Date date = new SimpleDateFormat("yyyy/MM/dd").parse(newDate);

                switch (oneLine[3]) {
                    case "w":
                        double _recordedweight = Double.parseDouble(oneLine[4]);
                        dl.addRecordedWeight(_recordedweight,date);
                        break;
                    case "c":
                        double _recordedcal = Double.parseDouble(oneLine[4]);
                        dl.addRecordedCaloricLimit(_recordedcal,date);
                        break;
                    case "f":
                        String foodname = oneLine[4];
                        Double _recordedserving = Double.parseDouble(oneLine[5]);
                        dl.addConsumedFood(new ConsumedFood(foodname,_recordedserving),date);
                        break;
                    case "e":
                        String exercisename = oneLine[4];
                        Double _recordedminutes = Double.parseDouble(oneLine[5]);
                        dl.addExercise(new RecordExercise(exercisename,_recordedminutes),date);
                        break;

                }
                }

        }
        catch (Exception e ){

        }
    }
    public void readExercise() {
        try {
            this.scanner = new Scanner(new File(exerciseFilePath));

            while (scanner.hasNextLine()) {
                String[] oneLine = scanner.nextLine().split(",");
                String exerciseName = oneLine[1];
                Double exercisecalories = Double.parseDouble(oneLine[2]);

                Exercise e = new Exercise(exerciseName, exercisecalories);
                ec.addExercise(e);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}