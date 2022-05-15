package fr.insa.BankWebServices.endpoint;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.insa.BankWebServices.exception.ClientNotFoundException;
import fr.insa.BankWebServices.models.Client;
import fr.insa.BankWebServices.repository.ClientRepository;
import fr.insa.BankWebServices.repository.PaymentRepository;
import io.spring.payment.GetIdOperationRequest;
import io.spring.payment.GetIdOperationResponse;
import io.spring.payment.Type;

@Endpoint
public class PaymentEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/payment";

	private PaymentRepository paymentRepository;
	
	private ClientRepository repository;

	@Autowired
	public PaymentEndpoint(PaymentRepository paymentRepository, ClientRepository repository) {
		this.paymentRepository = paymentRepository;
		this.repository = repository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdOperationRequest")
	@ResponsePayload
	public GetIdOperationResponse getIdOperation(@RequestPayload GetIdOperationRequest request) {
		GetIdOperationResponse response = new GetIdOperationResponse();
		response.setPayment(paymentRepository.findPayment(request.getIdOperation()));

		double solde;	// nouveau solde
		String op; // operation effectué
		
		Long id = (long) response.getPayment().getIdClient(); // init id client
		
		Client updatedClient =  repository.findById(id) // recherche du client
		        .orElseThrow(() -> new ClientNotFoundException(id));
		ArrayList<String> operations = updatedClient.getOperation();
		
		if(response.getPayment().getType() == Type.DEBIT ){	// alors, on retire de l'argent
			solde = updatedClient.getSolde() - response.getPayment().getPrix();
			op = "-"+response.getPayment().getPrix();
			operations.add(op);
		}
		else { // on ajoute (car forcement Type.REMBROUSEMENT
			solde = updatedClient.getSolde() + response.getPayment().getPrix();
			op = "+"+response.getPayment().getPrix();
			operations.add(op);
		}
		
		//enregistrement de la modif
		
		updatedClient.setSolde(solde);
		updatedClient.setOperation(operations);
		repository.save(updatedClient);
			      
		return response;
	}
}
