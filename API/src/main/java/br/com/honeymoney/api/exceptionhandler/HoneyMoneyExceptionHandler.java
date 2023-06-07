package br.com.honeymoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class HoneyMoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handlerConstraintViolationException(javax.validation.ConstraintViolationException ex) {
        List<String> erros = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.badRequest().body(erros);
    }

}
