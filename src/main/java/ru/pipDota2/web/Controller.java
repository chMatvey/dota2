package ru.pipDota2.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Attack;
import ru.pipDota2.domain.Hero;
import ru.pipDota2.domain.Type;
import ru.pipDota2.service.AttackServiceImpl;
import ru.pipDota2.service.HeroServiceImpl;
import ru.pipDota2.service.TypeServiceImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @GetMapping("/")
    public String getHello(){
        return "Hello World";
    }
}
