package com.aguerredev.rental.services;

import com.aguerredev.rental.records.ClientRecord;
import com.aguerredev.rental.entities.Client;
import com.aguerredev.rental.requests.ClientRequestObject;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class ClientService {
    private Map<Integer, Client> clients;

    public ClientService(){
        this.clients = Collections.synchronizedNavigableMap(new TreeMap<>());
    }

    public List<ClientRecord> getClients() {
        return clients.values().stream().map(client -> clientToClientDTO(client)).toList();
    }

    public Optional<ClientRecord> getClient(Integer clientId) {
        return Optional.ofNullable(clientToClientDTO(clients.get(clientId)));
    }

    public ClientRecord addClient(ClientRequestObject clientRequestObject) {
        Client newClient = new Client(clientRequestObject.getName(), clientRequestObject.getBirthday());
        clients.put(newClient.getClientId(), newClient);
        return clientToClientDTO(clients.get(newClient.getClientId()));
    }

    public Optional<ClientRecord> updateClient(Integer clientId, ClientRequestObject clientRequestObject) {
        Client client = clients.get(clientId);
        if(client == null) {
            return Optional.empty();

        }

        client.setName(clientRequestObject.getName());
        client.setBirthday(clientRequestObject.getBirthday());
        return Optional.of(clientToClientDTO(clients.put(client.getClientId(), client)));
    }

    public Optional<ClientRecord> deleteClient(Integer clientId) {
        return Optional.ofNullable(clientToClientDTO(clients.remove(clientId)));
    }

    private ClientRecord clientToClientDTO(Client client) {
        if(client != null) {
            return new ClientRecord(client.getClientId(), client.getName(), client.getBirthday());
        }

        return null;
    }
}
