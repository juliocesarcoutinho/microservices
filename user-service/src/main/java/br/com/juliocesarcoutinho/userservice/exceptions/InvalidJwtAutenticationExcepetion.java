package br.com.juliocesarcoutinho.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAutenticationExcepetion extends AuthenticationException {
  public InvalidJwtAutenticationExcepetion(String message) {
    super(message);
  }
}