package com.filRouge;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Client;
import com.filRouge.model.Contact;
import com.filRouge.repository.ClientRepository;
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

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ContactService contactService;

    private Contact contact;
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        client = new Client();
        client.setId(1L);
        client.setUsername("Test Client");

        contact = new Contact();
        contact.setId(1L);
        contact.setMessage("Test Message");
    }

    @Test
    void testCreateContact() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact createdContact = contactService.createContact(1L, contact);

        assertNotNull(createdContact);
        assertEquals(contact.getId(), createdContact.getId());
        assertEquals(contact.getMessage(), createdContact.getMessage());

        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    void testCreateContact_ClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            contactService.createContact(1L, contact);
        });

        assertEquals("Client non trouvé avec l'id : 1", exception.getMessage());
        verify(contactRepository, never()).save(contact);
    }

    @Test
    void testFindById() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Contact foundContact = contactService.findById(1L);

        assertNotNull(foundContact);
        assertEquals(contact.getId(), foundContact.getId());

        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        Contact foundContact = contactService.findById(1L);

        assertNull(foundContact);
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteContact() {
        contactService.deleteContact(1L);
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);

        when(contactRepository.findAll()).thenReturn(contactList);

        List<Contact> foundContacts = contactService.getAllContacts();

        assertNotNull(foundContacts);
        assertEquals(1, foundContacts.size());

        verify(contactRepository, times(1)).findAll();
    }
}
