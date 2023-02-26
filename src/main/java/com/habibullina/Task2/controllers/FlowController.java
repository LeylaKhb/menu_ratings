package com.habibullina.Task2.controllers;

import com.habibullina.Task2.models.menu_ratings_generator.MenuRatingsGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class FlowController {

    @PostMapping("/")
    public @ResponseBody Map<String, Object> index(@RequestBody String req) {
        MenuRatingsGenerator generator = new MenuRatingsGenerator(req);
        return generator.makeMenuRatings();
    }
}
