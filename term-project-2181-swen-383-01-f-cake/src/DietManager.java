import Controller.*;
import Model.*;
import View.DietManager_UI;

import java.lang.Exception;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * DietManager Class
 * Execute the program
 */
public class DietManager {
    public static void main(String[] args) throws ParseException {
        FoodCollection fc = new FoodCollection();
        ExerciseCollection ec = new ExerciseCollection();
        DailyLog dl = new DailyLog(fc,ec);
        DietManagerController manager = new DietManagerController(fc, ec, dl);
        DietManager_UI gui = new DietManager_UI();

        int select;

        while (true) {
            // Print the option list, ask for user input
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Please select:\n" +
                        "1. Add Food\n" +
                        "2. Add Recipe\n" +
                        "3. Add Weight\n" +
                        "4. Add Food Intake\n" +
                        "5. Desired Calorie Limit\n" +
                        "6. Delete Food from Daily Log\n" +
                        "7. View Food Collection\n" +
                        "8. View Daily Log\n" +
                        "21. View Daily Cal Total \n" +
                        "23. View Cal Lost \n" +
                        "9. Add Exercise to Collection\n" +
                        "10. View Exercise Collection\n" +
                        "30. Edit Basic Food\n" +
                        "11. Record Exercise\n" +
                        "20. Exit and Save");

                select = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Selection, please try again.\n");
                continue;
            }

            // if user input = 1(Add food), program ask for food info, then adding to the food collection, adding to the foods.csv
            if (select == 1) {
                scanner = new Scanner(System.in);
                System.out.println("Please enter food name, calorie, fat, carbohydrates and protein. EX: Hot Dog,147.0,13.6,1.1,5.1");
                String food = scanner.nextLine();
                try {
                    String food_input = food.split(",")[0];

                    double calorie_input = Double.parseDouble(food.split(",")[1]);
                    double fat_input = Double.parseDouble(food.split(",")[2]);
                    double carb_input = Double.parseDouble(food.split(",")[3]);
                    double protein_input = Double.parseDouble(food.split(",")[4]);
                    manager.addFood(food_input, calorie_input, fat_input, carb_input, protein_input);
                    //user.setFoodInfo(food_input, calorie_input, fat_input, carb_input, protein_input);
                    // user.writeBasic();
                    // afc.setFood(food_input, calorie_input, fat_input, carb_input, protein_input);
                    //afc.add();
                    // BasicFood bc = new BasicFood(food_input, calorie_input, fat_input, carb_input, protein_input);
                    //manager.addFoodToCollection(bc);
                } catch (Exception e) {
                    System.out.println("Invalid input, please try again. EX: Hot Dog,147.0,13.6,1.1,5.1\n");
                    continue;
                }
            } else if (select == 7) {
                manager.printFoodCollection();
                continue;
            }
            else if (select == 30) {
                ArrayList<Food> foodlist = fc.getFoodCollectionList();
                scanner = new Scanner(System.in);
                System.out.println("Please enter basic food name that you would like to edit, and then the new nutriential values");
                System.out.println("Example - 'ExistingFoodName','newcalories','newfat','newcarbs','newproteins'");
                String bfood = scanner.nextLine();
                String food[] = bfood.split(",");
                String foodname = food[0].trim();

                if (!fc.checkContains(foodname)) {
                    System.out.println("Food does not exist in the Food Collection");
                    continue;
                }

                try {
                        if (fc.checkContains(foodname)) {
                            Food fname = fc.getFood(foodname);
                            double calorie_input = Double.parseDouble(food[1]);
                            double fat_input = Double.parseDouble(food[2]);
                            double carb_input = Double.parseDouble(food[3]);
                            double protein_input = Double.parseDouble(food[4]);

                            fname.setProtein(protein_input);
                            fname.setCarb(carb_input);
                            fname.setProtein(protein_input);
                            fname.setCalorieCount(calorie_input);
                            //manager.addFood(food_input, calorie_input, fat_input, carb_input, protein_input);
                        }
                    } catch(Exception e){
                    System.out.println("Invalid input, please try again. EX: Hot Dog,147.0,13.6,1.1,5.1\n");
                    continue;
                    }



            }

            else if (select == 31){

            }


            // if user input = 2(Add Recipe), program ask for recipe info, then adding to the food collection, adding to the foods.csv
            else if (select == 2) {
                scanner = new Scanner(System.in);
                System.out.println("Please enter food name, subingredientname, subingredientservings , ++ ");
                String food = scanner.nextLine();
                try {
                    String[] fd = food.split(",");
                    if (fd.length < 3 || fd.length % 2 == 0) {
                        System.out.println("Invalid input, please try again.\n");
                        continue;
                    }
                    String food_input = fd[0].trim();
                    Map<Food, Double> values = new HashMap<>();
                    boolean cancelFlag = false;

                    for (int i = 1; i < fd.length; i += 2) {
                        String subName = fd[i].trim();
                        Double subServing = Double.parseDouble(fd[i + 1].trim());
                        if (fc.checkContains(subName)) {
                            fc.getFood(subName).setServings(subServing);
                            values.put(fc.getFood(subName), subServing);

                        } else {
                            System.out.println("This Food does not exist in the Food collection. Must add the subrecipes to the FC");
                            cancelFlag = true;
                            break;
                        }
                    }
                    if (!cancelFlag) {
                        manager.addRecipe(food_input, values);
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input, please try again.\n");
                    continue;
                }


            }

            // if user input = 3/4/5, ask the what date did they like to log
            else if (select == 3 || select == 4 || select == 5 || select==6 || select == 8 || select == 11 || select == 21 || select == 22 || select == 23
            || select == 24 ) {

                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                System.out.println(date);
                String currDate = df.format(date);
                date = new SimpleDateFormat("yyyy/MM/dd").parse(currDate);
                System.out.println(date);
                System.out.println("Do you want to log your entries for today's date? (Enter anything for YES. Enter N/n for NO)");
                char yn = scanner.next().charAt(0);
                if (yn == 'n' || yn == 'N') {
                    System.out.println("What day would you like to log your entries? Ex: yyyy/mm/dd");
                    currDate = scanner.next();
                    try {
                        date = new SimpleDateFormat("yyyy/MM/dd").parse(currDate);
                        System.out.println(date);
                        //currDate = df.format(dateUse);
                    } catch (java.text.ParseException e) {
                        System.out.println("Invalid input, please try again. Ex: yyyy/mm/dd\n");
                        continue;
                    }
                }
                else if (select == 23) {

                    System.out.println(dl.totalCalLost(date));

                }
                else if (select == 24) {
                    scanner = new Scanner(System.in);
                    System.out.println("Please enter exercise name, minutes ");
                    String exercise = scanner.nextLine();
                    String[] exercisesplit = exercise.split(",");
                    String exname = exercisesplit[0];

                    if (!ec.checkContains(exname)) {
                        System.out.println("try again");
                        continue;
                    }
                    try {

                        double exercisemin  = Double.parseDouble(exercisesplit[1]);

                        manager.recordExercise(date,exname,exercisemin);

                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again. EX: Hot Dog,147.0,13.6,1.1,5.1\n");
                        continue;
                    }
                }
                else if (select ==6 ) {
                    System.out.println("Below are consumed Food of " + date);

                    List<ConsumedFood> ar = dl.getConsumedFoods(date);

                    for (int i = 0; i<ar.size(); i++) {

                        Food foo = fc.getFood(ar.get(i).getName());
                        Double calcount = foo.getCalorieCount() * ar.get(i).getServings();
                        Double serv = ar.get(i).getServings();
                        String conname = ar.get(i).getName();

                        System.out.println( "Index " + i + " ConsumedFood Name: " + conname + "-- Servings: " + serv + "-- Total Calories: " + calcount);

                    }

                    scanner = new Scanner(System.in);
                    System.out.println("Enter index you would like to remove");
                    int ind = scanner.nextInt();
                    if ( ind >= ar.size() || ind < 0 ){
                        System.out.println("Wrong index");
                        continue;
                    }
                    dl.removefromCF(ind,date);


                }

                else if (select == 22) {
                    System.out.println( "Total Cal " + dl.totalCalConsumed(date) + "Total Fat  " + dl.totalFat(date) + "total Carb " + dl.totalCarb(date)
                    + " Total Protein " + dl.totalProtein(date));
                }
                // if user input = 3(Add weight), program ask for user weight, adding to log.csv
                else if (select == 3) {
                    scanner = new Scanner(System.in);
                    try {
                        System.out.println(date);
                        System.out.println("Please enter weight. EX:100.0");
                        String weight = scanner.nextLine();
                        System.out.println(weight);
                        manager.addWeight(date, Double.parseDouble(weight));
                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again. EX:100.0\n" + e);
                        continue;
                    }

                }

                // if user input = 4(Add food to log), program ask for food name and serving number, then adding to log.csv
                else if (select == 4) {
                    scanner = new Scanner(System.in);
                    try {
                        System.out.println("Please enter food name and serving. EX:HotDog, 1.0");
                        String foodIntake = scanner.nextLine();
                        String foodName = foodIntake.split(",")[0];
                        double serving = Double.parseDouble(foodIntake.split(",")[1]);
                        if (fc.checkContains(foodName)) {
                            manager.addFoodIntake(date, foodName, serving);
                            System.out.println("Your food item was added to the daily log");
                        } else if (!fc.checkContains(foodName)) {
                            System.out.println("That food is not yet in the food collection, please add to the food collection first.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again. EX:HotDog, 1.0\n");
                        continue;
                    }
                }

                // if user input = 5(Add caloric limit), program ask for user desired caloric limit, adding to log.csv
                else if (select == 5) {
                    scanner = new Scanner(System.in);
                    try {
                        System.out.println("Please enter caloric limit. EX:2000.0");
                        double caloricLimit = scanner.nextDouble();
                        manager.addCaloricLimit(date, caloricLimit);
                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again. EX:2000.0\n");
                        continue;
                    }
                }

                else if (select == 21) {
                    System.out.println(dl.totalCalConsumed(date));
                }

                // if user inout = 11
                else if (select == 11) {
                    scanner = new Scanner(System.in);
                    try {
                        System.out.println("Please enter exercise information. EX:Gardening,15.0");
                        String exercise = scanner.nextLine();
                        String exerciseName = exercise.split(",")[0];
                        double exercisMinutes = Double.parseDouble(exercise.split(",")[1]);
                        // manager.recordExercise(date,exerciseName,exercisMinutes);
                        if (ec.checkContains(exerciseName)) {
                            manager.recordExercise(date, exerciseName, exercisMinutes);
                            System.out.println("Your exercise item was added to the daily log");
                        } else if (!ec.checkContains(exerciseName)) {
                            System.out.println("That exercise is not yet in the exercise collection, please add to the exercise collection first.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again. EX:Gardening,15.0\n");
                        continue;
                    }
                }
                else if (select == 8) {

                    System.out.println(date);
                    manager.printDailyLog(date);
                }
                }
                else if (select == 9) {
                    scanner = new Scanner(System.in);
                    try {
                        System.out.println("Please enter exercise name and number of calories expended by the exercise. EX: Gardening,180.0");
                        String exercise = scanner.nextLine();
                        String exerciseName = exercise.split(",")[0];
                        double exerciseCalorie = Double.parseDouble(exercise.split(",")[1]);
                        manager.addExercise(exerciseName, exerciseCalorie);
                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again. EX: Gardening,180.0\n");
                        continue;
                    }
                } else if (select == 10) {
                    manager.printExerciseCollection();
                    continue;
                }

                // if user input = 20(Exit), Stop the program
                else if (select == 20) {
                    return;
                }

                // if user input is not in the list, print error message
                else {
                    System.out.println("Invalid Selection\n");
                }

                // After each activity, ask the use do they want to continue.
                System.out.println("Do you want to continue? ((Enter anything for YES. Enter N/n for NO)");
                char ync = scanner.next().charAt(0);
                if (ync == 'n' || ync == 'N') {
                    return;
                }

            }
        }
    }

