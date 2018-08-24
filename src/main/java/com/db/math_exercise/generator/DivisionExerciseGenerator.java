package com.db.math_exercise.generator;

import com.db.math_exercise.exercise.DivisionExercise;
import com.db.math_exercise.exercise.Exercise;
import org.fluttercode.datafactory.impl.DataFactory;

public class DivisionExerciseGenerator implements ExerciseGenerator {

    private DataFactory dataFactory = new DataFactory();

    @Override
    public Exercise generateExercise() {
        Exercise exercise = new DivisionExercise();
        exercise.setFirst(dataFactory.getNumberBetween(20, 100));
        exercise.setSecond(dataFactory.getNumberBetween(1, 10));
        return exercise;
    }
}
