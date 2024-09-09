package com.filRouge.service;

import com.filRouge.model.Contact;
import com.filRouge.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private  ContactRepository contactRepository;



    public Contact creerContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> afficherContacts() {
        return contactRepository.findAll();
    }
}