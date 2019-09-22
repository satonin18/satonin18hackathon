package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestServiceControllerTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("---------@Test-@Before--------------");

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("---------@Test-@After--------------");
    }

    @Test
    public void clear() {
        final String API_ROOT = "http://10.32.101.63:8080/hackathon";
        Response r = RestAssured.get(API_ROOT + "/clear");

        assertEquals(200,r.getStatusCode());
        System.out.println(200);
    }

    @Test
    public void save_person() {

    }

    @Test
    public void save_car() {
    }

    @Test
    public void get_personwithcars() {
    }

    @Test
    public void statistics() {
    }

}