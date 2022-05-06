package fr.insa.BankWebServices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.BankWebServices.exception.ClientNotFoundException;
import fr.insa.BankWebServices.models.Client;
import fr.insa.BankWebServices.models.ClientModelAssembler;
import fr.insa.BankWebServices.repository.ClientRepository;

@RestController
public class ClientController {

  private final ClientRepository repository;
  
  private final ClientModelAssembler assembler;

  ClientController(ClientRepository repository, ClientModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  public CollectionModel<EntityModel<Client>> all() {

	  List<EntityModel<Client>> clients = repository.findAll().stream() //
		      .map(assembler::toModel) //
		      .collect(Collectors.toList());

	  return CollectionModel.of(clients, linkTo(methodOn(ClientController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/client")
  ResponseEntity<?> newClient(@RequestBody Client newClient) {

	  EntityModel<Client> entityModel = assembler.toModel(repository.save(newClient));

	  return ResponseEntity //
	      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	      .body(entityModel);
	}

  // Single item
  
  @GetMapping("/client/{id}")
  public EntityModel<Client> one(@PathVariable Long id) {

    Client client = repository.findById(id) //
        .orElseThrow(() -> new ClientNotFoundException(id));

    return assembler.toModel(client);
  }

  @PutMapping("/client/{id}")
  public ResponseEntity<?> replaceClient(@RequestBody Client newClient, @PathVariable Long id) {
    
	  Client updatedClient =  repository.findById(id)
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
	  
	  EntityModel<Client> entityModel = assembler.toModel(updatedClient);

	  return ResponseEntity //
	      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	      .body(entityModel);
  }

  @DeleteMapping("/client/{id}")
  public ResponseEntity<?> deleteClient(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
