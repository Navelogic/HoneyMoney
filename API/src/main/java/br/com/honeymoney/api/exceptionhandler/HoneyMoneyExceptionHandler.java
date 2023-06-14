package br.com.honeymoney.api.exceptionhandler;

import br.com.honeymoney.api.model.Erro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class HoneyMoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    // Trata exceções de requisições com mensagem HTTP ilegível
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Obtém a mensagem de erro do utilizador com base no arquivo de propriedades de mensagens
        String userMessage = messageSource.getMessage("message.erro.category.toomanyatributes", null, LocaleContextHolder.getLocale());
        // Obtém a mensagem de erro do desenvolvedor
        String devMessage = ex.getCause().toString();
        // Cria uma lista de erros com a mensagem do utilizador e do desenvolvedor
        List<Erro> erros = Arrays.asList(new Erro(userMessage, devMessage));
        // Retorna a resposta com a lista de erros e o status HTTP correspondente
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    // Trata exceções de argumentos inválidos em métodos
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Cria uma lista de erros com base nos erros de validação do BindingResult
        List<Erro> erros = createErrorList(ex.getBindingResult());
        // Retorna a resposta com a lista de erros e o status HTTP correspondente
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    // Trata exceções de acesso a dados vazio
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        // Obtém a mensagem de erro do utilizador com base no arquivo de propriedades de mensagens
        String userMessage = messageSource.getMessage("message.erro.resource.notfound", null, LocaleContextHolder.getLocale());
        // Obtém a mensagem de erro do desenvolvedor
        String devMessage = ex.toString();
        // Cria uma lista de erros com a mensagem do utilizador e do desenvolvedor
        List<Erro> erros = Arrays.asList(new Erro(userMessage, devMessage));
        // Retorna a resposta com a lista de erros e o status HTTP correspondente
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // Trata exceções de violação de integridade de dados
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        // Obtém a mensagem de erro do usuário com base no arquivo de propriedades de mensagens
        String userMessage = messageSource.getMessage("message.erro.resource.operationnotallowed", null, LocaleContextHolder.getLocale());
        // Obtém a mensagem de erro do desenvolvedor
        String devMessage = ex.toString();
        // Cria uma lista de erros com a mensagem do usuário e do desenvolvedor
        List<Erro> erros = Arrays.asList(new Erro(userMessage, devMessage));
        // Retorna a resposta com a lista de erros e o status HTTP correspondente
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // Cria uma lista de erros com base nos erros de validação do BindingResult
    private List<Erro> createErrorList(BindingResult bindingResult){
        List<Erro> erros = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            // Obtém a mensagem de erro do usuário com base no arquivo de propriedades de mensagens
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            // Obtém a mensagem de erro do desenvolvedor
            String devMessage = fieldError.toString();
            // Adiciona um novo objeto Erro à lista de erros com a mensagem do usuário e do desenvolvedor
            erros.add(new Erro(userMessage, devMessage));
        }
        return erros;
    }
}
