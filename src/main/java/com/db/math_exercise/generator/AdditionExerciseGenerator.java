package com.db.math_exercise.generator;

import com.db.math_exercise.exercise.AdditionExercise;
import com.db.math_exercise.exercise.Exercise;
import org.fluttercode.datafactory.impl.DataFactory;

public class AdditionExerciseGenerator implements ExerciseGenerator {

    private DataFactory dataFactory = new DataFactory();

    @Override
    public Exercise generateExercise() {
        Exercise exercise = new AdditionExercise();
        exercise.setFirst(dataFactory.getNumberBetween(1, 100));
        exercise.setSecond(dataFactory.getNumberBetween(1, 100));
        return exercise;
    }
}
