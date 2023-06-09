package ru.bespalyy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeCotroller {

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }
}
