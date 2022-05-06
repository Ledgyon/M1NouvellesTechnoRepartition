package fr.insa.BankWebServices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClientNotFoundAdvice {

  @ResponseBody		// pour envoyer le message directement dans le body
  @ExceptionHandler(ClientNotFoundException.class)		// repondre si l'exception ClientNotFoundException est invoqué
  @ResponseStatus(HttpStatus.NOT_FOUND)		// donne un message de type "Erreur 404"
  public String clientNotFoundHandler(ClientNotFoundException ex) {
    return ex.getMessage();
  }
}
