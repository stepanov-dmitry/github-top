/*
 *
 *     Copyright 2018 Dmitry Stepanov to Present.
 *
 *     All rights reserved.
 *
 */

package ru.mera.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mera.entity.Project;
import ru.mera.entity.Response;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GithubService {

    private final ObjectMapper mapper;

    private static final String REQUEST_URL = "https://api.github.com/search/repositories?q=tetris";
    private static final String PER_PAGE_ATTR = "&per_page=";
    private static final String PAGE_NUMBER_ATTR = "&page=";

    public GithubService() {
        mapper = new ObjectMapper();
    }

    public List<Project> getProjects(int pageNumber, int perPage) {
        try {
            URL url = new URL(REQUEST_URL + PAGE_NUMBER_ATTR + pageNumber + PER_PAGE_ATTR + perPage);
            List<Project> projects = mapper.readValue(url, Response.class).getProjects();
            return projects;
        } catch (IOException e) {
            return null;
        }
    }
}