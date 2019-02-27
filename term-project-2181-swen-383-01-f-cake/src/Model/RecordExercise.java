package Model;

import java.util.Date;

public class RecordExercise {
    // Attributes
    private String name;
    private double minutes;

    public RecordExercise( String _name, double _minutes){
        name = _name;
        minutes = _minutes;
    }

    /**
     * Gets date
     * @return Date
     */
    //public Date getDate(){return date;}

    /**
     * Gets name of exercise
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets number of calories expended by the exercise
     * @return double
     */
    public double getMinutes() {
        return minutes;
    }
}
