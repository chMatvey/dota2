package ru.pipDota2.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class LoadDataToDBController {
    private final HeroServiceImpl heroService;
    private final AttackServiceImpl attackService;
    private final TypeServiceImpl typeService;

    @Autowired
    public LoadDataToDBController(final HeroServiceImpl heroService,
                                  final AttackServiceImpl attackService,
                                  final TypeServiceImpl typeService){
        this.heroService = heroService;
        this.attackService = attackService;
        this.typeService = typeService;
    }

    @GetMapping("/load/heroes")
    public String loadJson() throws IOException, ParseException {
        attackService.saveAttack(Attack.of("melee"));
        attackService.saveAttack(Attack.of("range"));
        typeService.saveType(Type.of("/img/icon-str.png"));
        typeService.saveType(Type.of("/img/icon-agi.png"));
        typeService.saveType(Type.of("/img/icon-int.png"));
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(new FileReader
                ("E:\\project\\java\\couseworks\\dota2\\src\\main\\resources\\heroesAll.json"));
        List<Hero> heroes = new ArrayList<Hero>();
        for (Object object : array) {
            JSONObject data = (JSONObject) object;
            Hero hero = Hero.of(
                    (String)data.get("name"),
                    (String)data.get("role"),
                    (String)data.get("img"),
                    attackService.getAttackByName((String)data.get("attack")),
                    typeService.getTypeByImg((String)data.get("type")));
            heroes.add(hero);
        }
        if (heroService.saveHero(heroes)){
            return "data was loaded successes";
        } else {
            return "error";
        }
    }
}
