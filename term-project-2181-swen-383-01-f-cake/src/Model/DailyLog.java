package Model;

import java.util.*;

/**
 * Stores the users consumed foods, recorded weight, caloricintakeintake
 * from the DailyLog csv file
 *
 */
public class DailyLog {
    public static final double DEFAULT_WEIGHT = 150.00;
    public static final double DEFAULT_CAL = 2000.00;

    private FoodCollection fc;
    private ExerciseCollection ec;

    private Map<Date, List<ConsumedFood>> consumedFoodsMap ;
    private Map<Date, Double > weightMap;
    private Map<Date, List<RecordExercise>> exerciseMap;
    private Map<Date, Double> cilMap;
    /**
     * Constuctor that instantiates the attributes.
     */
    public DailyLog(FoodCollection fc, ExerciseCollection ec){
//        consumedFoods = new ArrayList<ConsumedFood>();
//        recordedCaloricLimit = new ArrayList<CaloricIntakeLimit>();
//        recordedWeight = new ArrayList<Weight>();
//        logEntry = new HashMap<Date, DailyLog>();
        consumedFoodsMap = new TreeMap<>();
        weightMap = new TreeMap<>();
        exerciseMap = new TreeMap<>();
        cilMap = new TreeMap<>();
        this.fc = fc;
        this.ec = ec;
    }

    public void addExercise(RecordExercise ec, Date date){
        if (!exerciseMap.containsKey(date)){

            ArrayList elist = new ArrayList();
            this.exerciseMap.put(date, elist);

        }
        exerciseMap.get(date).add(ec);
    }

    public Double totalCalLost(Date date){
        Double totalCal = 0.0;
        if (!exerciseMap.containsKey(date)){
            return totalCal;
        }

        Double currWeight = getRecordedWeight(date);

        List<RecordExercise> elist = exerciseMap.get(date);

        for (RecordExercise re : elist) {
            Double calperhour = ec.getExercise(re.getName()).getCalories();
            Double minutes = re.getMinutes();


            totalCal += (calperhour * (currWeight/100.00) * (minutes/60));

        }
        return totalCal;
    }
    /**
     * A method that adds the food to the consumedFoods array.
     * @param conFood the consumed food.
     */
    public void addConsumedFood(ConsumedFood conFood, Date date){

        if (!consumedFoodsMap.containsKey(date)){

            ArrayList clist = new ArrayList();
            this.consumedFoodsMap.put(date, clist);

        }

        consumedFoodsMap.get(date).add(conFood);

    }

    public Double totalCalConsumed(Date date){
        Double totalCal = 0.0;
        if(!consumedFoodsMap.containsKey(date)){
            return totalCal;
        }
        List<ConsumedFood> clist = consumedFoodsMap.get(date);

        for (ConsumedFood cf : clist) {

             totalCal +=  fc.getFood(cf.getName()).getCalorieCount() * cf.getServings();

        }
        return totalCal;

    }

    public Double totalProtein(Date date){
        Double totalPro = 0.0;
        if(!consumedFoodsMap.containsKey(date)){
            return totalPro;
        }
        List<ConsumedFood> clist = consumedFoodsMap.get(date);

        for (ConsumedFood cf : clist) {

            totalPro +=  fc.getFood(cf.getName()).getProtein() * cf.getServings();

        }
        return totalPro;
    }

    public Double totalCarb(Date date){
        Double totalCarb = 0.0;
        if(!consumedFoodsMap.containsKey(date)){
            return totalCarb;
        }
        List<ConsumedFood> clist = consumedFoodsMap.get(date);

        for (ConsumedFood cf : clist) {

            totalCarb +=  fc.getFood(cf.getName()).getCarb() * cf.getServings();

        }
        return totalCarb;
    }

    public Double totalFat(Date date){
        Double totalFat = 0.0;
        if(!consumedFoodsMap.containsKey(date)){
            return totalFat;
        }
        List<ConsumedFood> clist = consumedFoodsMap.get(date);

        for (ConsumedFood cf : clist) {

            totalFat +=  fc.getFood(cf.getName()).getFat() * cf.getServings();

        }
        return totalFat;
    }


    /**
     * A method that adds the weight to the Weight array
     * @param _recordedWeight recorded weight.
     */
    public void addRecordedWeight(Double _recordedWeight, Date date){

        weightMap.put(date,_recordedWeight);

    }

    /**
     * A method that adds the caloric intake limit to the limit array
     * @param _recordedIntakeLimit
     */
    public void addRecordedCaloricLimit(Double _recordedIntakeLimit, Date date){

        cilMap.put(date, _recordedIntakeLimit);
    }


    /**
     * getConsumedFoods() method
     * Get consumed food record
     * @return ArrayList<ConsumedFood>
     */
    public List<ConsumedFood> getConsumedFoods(Date date) {
        if(!consumedFoodsMap.containsKey(date)){
            return null;
        }
        return consumedFoodsMap.get(date);
    }

    public Map<Date, List<ConsumedFood>> getConsumedMap() { return consumedFoodsMap;}

    public List<RecordExercise>  getRecordedExercise(Date date){
        if(!exerciseMap.containsKey(date)){
            return null;
        }
        return exerciseMap.get(date);
    }

    public void removefromCF (int i, Date date) {
        List<ConsumedFood> ar = getConsumedFoods(date);
        ar.remove(i);
    }


    /**
     * getRecordedWeight() method
     * Get weight record
     * @return ArrayList<Weight>
     */


    public Double getCaloricIntake(Date date) {
        if(cilMap.containsKey(date)){
            return cilMap.get(date);
        }
        else {
            Date finaldate= null;
            for (Date d : cilMap.keySet()) {
                if (d.compareTo(date) > 0 )
                {
                    break;
                }
                finaldate = d;
            }

            if (finaldate!=null){
                return cilMap.get(finaldate);
            } else {
                return DEFAULT_CAL;
            }
        }

    }
    public Double getRecordedWeight(Date date) {
        if(weightMap.containsKey(date)){
           return weightMap.get(date);
        }
        else {
            Date finaldate= null;
            for (Date d : weightMap.keySet()) {
                if (d.compareTo(date) > 0 )
                {
                    break;
                }
                finaldate = d;
            }

            if (finaldate!=null){
                return weightMap.get(finaldate);
            } else {
                return DEFAULT_WEIGHT;
            }
        }

    }

    /**
     * getRecordedCaloricLimit() method
     * Get user caloric limit record
     * @return ArrayList<CaloricIntakeLimit>
     */
   // public ArrayList<CaloricIntakeLimit> getRecordedCaloricLimit() {
     //   return recordedCaloricLimit;
   // }


}
