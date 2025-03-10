package com.example.magasklad.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "MagaliF5");
        return "homePage";
    }

    @PostMapping("calculate")
    public String calculate(@RequestParam("operand1") double operand1,
                            @RequestParam("operand2") double operand2,
                            @RequestParam("operator") String operator,
                            Model model) {
        double result = switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "/" -> operand1 / operand2;
            case "*" -> operand1 * operand2;
            default -> 0.0;
        };
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/calculator")
    public String calculator() {
        return "calculator";
    }

    @GetMapping("/currency-converter")
    public String convertCurrency() {
        return "converter";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("count") double count,
                          @RequestParam("from-convert") String from_convert,
                          @RequestParam("to_convert") String to_convert,
                          Model model) {
        double result = count;
        switch (from_convert) {
            case "RUB" -> {
                if (to_convert.equals("EUR")) result = count * 0.009356;
                else if (to_convert.equals("USD")) result = count * 0.010198;
            }
            case "USD" -> {
                if (to_convert.equals("RUB")) result = count * 98.06;
                else if (to_convert.equals("EUR")) result = count * 0.93568;
            }
            case "EUR" -> {
                if (to_convert.equals("USD")) result = count * 1.07;
                else if (to_convert.equals("USD")) result = count * 106.89;
            }
        }
        model.addAttribute("result", result);
        return "result";
    }
}

