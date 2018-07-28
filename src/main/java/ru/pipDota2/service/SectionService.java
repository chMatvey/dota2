package ru.pipDota2.service;

import ru.pipDota2.domain.Section;

public interface SectionService {
    Iterable<Section> saveSections(Iterable<Section> sections);

    Iterable<Section> getAllSections();
}
