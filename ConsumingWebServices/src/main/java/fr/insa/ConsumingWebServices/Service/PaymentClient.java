package fr.insa.ConsumingWebServices.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import fr.insa.consumingwebservice.wsdl.GetIdOperationRequest;
import fr.insa.consumingwebservice.wsdl.GetIdOperationResponse;

public class PaymentClient extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(PaymentClient.class);

  public GetIdOperationResponse getIdOperation(int idOperation) {

    GetIdOperationRequest request = new GetIdOperationRequest();
    request.setIdOperation(idOperation);

    log.info("Requesting location for idOperation : " + idOperation);

    GetIdOperationResponse response = (GetIdOperationResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://localhost:8080/ws/Payment", request,
            new SoapActionCallback(
                "http://spring.io/payment/GetIdOperationRequest"));

    return response;
  }

}
