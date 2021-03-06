package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Subject;
import ru.pipDota2.service.SubjectService;

@RestController
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(final SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping("/get/item")
    public Subject getSubject(@RequestParam("id") int id){
        return subjectService.getSubjectById(id);
    }
}
