package com.db.math_exercise;

import com.db.math_exercise.exercise.*;
import com.db.math_exercise.generator.*;
import lombok.SneakyThrows;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ExaminatorService {
    private DataFactory dataFactory = new DataFactory();

    public Collection<Exercise> getExercises() {
        List<Exercise> exercises = new ArrayList<>();

        List<Class<? extends ExerciseGenerator>> exerciseGeneratorClasses = getExerciseGeneratorClasses();
        for (int i = 0; i < getExercisesSize(); i++) {
            Class<? extends ExerciseGenerator> exerciseClass = dataFactory.getItem(exerciseGeneratorClasses);
            exercises.add(generateExercise(exerciseClass));
        }

        return exercises;
    }

    @SneakyThrows
    private Exercise generateExercise(Class<? extends ExerciseGenerator> exerciseGeneratorClass) {
        ExerciseGenerator exerciseGenerator = exerciseGeneratorClass.newInstance();
        return exerciseGenerator.generateExercise();
    }

    private int getExercisesSize() {
        return 10;
    }

    private List<Class<? extends ExerciseGenerator>> getExerciseGeneratorClasses() {
        return Arrays.asList(AdditionExerciseGenerator.class,
                SubtractionExerciseGenerator.class,
                MultiplicationExerciseGenerator.class,
                DivisionExerciseGenerator.class);
    }
}
