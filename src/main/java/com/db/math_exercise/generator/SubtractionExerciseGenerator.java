package com.db.math_exercise.generator;

import com.db.math_exercise.exercise.Exercise;
import com.db.math_exercise.exercise.SubtractionExercise;
import org.fluttercode.datafactory.impl.DataFactory;

public class SubtractionExerciseGenerator implements ExerciseGenerator {

    private DataFactory dataFactory = new DataFactory();

    @Override
    public Exercise generateExercise() {
        Exercise exercise = new SubtractionExercise();
        exercise.setFirst(dataFactory.getNumberBetween(50, 100));
        exercise.setSecond(dataFactory.getNumberBetween(1, 50));
        return exercise;
    }
}
