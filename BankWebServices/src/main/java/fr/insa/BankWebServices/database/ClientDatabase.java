package fr.insa.BankWebServices.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.insa.BankWebServices.models.Client;
import fr.insa.BankWebServices.repository.ClientRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ClientRepository employeeRepository) {

    return args -> {
      employeeRepository.save(new Client("Simon Rudent", 21, 1000));
      employeeRepository.save(new Client("Norman Di Bello", 21, 999));

      employeeRepository.findAll().forEach(client -> log.info("Preloaded " + client));
      
    };
  }
}
