package Model;

public class Exercise {
    // Attributes
    private String name;
    private double calories;

    public Exercise(String _name, double _calories){
        name = _name;
        calories = _calories;
    }

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
    public double getCalories() {
        return calories;
    }

    public void setCalories( double _calories) { calories = _calories;}
}
