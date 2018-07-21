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

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    @JsonProperty("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                '}';
    }
}