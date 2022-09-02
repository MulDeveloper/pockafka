package dev.muldev.pockafka.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("time", LocalDateTime.now().toString());
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = "message";
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("MethodArgumentNotValidException " + ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, String> handleParamNotPresent(
            MissingServletRequestParameterException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("time", LocalDateTime.now().toString());
        errors.put("message", ex.getMessage());
        log.error("MissingServletRequestParameterException " + ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public Map<String, String> handleUserAlreadyRegistered(
            UserAlreadyRegisteredException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("time", LocalDateTime.now().toString());
        errors.put("message", ex.getMessage());
        log.error("UserAlreadyRegisteredException " + ex.getMessage());

        return errors;
    }

}