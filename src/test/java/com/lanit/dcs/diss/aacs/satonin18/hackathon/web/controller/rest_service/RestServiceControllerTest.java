package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.PersonService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@ContextConfiguration(locations = {"classpath:com/lanit/dcs/diss/aacs/satonin18/hackathon/config/SpringMvcConfig.java"})
//@WebAppConfiguration
//@ActiveProfiles(profiles = "local")
public class RestServiceControllerTest {

    @InjectMocks
    private RestServiceController restServiceController;

    private MockMvc mockMvc;

//    @Autowired
//    PersonService personService;

//    @Autowired
//    WebApplicationContext webApplicationContext;
//
//    @Mock
//    private PersonService personService;

    @Before
    public void setUp() throws Exception {
        System.out.println("---------@Test-@Before--------------");

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(restServiceController)
//                .addFilters(new CORSFilter())
                .build();

//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(webApplicationContext)
//                .build();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("---------@Test-@After--------------");
    }

    @Test
    public void clear() throws Exception {
        //ВНЕШНИЕ ТЕСТЫ (при запущенном сервере, также как soapUi)
//        final String API_ROOT = "http://10.32.101.63:8080/hackathon";
//        Response r = RestAssured.get(API_ROOT + "/clear");
//        assertEquals(200,r.getStatusCode());

        System.out.println(200);

        this.mockMvc.perform(get("/clear")).andExpect(status().isOk());

        System.out.println("OKKKKKKKKKKKKKKKKKKK");
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