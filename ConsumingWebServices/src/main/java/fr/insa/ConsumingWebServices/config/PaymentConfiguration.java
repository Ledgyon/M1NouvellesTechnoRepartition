package fr.insa.ConsumingWebServices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import fr.insa.ConsumingWebServices.Service.PaymentClient;

@Configuration
public class PaymentConfiguration {

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // this package must match the package in the <generatePackage> specified in
    // pom.xml
    marshaller.setContextPath("fr.insa.consumingwebservice.wsdl");
    return marshaller;
  }

  @Bean
  public PaymentClient paymentClient(Jaxb2Marshaller marshaller) {
    PaymentClient client = new PaymentClient();
    client.setDefaultUri("http://localhost:8080/ws");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }

}
