package com.filRouge.service;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Client;
import com.filRouge.model.Contact;
import com.filRouge.repository.ClientRepository;
import com.filRouge.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Contact createContact(Contact contact) {
      return contactRepository.save(contact);
    }

    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
