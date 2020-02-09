package ipr.web.controller.rest_service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
    public void BAD_clear() throws Exception {
        //ВНЕШНИЕ ТЕСТЫ (при запущенном сервере, также как soapUi)
//        final String API_ROOT = "http://10.32.101.63:8080/hackathon";
//        Response r = RestAssured.get(API_ROOT + "/clear");
//        assertEquals(200,r.getStatusCode());

        System.out.println(200);

        this.mockMvc.perform(get("/BAD_clear")).andExpect(status().isNotFound());

        System.out.println("BADDDDDDDDDD");
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