package fr.insa.ConsumingWebServices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.insa.ConsumingWebServices.Service.PaymentClient;
import fr.insa.consumingwebservice.wsdl.GetIdOperationResponse;

@SpringBootApplication
public class ConsumingWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumingWebServicesApplication.class, args);
	}
	
	@Bean
	  CommandLineRunner lookup(PaymentClient quoteClient) {
	    return args -> {
	      int id = 1;

	      if (args.length > 0) {
	        id = Integer.parseInt(args[0]);
	      }
	      GetIdOperationResponse response = quoteClient.getIdOperation(id);
	      System.err.println(response.getPayment().getType());
	    };
	  }

}
