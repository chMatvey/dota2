package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Section;
import ru.pipDota2.service.SectionService;

@RestController
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(final SectionService sectionService){
        this.sectionService = sectionService;
    }

    @GetMapping("/get/sections")
    public Iterable<Section> getAllSections(){
        return sectionService.getAllSections();
    }
}
