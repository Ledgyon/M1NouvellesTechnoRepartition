package fr.insa.PaymentGateway.producingwebservices.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.insa.PaymentGateway.producingwebservices.PaymentRepository;
import io.spring.payment.GetIdOperationRequest;
import io.spring.payment.GetIdOperationResponse;

@Endpoint
public class PaymentEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/payment";

	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentEndpoint(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdOperationRequest")
	@ResponsePayload
	public GetIdOperationResponse getIdOperation(@RequestPayload GetIdOperationRequest request) {
		GetIdOperationResponse response = new GetIdOperationResponse();
		response.setPayment(paymentRepository.findPayment(request.getIdOperation()));

		return response;
	}
}
