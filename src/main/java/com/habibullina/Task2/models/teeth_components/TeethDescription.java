package com.habibullina.Task2.models.teeth_components;

import java.util.Map;

public class TeethDescription {

    private Map<String, Object> task;

    @Override
    public String toString() {
        return "TeethDescription{" +
                "task=" + task +
                '}';
    }

    public Map<String, Object> getTask() {
        return task;
    }
}
