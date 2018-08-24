package com.db.math_exercise.exams;

import com.db.heroes_game.characters.RandomFactory;
import lombok.Builder;

@Builder
public class StandardExerciseGeneratorAssistant implements ExerciseGeneratorAssistant {

    private int min;
    private int max;

    @Override
    public Exercise generateTemplate() {
        int a = RandomFactory.getInstance().getNumberBetween(min, max);
        int b = RandomFactory.getInstance().getNumberBetween(min, max);
        return Exercise.builder().a(a).b(b).build();
    }
}
