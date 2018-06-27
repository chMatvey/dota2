package ru.pipDota2.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Hero;
import ru.pipDota2.service.HeroServiceImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    private final HeroServiceImpl heroService;

    @Autowired
    public Controller(final HeroServiceImpl heroService){
        this.heroService = heroService;
    }

    @GetMapping("/lol/lol")
    public String getLol(){
        return "Lol";
    }

    @GetMapping("/")
    public String getHello(){
        return "Hello World";
    }

    @GetMapping("/load/heroes")
    public String loadJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(new FileReader
                ("E:\\project\\java\\couseworks\\dota2\\src\\main\\resources\\heroesAll.json"));
        List<Hero> heroes = new ArrayList<Hero>();
        for (Object object : array) {
            JSONObject data = (JSONObject) object;
            Hero hero = Hero.of(
                    (String)data.get("name"),
                    (String)data.get("role"),
                    (String)data.get("attack"),
                    (String)data.get("img"),
                    (String)data.get("type"));
            heroes.add(hero);
        }
        if (heroService.saveHero(heroes)){
            return "data was loaded successes";
        } else {
            return "error";
        }
    }

    @GetMapping("/heroes")
    public List<Hero> getHeroes(@RequestParam("limit") int limit, @RequestParam("offset") int offset){
        return heroService.getHeroesLimitOffset(limit, offset);
    }
}
