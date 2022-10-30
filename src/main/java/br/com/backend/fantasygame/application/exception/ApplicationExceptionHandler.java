package br.com.backend.fantasygame.application.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.backend.fantasygame.domain.exception.FormErrorDto;
import br.com.backend.fantasygame.domain.exception.FormacaoNaoEncontradaException;
import br.com.backend.fantasygame.domain.exception.JogadorNaoEncontradoException;
import br.com.backend.fantasygame.domain.exception.PaisNaoEncontradoException;
import br.com.backend.fantasygame.domain.exception.PosicaoNaoEncontradaException;
import br.com.backend.fantasygame.domain.exception.StandardError;
import br.com.backend.fantasygame.domain.exception.TimeNaoEncontradoException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    
    @ExceptionHandler(value = {
        PaisNaoEncontradoException.class,
        PosicaoNaoEncontradaException.class,
        FormacaoNaoEncontradaException.class,
        TimeNaoEncontradoException.class,
        JogadorNaoEncontradoException.class
    }
    )
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        StandardError error = new StandardError(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        StandardError error = new StandardError(ex.getMessage());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        StandardError error = new StandardError(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FormErrorDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FormErrorDto> errors = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            FormErrorDto error = new FormErrorDto(e.getField(), message);
            errors.add(error);
        });
        
        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
