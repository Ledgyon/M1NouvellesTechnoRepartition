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
  CommandLineRunner initDatabase(ClientRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Client("Simon Rudent", 21, 1000)));
      log.info("Preloading " + repository.save(new Client("Norman Di Bello", 21, 999)));
    };
  }
}
