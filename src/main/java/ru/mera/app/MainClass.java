/*
 *
 *     Copyright 2018 Dmitry Stepanov to Present.
 *
 *     All rights reserved.
 *
 */

package ru.mera.app;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mera.domain.WeightIdentifier;
import ru.mera.entity.Project;
import ru.mera.service.aggregator.ProjectsAggregator;
import ru.mera.service.exception.ServiceNotAvailableException;

import java.util.List;

import static ru.mera.util.LoggingUtil.getCurrentClassName;

public class MainClass {

    private static ProjectsAggregator aggregator;

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            aggregator = context.getBean(ProjectsAggregator.class);
            logger.info("Getting response from GitHub REST web service...");
            long from = System.currentTimeMillis();

            List<Project> projects = aggregator.requestProjects();
            WeightIdentifier identifier = new WeightIdentifier();
            identifier.identifyWeight(projects);
            List<Project> top10 = identifier.identifyTops(projects);
            logResults(top10);

            long to = System.currentTimeMillis();
            logTimeOfExecution(from, to);
        } catch (IllegalArgumentException e) {
            logger.error("Amount of requested projects can't be less than one.");
        } catch (ServiceNotAvailableException e) {
            logger.info("Github REST API isn't available now. Please, try later.");
        } catch (Exception e) {
            logger.error("Error", e);
        }
    }

    private static void logResults(List<Project> top10) {
        logger.info("------------------------------------------");
        for (int i = 0; i < top10.size(); i++)
            logger.info(i + 1 + ". " + top10.get(i).getFullName() + " - " + top10.get(i).getWeight());
    }

    private static void logTimeOfExecution(long from, long to) {
        logger.info("------------------------------------------");
        logger.info("Time of execution: " + (to - from) * 0.001 + " seconds");
    }
}