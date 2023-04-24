package com.springboot.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.springboot.cms.exception.BusinessException;
import com.springboot.cms.exception.ResourceNotFoundException;
import com.springboot.cms.model.Contacts;
import com.springboot.cms.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.springboot.cms.repository.ContactsRepository;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Contact-Mgmt", protocols = "http")
public class ContactController {
	@Autowired
	private ContactService contactService;

	@ApiOperation(value = "To access all the contact list", response = Contacts.class, code = 200)
	@GetMapping("/contacts/getAll")
	public List<Contacts> getAllContacts() {
		return contactService.getAllContacts();
	}

	@ApiOperation(value = "To access all the contact by contact id", response = Contacts.class, code = 200)	
	@GetMapping("/contacts/find/{id}")
	public ResponseEntity<Contacts> getContactById(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		try {
			Contacts contact = contactService.getContactById(contactId);
			return ResponseEntity.ok().body(contact);
		}catch (ResourceNotFoundException ex){
			throw new ResourceNotFoundException("contact not found for this id :: " + contactId);
		}

	}
	
	@ApiOperation(value = "To access all the contact list using value and type. Value can be user firstname,lastname or emailid and type have firstName/lastName/emailId", response = Contacts.class, code = 200)
	@GetMapping("/contacts/getContacts")
	public List<Contacts> getContacts(@RequestParam(value = "value", required = true) String value,
					@RequestParam(value = "type", required = true) String type) throws BusinessException {
		try {
			return contactService.getContacts(value, type);
		}catch (Exception ex){
			throw new BusinessException(ex.getMessage());
		}
	}

	@ApiOperation(value = "create the contact using contact object", response = Contacts.class, code = 200)
	@PostMapping("/contacts/create")
	public Contacts createContact(@Valid @RequestBody Contacts contact) throws BusinessException {
		try {
			return contactService.createContact(contact);
		}catch (Exception ex){
			throw new BusinessException("Something went wrong :: "+ ex.getCause());
		}
	}

	@ApiOperation(value = "update the contact using contact object", response = Contacts.class, code = 200)	
	@PutMapping("/contacts/update")
	public ResponseEntity<Contacts> updateContact(@Valid @RequestBody Contacts contacts) throws BusinessException {
		try {
			Contacts contact = contactService.updateContact(contacts);
			return ResponseEntity.ok(contact);
		} catch (Exception ex){
			throw new BusinessException("Contacts not found for this user : " +  contacts.getEmailId());
		}
	}

	@ApiOperation(value = "Delete the contact using contact id", response = Map.class, code = 200)	
	@DeleteMapping("/contacts/delete/{id}")
	public Map<String, Boolean> deleteContact(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		return contactService.deleteContact(contactId);
	}
}
