/*
 *
 *     Copyright 2018 Dmitry Stepanov to Present.
 *
 *     All rights reserved.
 *
 */

package ru.mera.service.aggregator;

import ru.mera.entity.Project;
import ru.mera.service.exception.ServiceNotAvailableException;

import java.util.List;

public interface ProjectsAggregator {

    List<Project> requestProjects() throws ServiceNotAvailableException;

}