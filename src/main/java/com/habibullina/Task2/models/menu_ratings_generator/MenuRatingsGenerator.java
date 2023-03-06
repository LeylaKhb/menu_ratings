package com.habibullina.Task2.models.menu_ratings_generator;

import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.habibullina.Task2.models.teeth_components.*;
import com.sun.source.tree.Tree;


public class MenuRatingsGenerator {
    private final String rawContent;
    private final Gson gson = new Gson();
    private static Map<Long, TreeMap<String, Integer>> menusAndValuesList = new TreeMap<>();

    public MenuRatingsGenerator(String rawContent) {
        this.rawContent = rawContent;
    }

    public Map<String, Object> makeMenuRatings() {

        generateMenuRatings();
        List<Object> menuRatings = sortAndCreateListMenuRatings();

        Map<String, Object> result = new HashMap<>();
        result.put("menu_ratings", menuRatings);
        return result;
    }

    private void generateMenuRatings() {
        TeethOrder teethOrder = gson.fromJson(rawContent, TeethOrder.class);

        for (TeethDescription teeth : teethOrder.getTeeth()) {

            if (!teeth.getTask().isEmpty()) {
                for (Map.Entry<String, Object> pair : teeth.getTask().entrySet()) {
                    if (!pair.getKey().equals("color")) {
                        LinkedTreeMap<String, Object> valueOfPair = (LinkedTreeMap<String, Object>) pair.getValue();
                        Long menuId = Math.round((Double) valueOfPair.get("menu"));
                        String menuValue = (String) valueOfPair.get("value");
                        if (menusAndValuesList.containsKey(menuId)) {
                            TreeMap<String, Integer> existedMenuValues = menusAndValuesList.get(menuId);
                            if (existedMenuValues.containsKey(menuValue)) {
                                Integer countOfExistedMenuValue = existedMenuValues.get(menuValue) + 1;
                                Integer totalCount = existedMenuValues.get("total") + 1;
                                existedMenuValues.put(menuValue, countOfExistedMenuValue);
                                existedMenuValues.put("total", totalCount);
                                menusAndValuesList.put(menuId, existedMenuValues);
                            } else {
                                Integer totalCount = existedMenuValues.get("total") + 1;
                                existedMenuValues.put(menuValue, 1);
                                existedMenuValues.put("total", totalCount);
                                menusAndValuesList.put(menuId, existedMenuValues);
                            }
                        } else {
                            TreeMap<String, Integer> valueAndCount = new TreeMap<>();
                            valueAndCount.put(menuValue, 1);
                            valueAndCount.put("total", 1);
                            menusAndValuesList.put(menuId, valueAndCount);
                        }
                    }
                }
            }
        }
    }

    private List<Object> sortAndCreateListMenuRatings() {
        List<Object> menuRatings = new ArrayList<>();
        for (Map.Entry<Long, TreeMap<String, Integer>> pair : menusAndValuesList.entrySet()) {
            Integer total = 0;
            TreeMap<String, Integer> map = pair.getValue();
            List<Map.Entry<String, Integer>> list = (map).entrySet().stream()
                    .sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue())).toList();
            List<Object> titles = new ArrayList<>();
            for (Map.Entry<String, Integer> stringIntegerEntry : list) {
                if (!stringIntegerEntry.getKey().equals("total")) {
                    Map<String, Object> titleAndCounts = new TreeMap<>();
                    titleAndCounts.put("variant_rating", stringIntegerEntry.getValue());
                    titleAndCounts.put("title", stringIntegerEntry.getKey());
                    titles.add(titleAndCounts);
                } else {
                    total = stringIntegerEntry.getValue();
                }
            }
            Map<String, Object> menusAndCounts = new TreeMap<>();
            menusAndCounts.put("variants_rating", titles);
            menusAndCounts.put("menu_rating", total);
            menusAndCounts.put("menu_id", pair.getKey());
            menuRatings.add(menusAndCounts);
        }
        return menuRatings;
    }
}

