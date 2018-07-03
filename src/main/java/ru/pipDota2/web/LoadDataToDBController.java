package ru.pipDota2.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.*;
import ru.pipDota2.service.*;

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
    private final CharacteristicServiceImpl characteristicService;

    @Autowired
    public LoadDataToDBController(final HeroServiceImpl heroService,
                                  final AttackServiceImpl attackService,
                                  final TypeServiceImpl typeService,
                                  final SectionServiceImpl sectionService,
                                  final CharacteristicServiceImpl characteristicService){
        this.heroService = heroService;
        this.attackService = attackService;
        this.typeService = typeService;
        this.sectionService = sectionService;
        this.characteristicService = characteristicService;
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
                ("E:\\project\\java\\couseworks\\dataForDota2\\heroesAll.json"));
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
                    ("E:\\project\\java\\couseworks\\dataForDota2\\" + name[i] + ".json"));
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

    private List<Skill> getSkills(String nameHero) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray skills = (JSONArray) parser.parse(new FileReader
                ("E:\\project\\java\\couseworks\\dataForDota2\\skills.json"));
        List<Skill> list = new ArrayList<>();
        for (Object object : skills) {
            JSONObject dataForSkill = (JSONObject) object;
            String name = (String)dataForSkill.get("owner");
            if (name.equals(nameHero)){
                list.add(Skill.of(
                        (String)dataForSkill.get("name"),
                        (String)dataForSkill.get("img"),
                        (String)dataForSkill.get("description")
                ));
            }
        }
        return list;
    }

    private List<Skill> getSkills(String nameHero, String description) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray skills = (JSONArray) parser.parse(new FileReader
                ("E:\\project\\java\\couseworks\\dataForDota2\\skills.json"));
        List<Skill> list = new ArrayList<>();
        for (Object object : skills) {
            JSONObject dataForSkill = (JSONObject) object;
            String name = (String)dataForSkill.get("owner");
            if (name.equals(nameHero)){
                list.add(Skill.of(
                        (String)dataForSkill.get("name"),
                        (String)dataForSkill.get("img"),
                        description
                ));
            }
        }
        return list;
    }

    @GetMapping("/load/characteristics")
    public String loadCharacteristics() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray info = (JSONArray) parser.parse(new FileReader
                ("E:\\project\\java\\couseworks\\dataForDota2\\info.json"));
        JSONArray stats = (JSONArray) parser.parse(new FileReader
                ("E:\\project\\java\\couseworks\\dataForDota2\\stats.json"));
        //List<Characteristic> characteristics = new ArrayList<>();

        for (int i = 0; i < info.size(); i++) {
            JSONObject dataForStat = (JSONObject)stats.get(i);
            String nameHero = (String)dataForStat.get("name");
            Stat stat = Stat.of(
                    (String)dataForStat.get("level"),
                    (String)dataForStat.get("damage"),
                    (String)dataForStat.get("health"),
                    (String)dataForStat.get("mana"),
                    (String)dataForStat.get("defence"),
                    (String)dataForStat.get("timeAttack"),
                    (String)dataForStat.get("attackInSecond"),
                    (String)dataForStat.get("vision"),
                    (String)dataForStat.get("distanceAttack")
            );
            JSONObject dataForInfo = (JSONObject)info.get(i);
            String name = (String)dataForInfo.get("name");
            if (!name.equals(nameHero)){
                return "sort error";
            }
            boolean result;
            try{
                result = characteristicService.saveCharacteristics(Characteristic.of(
                        (String)dataForInfo.get("img"),
                        (String)dataForInfo.get("bio"),
                        (String)dataForInfo.get("video"),
                        (String)dataForInfo.get("power"),
                        (String)dataForInfo.get("agility"),
                        (String)dataForInfo.get("intelligence"),
                        (String)dataForInfo.get("damage"),
                        (String)dataForInfo.get("defence"),
                        (String)dataForInfo.get("speed"),
                        (String)dataForInfo.get("ability1"),
                        (String)dataForInfo.get("ability2"),
                        (String)dataForInfo.get("ability3"),
                        (String)dataForInfo.get("ability4"),
                        stat,
                        getSkills(nameHero),
                        heroService.getHeroById(i+1)
                ));
            } catch (Exception e){
                stat = Stat.of(
                        (String)dataForStat.get("level"),
                        (String)dataForStat.get("damage"),
                        (String)dataForStat.get("health"),
                        (String)dataForStat.get("mana"),
                        (String)dataForStat.get("defence"),
                        (String)dataForStat.get("timeAttack"),
                        (String)dataForStat.get("attackInSecond"),
                        (String)dataForStat.get("vision"),
                        (String)dataForStat.get("distanceAttack")
                );
                result = characteristicService.saveCharacteristics(Characteristic.of(
                        (String)dataForInfo.get("img"),
                        "bio",
                        (String)dataForInfo.get("video"),
                        (String)dataForInfo.get("power"),
                        (String)dataForInfo.get("agility"),
                        (String)dataForInfo.get("intelligence"),
                        (String)dataForInfo.get("damage"),
                        (String)dataForInfo.get("defence"),
                        (String)dataForInfo.get("speed"),
                        (String)dataForInfo.get("ability1"),
                        (String)dataForInfo.get("ability2"),
                        (String)dataForInfo.get("ability3"),
                        (String)dataForInfo.get("ability4"),
                        stat,
                        getSkills(nameHero, "description"),
                        heroService.getHeroById(i+1)
                ));
            }
            if(!result){
                return "error";
            }
        }
        return "data was loaded successes";
    }
}
