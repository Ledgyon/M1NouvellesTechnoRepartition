package fr.insa.BankWebServices.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.BankWebServices.exception.ClientNotFoundException;
import fr.insa.BankWebServices.models.Client;
import fr.insa.BankWebServices.repository.ClientRepository;

@RestController
class ClientController {

  private final ClientRepository repository;

  ClientController(ClientRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/client")
  List<Client> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/client")
  Client newClient(@RequestBody Client newClient) {
    return repository.save(newClient);
  }

  // Single item
  
  @GetMapping("/client/{id}")
  Client one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ClientNotFoundException(id));
  }

  @PutMapping("/client/{id}")
  Client replaceEmployee(@RequestBody Client newClient, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(client -> {
    	  client.setName(newClient.getName());
    	  client.setAge(newClient.getAge());
    	  client.setSolde(newClient.getSolde());
        return repository.save(client);
      })
      .orElseGet(() -> {
        newClient.setId(id);
        return repository.save(newClient);
      });
  }

  @DeleteMapping("/client/{id}")
  void deleteClient(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
