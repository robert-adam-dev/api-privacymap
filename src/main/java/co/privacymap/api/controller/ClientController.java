package co.privacymap.api.controller;

import co.privacymap.api.model.Client;
import co.privacymap.api.model.dto.ClientInput;
import co.privacymap.api.model.dto.ClientInputUpdate;
import co.privacymap.api.model.dto.ClientOutput;
import co.privacymap.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RestController // Deixa implícito o @ResponseBody
@RequestMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<Page<ClientOutput>> listAllClients(@RequestParam(required = false) String cpf,
                                                             @RequestParam int page,
                                                             @RequestParam int size){


        Pageable pageable = PageRequest.of(page, size);

        if (cpf == null){
            Page<Client> clientsList = clientRepository.findAll(pageable);
            return ResponseEntity.ok(ClientOutput.converter(clientsList));
        }else{
            Page<Client> clientList = clientRepository.findByCpf(cpf, pageable);
            return ResponseEntity.ok(ClientOutput.converter(clientList));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientOutput> findClientById(@PathVariable UUID id){
        Optional <Client> foundClient = clientRepository.findById(id);
        if (foundClient.isPresent()){
            return ResponseEntity.ok(new ClientOutput(foundClient.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClientOutput> createNewClient(@RequestBody @Valid ClientInput client, UriComponentsBuilder uriComponentsBuilder){

        Client newClient = client.converter();
        clientRepository.save(newClient);
        URI uri = uriComponentsBuilder.path("/clients/{id}").buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClientOutput(newClient));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientOutput> updateClient(@PathVariable UUID id, @RequestBody @Valid ClientInputUpdate form){
        Optional <Client> foundClient = clientRepository.findById(id);
        if (foundClient.isPresent()){
            Client client = form.update(id, clientRepository);
            return ResponseEntity.ok(new ClientOutput(client));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeClient(@PathVariable UUID id){
        Optional <Client> foundClient = clientRepository.findById(id);
        if (foundClient.isPresent()){
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
