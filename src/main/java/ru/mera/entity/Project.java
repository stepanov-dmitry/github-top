/*
 *
 *     Copyright 2018 Dmitry Stepanov to Present.
 *
 *     All rights reserved.
 *
 */

package ru.mera.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @JsonProperty("forks")
    private int forks;

    @JsonProperty("watchers")
    private int watchers;

    @JsonProperty("open_issues")
    private int openIssues;

    @JsonProperty("size")
    private int size;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("language")
    private String language;

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    @JsonIgnore
    private double weight;

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public int getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(int openIssues) {
        this.openIssues = openIssues;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Project{" +
                "forks=" + forks +
                ", watchers=" + watchers +
                ", openIssues=" + openIssues +
                ", size=" + size +
                ", fullName='" + fullName + '\'' +
                ", language='" + language + '\'' +
                ", owner=" + owner +
                ", date=" + date +
                ", weight=" + weight +
                '}';
    }
}