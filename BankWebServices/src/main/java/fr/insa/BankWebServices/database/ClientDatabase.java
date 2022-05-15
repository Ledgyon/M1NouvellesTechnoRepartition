package fr.insa.BankWebServices.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.insa.BankWebServices.models.Client;
import fr.insa.BankWebServices.repository.ClientRepository;

@Configuration
class ClientDatabase {

  private static final Logger log = LoggerFactory.getLogger(ClientDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ClientRepository clientRepository) {

    return args -> {
      clientRepository.save(new Client("Simon Rudent", 21, 1000));
      clientRepository.save(new Client("Norman Di Bello", 21, 999));

      clientRepository.findAll().forEach(client -> log.info("Preloaded " + client));
      
    };
  }
}
