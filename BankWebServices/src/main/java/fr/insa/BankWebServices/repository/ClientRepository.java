package fr.insa.BankWebServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.BankWebServices.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
