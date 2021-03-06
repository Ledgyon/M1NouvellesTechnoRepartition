package fr.insa.BankWebServices.models;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import fr.insa.BankWebServices.controller.ClientController;

@Component
public class ClientModelAssembler implements RepresentationModelAssembler<Client, EntityModel<Client>> {

  @Override
  public EntityModel<Client> toModel(Client client) {

    return EntityModel.of(client, //
        linkTo(methodOn(ClientController.class).one(client.getId())).withSelfRel(),
        linkTo(methodOn(ClientController.class).all()).withRel("employees"));
  }
}
