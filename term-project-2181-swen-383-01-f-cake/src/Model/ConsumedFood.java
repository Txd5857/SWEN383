package Model;
import java.util.*;

/**
 * Creates a ConsumedFood object for when it is recorded as consumed.
 */
public class ConsumedFood {

    private String name;
    private double servings;

    /**
     * Parametrized constructor for the ConsumedFood object.
     * @param _name     The Name of the consumed Food.
     * @param _servings The number of servings consumed.
     */
    public ConsumedFood( String _name, double _servings) {
        name = _name;
        servings = _servings;
    }

    /**
     * Gets the name of the consumed food
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the servings amount of the consumed food
     * @return double servings
     */
    public double getServings() {
        return servings;
    }

    /**
     * Method will return a entry that contains the name of the food and the number of servings
     */
    public String cfentry() {
        return name + Double.toString(servings) + " servings";
    }

}