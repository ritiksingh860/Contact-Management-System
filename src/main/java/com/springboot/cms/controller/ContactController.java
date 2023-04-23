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

import com.springboot.cms.repository.ContactsRepository;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
	@Autowired
	private ContactService contactService;

	@GetMapping("/contacts")
	public List<Contacts> getAllContacts() {
		return contactService.getAllContacts();
	}

	@GetMapping("/contacts/{id}")
	public ResponseEntity<Contacts> getContactById(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		try {
			Contacts contact = contactService.getContactById(contactId);
			return ResponseEntity.ok().body(contact);
		}catch (ResourceNotFoundException ex){
			throw new ResourceNotFoundException("contact not found for this id :: " + contactId);
		}

	}

	@PostMapping("/contacts")
	public Contacts createContact(@Valid @RequestBody Contacts contact) throws BusinessException {
		try {
			return contactService.createContact(contact);
		}catch (Exception ex){
			throw new BusinessException("Something went wrong :: "+ ex.getCause());
		}
	}

	@PutMapping("/contacts")
	public ResponseEntity<Contacts> updateContact(@Valid @RequestBody Contacts contacts) throws BusinessException {
		try {
			Contacts contact = contactService.updateContact(contacts);
			return ResponseEntity.ok(contact);
		} catch (Exception ex){
			throw new BusinessException("Contacts not found for this user : " +  contacts.getEmailId());
		}
	}

	@DeleteMapping("/contacts/{id}")
	public Map<String, Boolean> deleteContact(@PathVariable(value = "id") Long contactId)
			throws ResourceNotFoundException {
		return contactService.deleteContact(contactId);
	}
}
