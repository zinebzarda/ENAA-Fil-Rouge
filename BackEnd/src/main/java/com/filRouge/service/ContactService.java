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

    public Contact createContact(Long clientId, Contact contact) {
        // Find the client by ID
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + clientId));

        // Set the client on the contact (assuming there’s a method for that in Contact class)
        contact.setClient(client); // Modify if necessary based on your Contact entity structure

        // Save the contact
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
