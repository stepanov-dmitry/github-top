/*
 *
 *     Copyright 2018 Dmitry Stepanov to Present.
 *
 *     All rights reserved.
 *
 */

package ru.mera.service.aggregator;

import ru.mera.entity.Project;
import ru.mera.service.GithubService;
import ru.mera.service.exception.ServiceNotAvailableException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RequestsAggregator implements ProjectsAggregator {

    private int perPage;
    private int remainder;
    private int pagesAmount;
    private ExecutorService executor;
    private final GithubService service;
    private final List<Future<List<Project>>> futures;

    public static final int MAX_PER_PAGE = 100;

    public RequestsAggregator(int projectsAmount) {
        if (projectsAmount <= 0) throw new IllegalArgumentException("Amount of projects can be only more that 0.");
        if (projectsAmount <= MAX_PER_PAGE) perPage = projectsAmount;
        else if (projectsAmount > MAX_PER_PAGE) {
            pagesAmount = projectsAmount / MAX_PER_PAGE;
            perPage = MAX_PER_PAGE;
            remainder = projectsAmount - pagesAmount * perPage;
        }
        futures = new ArrayList<>();
        service = new GithubService();
    }

    @Override
    public List<Project> requestProjects() throws ServiceNotAvailableException {
        try {
            List<Project> projects = new ArrayList<>();
            executor = Executors.newFixedThreadPool(10);
            requestPages();
            requestRemainder();
            collect(projects);
            checkAvailability(projects);
            return projects;
        } catch (Exception e) {
            throw new ServiceNotAvailableException(e);
        } finally {
            close();
        }
    }

    private void requestPages() {
        for (int i = 1; i <= pagesAmount; i++) {
            int page = i;
            futures.add(executor.submit(() -> service.getProjects(page, MAX_PER_PAGE)));
        }
    }

    private void requestRemainder() {
        if (remainder > 0)
            futures.add(executor.submit(() -> service.getProjects(pagesAmount + 1, remainder)));
    }

    private void collect(List<Project> projects) throws ExecutionException, InterruptedException {
        for (Future<List<Project>> future : futures)
            projects.addAll(future.get());
    }

    private void checkAvailability(List<Project> projects) throws ServiceNotAvailableException {
        projects.removeIf(Objects::isNull);
        if (projects.isEmpty()) throw new ServiceNotAvailableException();
    }

    private void close() {
        if (executor != null) executor.shutdown();
    }
}