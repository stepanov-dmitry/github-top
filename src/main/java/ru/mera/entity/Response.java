/*
 *
 *     Copyright 2018 Dmitry Stepanov to Present.
 *
 *     All rights reserved.
 *
 */

package ru.mera.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    @JsonProperty("total_count")
    private String totalCount;

    @JsonProperty("items")
    private List<Project> projects;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Response{" +
                "totalCount='" + totalCount + '\'' +
                ", projects=" + projects +
                '}';
    }
}