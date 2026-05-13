package com.pess.vet.exception;

import com.pess.vet.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OwnerNotExitsException.class)
    public ResponseEntity<ErrorResponse> ownerNotExistsExceptionHandler(OwnerNotExitsException ex){
        //futuramente eu posso criar uma entidade de erro, que retorne o status e a mensagem em formato json;
        //criei:
        ErrorResponse errorResponseDTO = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "This owner does not exist",
                Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getFieldErrors().getFirst().getDefaultMessage(),
                Instant.now()
        ));
    }

    @ExceptionHandler(OwnerAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> ownerAlreadyRegisteredExceptionHandler(OwnerAlreadyRegisteredException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Owner Already Registered",
                Instant.now()
        ));
    }

    @ExceptionHandler(InvalidOwnerAttributeException.class)
    public ResponseEntity<ErrorResponse> invalidOwnerAttributeExceptionHandler(InvalidOwnerAttributeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                Instant.now()
        ));
    }
}
