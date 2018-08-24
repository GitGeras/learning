package com.db.math_exercise.exercise;

import lombok.Data;
import lombok.SneakyThrows;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Data
public abstract class Exercise {
    private int first;
    private int second;

    public abstract Operator getOperator();

    @SneakyThrows
    public int getAnswer() {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("javascript");
        Object evaluated = engine.eval("" + getFirst() + getOperator() + getSecond());
        if (evaluated instanceof Double){
            Double answer = (Double) evaluated;
            return answer.intValue();
        }else {
            return (Integer) evaluated;
        }
    }

    @Override
    public String toString() {
        return getFirst() + " " + getOperator() + " " + getSecond() + " = " + getAnswer();
    }
}
