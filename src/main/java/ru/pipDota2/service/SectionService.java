package ru.pipDota2.service;

import ru.pipDota2.domain.Section;

public interface SectionService {
    boolean saveSections(Iterable<Section> sections);

    Iterable<Section> getAllSections();
}
