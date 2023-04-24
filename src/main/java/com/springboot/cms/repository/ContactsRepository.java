package com.springboot.cms.repository;

import com.springboot.cms.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long>{
  
    List<Contacts> findByEmailId(String emailId);
    List<Contacts> findByFirstName(String emailId);
    List<Contacts> findByLastName(String emailId);

}
