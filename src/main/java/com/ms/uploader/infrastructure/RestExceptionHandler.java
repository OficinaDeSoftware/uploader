package com.ms.uploader.infrastructure;

import com.ms.uploader.exceptions.CredentialsNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( CredentialsNotFound.class )
    private ResponseEntity<RestErrorMessage> eventNotFoundHandler( CredentialsNotFound exception ) {
        RestErrorMessage response = new RestErrorMessage( HttpStatus.NOT_FOUND, exception.getMessage() );
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( response );
    }

    @ExceptionHandler( RuntimeException.class )
    private ResponseEntity<RestErrorMessage> eventNotFoundHandler( RuntimeException exception ) {
        RestErrorMessage response = new RestErrorMessage( HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage() );
        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( response );
    }

    @ExceptionHandler( IllegalArgumentException.class )
    private ResponseEntity<RestErrorMessage> eventNotFoundHandler( IllegalArgumentException exception ) {
        RestErrorMessage response = new RestErrorMessage( HttpStatus.BAD_REQUEST, exception.getMessage() );
        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( response );
    }

}
