package com.springboot.cms.service.impl;

import com.springboot.cms.exception.BusinessException;
import com.springboot.cms.exception.ResourceNotFoundException;
import com.springboot.cms.model.Contacts;
import com.springboot.cms.repository.ContactsRepository;
import com.springboot.cms.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactsRepository contactsRepository;

    @Override
    public List<Contacts> getAllContacts() {
        return contactsRepository.findAll();
    }

    @Override
    public Contacts getContactById(Long ContactId) throws ResourceNotFoundException {
        Contacts contact = contactsRepository.findById(ContactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + ContactId));
        return contact;
    }
    
    @Override
    public List<Contacts> getContacts(String value, String type) {
        if(type.equalsIgnoreCase("firstName")){
           return contactsRepository.findByFirstName(value);
        }else if(type.equalsIgnoreCase("lastName")){
           return contactsRepository.findByLastName(value);
        }else if(type.equalsIgnoreCase("emailId")){
           return contactsRepository.findByEmailId(value);
        }
        return new ArrayList<>();
    }

    @Override
    public Contacts createContact(Contacts Contact) {
        return contactsRepository.save(Contact);
    }

    @Override
    public Contacts updateContact(Contacts contacts) throws BusinessException{
        try {
            contactsRepository.findById(contacts.getId()).orElseThrow(()-> new ResourceNotFoundException("contact not found for this id :: "+contacts.getId()));
            return contactsRepository.save(contacts);
        }catch (Exception ex){
            throw new BusinessException("Something went Wrong :: "+ex.getCause());
        }
    }

    @Override
    public Map<String, Boolean> deleteContact(Long contactsId) throws ResourceNotFoundException {
        Contacts Contact = contactsRepository.findById(contactsId)
                .orElseThrow(() -> new ResourceNotFoundException("Contacts not found for this id :: " + contactsId));
        contactsRepository.delete(Contact);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
