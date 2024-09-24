package com.filRouge.controller;

import com.filRouge.model.Contact;
import com.filRouge.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestParam Long clientId, @RequestBody Contact contact) {
        // Crée un contact avec l'ID du client
        Contact createdContact = contactService.createContact(clientId, contact);
        return ResponseEntity.ok(createdContact);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.findById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build(); // Retourne 404 si le contact n'est pas trouvé
        }
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build(); // Retourne 204 No Content
    }
}
