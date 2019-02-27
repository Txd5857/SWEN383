package Model;
import java.util.*;

/**
 * Creates a new recorded weight as an object for when a user records their weight.
 *
 */
public class Weight {
    // Attributes
    //private Date date;
    private double recordedWeight;

    /**
     * Weight object Constructor
     * @param _recordedWeight The weight value that is being recorded.
     */
    public Weight(Date _date, double _recordedWeight) {
        //date = _date;
        recordedWeight = _recordedWeight;
    }

    /**
     * getDate() method
     * Get the date that the user like to use
     * @return Date
     */
    //public Date getDate(){return date;}

    /**
     * getRecordedWeight() method
     * Get user weight record
     * @return double
     */
    public double getRecordedWeight() {
        return recordedWeight;
    }

    /**
     * setRecordedWeight() method
     * Set user weight
     * @param newrecordedWeight
     */
    public void setRecordedWeight(double newrecordedWeight) {
        this.recordedWeight = newrecordedWeight;
    }

    public String rwentry(){
        return "Weight recorded to be " + Double.toString(recordedWeight);
    }
}
