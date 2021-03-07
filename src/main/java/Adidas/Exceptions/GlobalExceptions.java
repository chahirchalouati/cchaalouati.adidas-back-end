/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Exceptions;

import Adidas.Utilities.ApiResponse;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Chahir Chalouati
 */
@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleAllExceptionMethod(MethodArgumentNotValidException ex, WebRequest requset) {
        return ResponseEntity.badRequest().body(ex.getBindingResult().getAllErrors());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND, new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<?> userExistException(UserExistException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), HttpStatus.CONFLICT, new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)

    public ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
