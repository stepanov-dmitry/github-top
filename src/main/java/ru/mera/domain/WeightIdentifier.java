/*
 *
 *     Copyright 2018 Dmitry Stepanov to Present.
 *
 *     All rights reserved.
 *
 */

package ru.mera.domain;

import ru.mera.entity.Project;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WeightIdentifier {

    private static final int TOP = 10;
    private static final int YEARS = 2;
    private static final int FORK_FACTOR = 3;
    private static final int MEGABYTE = 1024;
    private static final int YEAR_FACTOR = 20;
    private static final int ODD_ID_FACTOR = 30;
    private static final int LANGUAGE_FACTOR = 5;
    private static final double SIZE_FACTOR = 0.1;
    private static final String LANGUAGE = "Java";

    public void identifyWeight(List<Project> projects) {
        projects.parallelStream().forEach(p -> p.setWeight(p.getWeight() + p.getForks() * FORK_FACTOR));
        projects.parallelStream().forEach(p -> p.setWeight(p.getWeight() + p.getWatchers()));
        projects.parallelStream().forEach(p -> p.setWeight(p.getWeight() - p.getOpenIssues()));
        projects.parallelStream().filter(p -> p.getOwner().getId() % 2 != 0)
                .forEach(p -> p.setWeight(p.getWeight() - ODD_ID_FACTOR));
        projects.parallelStream().forEach(p -> p.setWeight(p.getWeight() +
                ((int) (p.getSize() / MEGABYTE)) * SIZE_FACTOR));
        projects.parallelStream().filter(p -> p.getLanguage() == null).forEach((p) -> p.setLanguage(""));
        projects.parallelStream().filter(p -> p.getLanguage().equalsIgnoreCase(LANGUAGE))
                .forEach(p -> p.setWeight(p.getWeight() + LANGUAGE_FACTOR));
        projects.parallelStream().filter(p -> ChronoUnit.YEARS.between(p.getDate(), LocalDateTime.now()) >= YEARS)
                .forEach(p -> p.setWeight(p.getWeight() - YEAR_FACTOR));
    }

    public List<Project> identifyTops(List<Project> projects) {
        Collections.sort(projects, Comparator.comparingDouble(Project::getWeight).reversed());
        List<Project> top10 = projects.stream().limit(TOP).collect(Collectors.toList());
        return top10;
    }
}