package com.aguerredev.rental.controllers;

import com.aguerredev.rental.records.ClientRecord;
import com.aguerredev.rental.requests.ClientRequestObject;
import com.aguerredev.rental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;

    public ClientController(@Autowired ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientRecord> getClientList() {
        return clientService.getClients();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Optional<ClientRecord>> getClient(@PathVariable Integer clientId) {
        Optional<ClientRecord> clientDTO = clientService.getClient(clientId);
        return new ResponseEntity<>(clientDTO, clientDTO.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ClientRecord> addClient(@RequestBody ClientRequestObject newClient){
        return new ResponseEntity<>(clientService.addClient(newClient), HttpStatus.CREATED);
    }

   @PutMapping("/{clientId}")
    public ResponseEntity<Optional<ClientRecord>> updateClient(@PathVariable Integer clientId,
                                                               @RequestBody ClientRequestObject newClient){
       Optional<ClientRecord> clientDTO = clientService.updateClient(clientId, newClient);
       return new ResponseEntity<>(clientDTO, clientDTO.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Optional<ClientRecord>> deleteClient(@PathVariable Integer clientId) {
        Optional<ClientRecord> clientDTO = clientService.deleteClient(clientId);
        return new ResponseEntity<>(clientDTO, clientDTO.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
