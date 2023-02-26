package com.habibullina.Task2.models.teeth_components;

import com.habibullina.Task2.models.teeth_components.TeethDescription;

import java.util.ArrayList;

public class TeethOrder {
    private long id;
    private ArrayList<TeethDescription> teeth;

    @Override
    public String toString() {
        return "TeethOrder{" +
                "id=" + id +
                ", teeth=" + teeth +
                '}';
    }

    public ArrayList<TeethDescription> getTeeth() {
        return teeth;
    }


}
