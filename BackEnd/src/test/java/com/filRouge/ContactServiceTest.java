package com.filRouge;

import com.filRouge.model.Contact;
import com.filRouge.repository.ContactRepository;
import com.filRouge.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    private Contact contact;

    @BeforeEach
    void setUp() {
        // Initialise les mocks
        MockitoAnnotations.openMocks(this);

        // Crée un contact exemple pour les tests
        contact = new Contact();
        contact.setId(1L);
        contact.setMessage("Test Message");

    }

    @Test
    void testCreateContact() {
        // Simule le comportement du repository lors de la sauvegarde
        when(contactRepository.save(contact)).thenReturn(contact);

        // Appelle la méthode createContact
        Contact createdContact = contactService.createContact(contact);

        // Vérifie que le contact est bien retourné
        assertNotNull(createdContact);
        assertEquals(contact.getId(), createdContact.getId());
        assertEquals(contact.getMessage(), createdContact.getMessage());

        // Vérifie que la méthode save du repository a été appelée une fois
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    void testFindById() {
        // Simule le comportement du repository lors de la recherche par ID
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        // Appelle la méthode findById
        Contact foundContact = contactService.findById(1L);

        // Vérifie que le contact est bien retourné
        assertNotNull(foundContact);
        assertEquals(contact.getId(), foundContact.getId());

        // Vérifie que la méthode findById du repository a été appelée une fois
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Simule le comportement du repository lorsque l'ID n'est pas trouvé
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        // Appelle la méthode findById
        Contact foundContact = contactService.findById(1L);

        // Vérifie que la méthode retourne null
        assertNull(foundContact);

        // Vérifie que la méthode findById du repository a été appelée une fois
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteContact() {
        // Appelle la méthode deleteContact
        contactService.deleteContact(1L);

        // Vérifie que la méthode deleteById du repository a été appelée une fois
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllContacts() {
        // Simule une liste de contacts
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);

        // Simule le comportement du repository lors de la récupération de tous les contacts
        when(contactRepository.findAll()).thenReturn(contactList);

        // Appelle la méthode getAllContacts
        List<Contact> foundContacts = contactService.getAllContacts();

        // Vérifie que la liste n'est pas vide
        assertNotNull(foundContacts);
        assertEquals(1, foundContacts.size());

        // Vérifie que la méthode findAll du repository a été appelée une fois
        verify(contactRepository, times(1)).findAll();
    }
}
