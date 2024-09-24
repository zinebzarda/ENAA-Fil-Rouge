package com.filRouge;

import com.filRouge.dto.ClientRequestDTO;
import com.filRouge.dto.ClientResponseDTO;
import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Client;
import com.filRouge.model.enums.Role;
import com.filRouge.repository.ClientRepository;
import com.filRouge.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private ClientRequestDTO clientRequestDTO;
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clientRequestDTO = new ClientRequestDTO();
        clientRequestDTO.setUsername("testUser");
        clientRequestDTO.setEmail("test@example.com");
        clientRequestDTO.setAdresse("123 Street");
        clientRequestDTO.setPassword("password123");

        client = new Client();
        client.setId(1L);
        client.setUsername("testUser");
        client.setEmail("test@example.com");
        client.setAdresse("123 Street");
        client.setRole(Role.CLIENT);
    }

    @Test
    void testCreateClient() {
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientResponseDTO createdClient = clientService.createClient(clientRequestDTO);

        assertNotNull(createdClient);
        assertEquals("testUser", createdClient.getUsername());
        assertEquals("test@example.com", createdClient.getEmail());

        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientArgumentCaptor.capture());
        assertEquals("encodedPassword", clientArgumentCaptor.getValue().getPassword());
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<ClientResponseDTO> clients = clientService.getAllClients();

        assertEquals(1, clients.size());
        assertEquals("testUser", clients.get(0).getUsername());
    }

    @Test
    void testFindById_ClientExists() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Optional<ClientResponseDTO> foundClient = clientService.findById(1L);

        assertTrue(foundClient.isPresent());
        assertEquals("testUser", foundClient.get().getUsername());
    }

    @Test
    void testFindById_ClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ClientResponseDTO> foundClient = clientService.findById(1L);

        assertFalse(foundClient.isPresent());
    }

    @Test
    void testUpdateClient() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientResponseDTO updatedClient = clientService.updateClient(1L, clientRequestDTO);

        assertEquals("testUser", updatedClient.getUsername());
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void testUpdateClient_ClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.updateClient(1L, clientRequestDTO));
    }

    @Test
    void testDeleteClient() {
        doNothing().when(clientRepository).deleteById(1L);

        assertDoesNotThrow(() -> clientService.deleteClient(1L));

        verify(clientRepository).deleteById(1L);
    }
}
