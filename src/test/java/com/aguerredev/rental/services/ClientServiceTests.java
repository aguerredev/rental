package com.aguerredev.rental.services;

import com.aguerredev.rental.records.ClientRecord;
import com.aguerredev.rental.requests.ClientRequestObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientServiceTests {
    private ClientService clientService;

    @BeforeEach
    public void init() {
        clientService = new ClientService();
    }

    @Test
    public void addClientTest_Ok() {
        ClientRequestObject newClient = new ClientRequestObject("Test Client",
                LocalDate.of(1990,5,20));

        ClientRecord returnedClient = clientService.addClient(newClient);

        assertNotNull(returnedClient);
        assertNotEquals(0, clientService.getClients().size());
        assertEquals(newClient.getName(), returnedClient.name());
        assertEquals(newClient.getBirthday(), returnedClient.birthday());
    }

    @Test
    public void updateClientTest_Ok() {
        ClientRequestObject newClient = new ClientRequestObject("Test Client",
                LocalDate.of(1990,5,20));
        ClientRecord clientRecord = clientService.addClient(newClient);
        newClient = new ClientRequestObject("New Test Client",
                LocalDate.of(1992,3,22));

        Optional<ClientRecord> returnedClient = clientService.updateClient(clientRecord.clientId(), newClient);

        assertNotNull(returnedClient);
        assertTrue(returnedClient.isPresent());
        assertNotEquals(0, clientService.getClients().size());
        assertEquals(newClient.getName(), returnedClient.get().name());
        assertEquals(newClient.getBirthday(), returnedClient.get().birthday());
    }

    @Test
    public void updateClientTest_ClientDoesNotExist() {
        ClientRequestObject newClient = new ClientRequestObject("Test Client",
                LocalDate.of(1990,5,20));

        Optional<ClientRecord> returnedClient = clientService.updateClient(1, newClient);

        assertNotNull(returnedClient);
        assertFalse(returnedClient.isPresent());
        assertEquals(0, clientService.getClients().size());
    }

    @Test
    public void deleteKnownClient_ok(){
        ClientRequestObject newClient = new ClientRequestObject("Test Client",
                LocalDate.of(1990,5,20));
        ClientRecord createdClientRecord = clientService.addClient(newClient);

        Optional<ClientRecord> deletedClientDTO = clientService.deleteClient(createdClientRecord.clientId());

        assertNotNull(deletedClientDTO);
        assertTrue(deletedClientDTO.isPresent());
        assertEquals(0, clientService.getClients().size());
    }

    @Test
    public void deleteUnknownClient_ClientDoesNotExist(){
        Optional<ClientRecord> deletedClientDTO = clientService.deleteClient(1);

        assertNotNull(deletedClientDTO);
        assertFalse(deletedClientDTO.isPresent());
        assertEquals(0, clientService.getClients().size());
    }

}
