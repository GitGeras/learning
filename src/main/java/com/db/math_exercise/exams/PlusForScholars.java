package com.db.math_exercise.exams;

public class PlusForScholars implements ExerciseGenerator {
    private ExerciseGeneratorAssistant assistant = new StandardExerciseGeneratorAssistant(0, 100);

    @Override
    public Exercise generate() {
        Exercise exercise = assistant.generateTemplate();
        exercise.setOperation(Operation.PLUS);
        exercise.setAnswer(exercise.getA() + exercise.getB());
        return exercise;
    }
}
