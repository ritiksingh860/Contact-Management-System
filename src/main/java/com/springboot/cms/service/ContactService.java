package com.springboot.cms.service;

import com.springboot.cms.exception.BusinessException;
import com.springboot.cms.exception.ResourceNotFoundException;
import com.springboot.cms.model.Contacts;

import java.util.List;
import java.util.Map;

public interface ContactService {

    List<Contacts> getAllContacts();

    Contacts getContactById(Long ContactId) throws ResourceNotFoundException;

    List<Contacts> getContacts(String value, String type);
    
    Contacts createContact(Contacts Contact);

    Contacts updateContact(Contacts contacts) throws BusinessException;

    Map<String, Boolean> deleteContact(Long ContactId) throws ResourceNotFoundException;
}
