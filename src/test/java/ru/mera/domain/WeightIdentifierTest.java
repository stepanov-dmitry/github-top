package ru.mera.domain;

import org.junit.Before;
import org.junit.Test;
import ru.mera.entity.Owner;
import ru.mera.entity.Project;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WeightIdentifierTest {

    private WeightIdentifier identifier;
    private List<Project> projects;

    @Before
    public void setUp() {
        identifier = new WeightIdentifier();
        projects = new ArrayList<>();
    }

    @Test
    public void identifyWeight() {
        Project project = new Project();
        Owner owner = new Owner();
        LocalDateTime date = LocalDateTime.now();
        date = date.minus(Period.ofYears(3));
        owner.setId(45345);
        project.setForks(40);
        project.setWatchers(250);
        project.setOpenIssues(5);
        project.setOwner(owner);
        project.setSize(5120);
        project.setLanguage("Java");
        project.setDate(date);
        projects.add(project);
        identifier.identifyWeight(projects);

        assertEquals(projects.get(0).getWeight(), 320.5, 0.1);
    }

    @Test
    public void identifyTop() {
        List<Project> projects = new ArrayList<>();
        Project project = new Project();
        project.setWeight(2345);
        projects.add(project);
        project = new Project();
        project.setWeight(3212);
        projects.add(project);
        project = new Project();
        project.setWeight(12);
        projects.add(project);
        project = new Project();
        project.setWeight(235.5);
        projects.add(project);
        project = new Project();
        project.setWeight(5000);
        projects.add(project);
        project = new Project();
        project.setWeight(1000);
        projects.add(project);
        project = new Project();
        project.setWeight(-50);
        projects.add(project);
        project = new Project();
        project.setWeight(-100);
        projects.add(project);
        project = new Project();
        project.setWeight(-5000);
        projects.add(project);
        project = new Project();
        project.setWeight(0);
        projects.add(project);

        List<Project> top = identifier.identifyTop(projects);
        assertEquals(top.get(0).getWeight(), 5000, 0.1);
        assertEquals(top.get(1).getWeight(), 3212, 0.1);
        assertEquals(top.get(2).getWeight(), 2345, 0.1);
        assertEquals(top.get(3).getWeight(), 1000, 0.1);
        assertEquals(top.get(4).getWeight(), 235.5, 0.1);
        assertEquals(top.get(5).getWeight(), 12, 0.1);
        assertEquals(top.get(6).getWeight(), 0, 0.1);
        assertEquals(top.get(7).getWeight(), -50, 0.1);
        assertEquals(top.get(8).getWeight(), -100, 0.1);
        assertEquals(top.get(9).getWeight(), -5000, 0.1);
    }
}