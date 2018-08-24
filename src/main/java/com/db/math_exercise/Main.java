package com.db.math_exercise;

import com.db.math_exercise.exercise.Exercise;

import javax.script.ScriptException;
import java.util.Collection;

public class Main {


    public static void main(String[] args) throws ScriptException {

        ExaminatorService examinatorService = new ExaminatorService();
        Collection<Exercise> exercises = examinatorService.getExercises();
        for (Exercise exercise : exercises) {
            System.out.println(exercise);
        }

    }
}
