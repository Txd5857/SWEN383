package Model;
import java.util.*;
/**CaloricIntake object for when a new
 * caloricIntake limit is added.
 */
public class CaloricIntakeLimit{
    // Attributes

    //private Date date;

    private double caloricLimit;

    /**
     * CaloricIntakeLimit Constructor
     * @param _date Date date
     * @param _caloricLimit double user caloric limit
     */
    public CaloricIntakeLimit(Date _date, double _caloricLimit){
        //date = _date;
        caloricLimit = _caloricLimit;
    }

    /**
     * getDate() method
     * Get date that the user like to use
     * @return Date
     */
    //public Date getDate(){return date;}

    /**
     * getCaloricLimit() method
     * Get user caloric limit
     * @return double
     */
    public double getCaloricLimit () {
        return caloricLimit;
    }

    /**
     * delEntry() method
     * @return String
     */
    public String dclEntry () {
        return "The Caloric Intake Limit is " + Double.toString(caloricLimit);
    }

}
