package ru.mera.service;

import org.junit.Test;

import static org.junit.Assert.fail;

public class GithubServiceTest {

    @Test
    public void getProjects() {
        GithubService service = new GithubService();
        try {
            service.getProjects(0, 1);
            fail("Exception was expected for values that less than 1");
        } catch (Exception e) {
        }

        try {
            service.getProjects(-1, 5);
            fail("Exception was expected for values that less than 1");
        } catch (Exception e) {
        }

        try {
            service.getProjects(-1, -5);
            fail("Exception was expected for values that less than 1");
        } catch (Exception e) {
        }
    }
}