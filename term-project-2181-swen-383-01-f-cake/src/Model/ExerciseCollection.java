package Model;

import java.util.*;

public class ExerciseCollection extends Observable{
    private Map<String,Exercise> exerciseCollection;

    public ExerciseCollection() {
        this.exerciseCollection = new HashMap<>();
    }

    /**
     * Method that adds the exercise into the exerciseCollection.
     * @param exercise object.
     */
    public void addExercise(Exercise exercise) {
        if (!this.exerciseCollection.containsKey(exercise.getName())) {
            this.setChanged();
            this.notifyObservers(exercise);
            exerciseCollection.put(exercise.getName(), exercise);
        } else {
            System.out.println("That Exercise has already been entered");
        }
    }

    public boolean checkContains(String exercise) {
        return this.exerciseCollection.containsKey(exercise);
    }

    public void deleteExercise(Exercise exercise){
        exerciseCollection.remove(exercise.getName());
        this.setChanged();
        this.notifyObservers(exercise);
    }

    public ArrayList<Exercise> getExerciseCollectionList(){
        return new ArrayList<Exercise>(this.exerciseCollection.values());
    }

    public Exercise getExercise(String name) {
        return exerciseCollection.get(name);
    }
}
