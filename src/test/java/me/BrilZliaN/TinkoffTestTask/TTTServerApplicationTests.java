package me.BrilZliaN.TinkoffTestTask;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import me.BrilZliaN.TinkoffTestTask.db.ApplicationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TTTServerApplicationTests {
	
    @LocalServerPort
    private int port;

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ApiController controller;
	
    @Autowired
    private TestRestTemplate rest;
	
    //context
    
	@Test
	public void contextLoads() {
		assert applicationService != null;
		assert controller != null;
		assert rest != null;
	}
	
	//Json tests
	
	@Test
	public void jsonNoParams() {
		assert rest.getForObject("http://localhost:" + port + "/json/getApplication", String.class).contains("ERROR_MESSAGE");
	}
	
	@Test
	public void jsonStringParam() {
		assert rest.getForObject("http://localhost:" + port + "/json/getApplication?contactId=test", String.class).contains("ERROR_MESSAGE");
	}
	
	@Test
	public void jsonNegativeParam() {
		assert rest.getForObject("http://localhost:" + port + "/json/getApplication?contactId=-1", String.class).contains("ERROR_MESSAGE");
	}
	
	@Test
	public void jsonTooBigParam() {
		Error error = rest.getForObject("http://localhost:" + port + "/json/getApplication?contactId=22222222222222222222222222", Error.class);
		assert error != null && error.getErrorMessage().equals("Parameter 'contactId' should be an integer");
	}
	
	@Test
	public void jsonOk() {
		String resp = rest.getForObject("http://localhost:" + port + "/json/getApplication?contactId=2", String.class);
		assert resp != null && resp.equals("{\"APPLICATION_ID\":5,\"CONTACT_ID\":2,\"DT_CREATED\":\"25.06.2018 21:00:29\",\"PRODUCT_NAME\":\"debit_card\"}");
	}
	
	//XML tests
	
	@Test
	public void xmlNoParams() {
		assert rest.getForObject("http://localhost:" + port + "/xml/getApplication", String.class).contains("ERROR_MESSAGE");
	}
	
	@Test
	public void xmlStringParam() {
		assert rest.getForObject("http://localhost:" + port + "/xml/getApplication?contactId=test", String.class).contains("ERROR_MESSAGE");
	}
	
	@Test
	public void xmlNegativeParam() {
		assert rest.getForObject("http://localhost:" + port + "/xml/getApplication?contactId=-1", String.class).contains("ERROR_MESSAGE");
	}
	
	@Test
	public void xmlOk() {
		String resp = rest.getForObject("http://localhost:" + port + "/xml/getApplication?contactId=2", String.class);
		assert resp != null && resp.equals("<Application><APPLICATION_ID>5</APPLICATION_ID><CONTACT_ID>2</CONTACT_ID><DT_CREATED>25.06.2018 21:00:29</DT_CREATED><PRODUCT_NAME>debit_card</PRODUCT_NAME></Application>");
	}
	
	//db
	
	@Test
	public void testSerivce() {
		Optional<Application> a = applicationService.getApplicationById(5);
		Optional<Application> b = applicationService.getApplicationByContactId(2);
		assert a.isPresent() && b.isPresent() && a.get().getId() == b.get().getId() && a.get().getContactId() == b.get().getContactId();
	}

}