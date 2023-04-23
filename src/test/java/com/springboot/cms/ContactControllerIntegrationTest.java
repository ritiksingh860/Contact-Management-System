package com.springboot.cms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.springboot.cms.model.Contacts;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllcontacts() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/contacts",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetContactById() {
		Contacts contact = restTemplate.getForObject(getRootUrl() + "/contacts/1", Contacts.class);
		System.out.println(contact.getFirstName());
		assertNotNull(contact);
	}

	@Test
	public void testCreateContact() {
		Contacts contact = new Contacts();
		contact.setEmailId("admin@gmail.com");
		contact.setFirstName("admin");
		contact.setLastName("admin");
		contact.setPhoneNumber("9876543321");

		ResponseEntity<Contacts> postResponse = restTemplate.postForEntity(getRootUrl() + "/contacts", contact, Contacts.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatecontact() {
		Contacts contact = restTemplate.getForObject(getRootUrl() + "/contacts" , Contacts.class);
		contact.setFirstName("admin1");
		contact.setLastName("admin2");
		contact.setPhoneNumber("9876554356");
		contact.setEmailId("admin@gmail.com");
		contact.setId(1);

		restTemplate.put(getRootUrl() + "/contacts", contact);

		Contacts updatedcontact = restTemplate.getForObject(getRootUrl() + "/contacts", Contacts.class);
		assertNotNull(updatedcontact);
	}

	@Test
	public void testDeletecontact() {
		int id = 2;
		Contacts contact = restTemplate.getForObject(getRootUrl() + "/contacts/" + id, Contacts.class);
		assertNotNull(contact);

		restTemplate.delete(getRootUrl() + "/contacts/" + id);

		try {
			contact = restTemplate.getForObject(getRootUrl() + "/contacts/" + id, Contacts.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
