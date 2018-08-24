package com.db.math_exercise.generator;

import com.db.math_exercise.exercise.Exercise;
import com.db.math_exercise.exercise.MultiplicationExercise;
import org.fluttercode.datafactory.impl.DataFactory;

public class MultiplicationExerciseGenerator implements ExerciseGenerator {

    private DataFactory dataFactory = new DataFactory();

    @Override
    public Exercise generateExercise() {
        Exercise exercise = new MultiplicationExercise();
        exercise.setFirst(dataFactory.getNumberBetween(1, 10));
        exercise.setSecond(dataFactory.getNumberBetween(1, 10));
        return exercise;
    }
}
