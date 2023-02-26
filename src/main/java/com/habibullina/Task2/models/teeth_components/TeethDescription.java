package com.habibullina.Task2.models.teeth_components;

import java.util.Map;

public class TeethDescription {

//    private TeethTask task;
    private Map<String, Object> task;

//    public static void main(String[] args) {
//        for (Map.Entry<String, Map<String, Object>> pair : task.entrySet()) {
//            TeethObject to = new TeethObject(pair.getValue());
//        }
//    }

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
