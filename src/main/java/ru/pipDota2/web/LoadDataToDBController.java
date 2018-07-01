package ru.pipDota2.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.*;
import ru.pipDota2.service.AttackServiceImpl;
import ru.pipDota2.service.HeroServiceImpl;
import ru.pipDota2.service.SectionServiceImpl;
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
    private final SectionServiceImpl sectionService;

    @Autowired
    public LoadDataToDBController(final HeroServiceImpl heroService,
                                  final AttackServiceImpl attackService,
                                  final TypeServiceImpl typeService,
                                  final SectionServiceImpl sectionService){
        this.heroService = heroService;
        this.attackService = attackService;
        this.typeService = typeService;
        this.sectionService = sectionService;
    }

    @GetMapping("/load/heroes")
    public String loadHeroesJson() throws IOException, ParseException {
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

    @GetMapping("/load/sections")
    public String loadSectionsJson() throws IOException, ParseException {
        String[] name = new String[]{ "consumables", "attributes", "armaments", "arcane", "common", "support", "caster", "weapons", "armor", "artifacts", "secret" };
        String[] img = new String[]{ "/img/icons/itemcat_consumables.png", "/img/icons/itemcat_attributes.png", "/img/icons/itemcat_armaments.png", "/img/icons/itemcat_arcane.png", "/img/icons/itemcat_common.png", "/img/icons/itemcat_support.png", "/img/icons/itemcat_caster.png", "/img/icons/itemcat_weapons.png", "/img/icons/itemcat_armor.png", "/img/icons/itemcat_artifacts.png", "/img/icons/itemcat_secret.png" };
        List<Section> sections = new ArrayList<>();
        JSONParser parser = new JSONParser();
        for (int i = 0; i < name.length; i++){
            JSONArray array = (JSONArray) parser.parse(new FileReader
                    ("E:\\project\\java\\couseworks\\dota2\\src\\main\\resources\\subjects\\" + name[i] + ".json"));
            Section section = Section.of(name[i], img[i]);
            for (Object object : array) {
                JSONObject data = (JSONObject) object;
                String description;
                try {
                    description = (String)data.get("use");
                    if (description.length() < 5)
                        description = "Предмет";
                } catch (Exception e){
                    description = "Предмет";
                }
                Subject subject = Subject.of(
                        (String)data.get("name"),
                        (String)data.get("img"),
                        (String)data.get("price"),
                        description,
                        (String)data.get("description"));
                section.addSubject(subject);
            }
            sections.add(section);
        }
        if (sectionService.saveSections(sections)){
            return "data was loaded successes";
        } else {
            return "error";
        }
    }
}
