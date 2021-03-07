/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Exceptions;

/**
 *
 * @author Chahir Chalouati
 */
public class FileStorageException extends RuntimeException {

    public FileStorageException() {
    }

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStorageException(Throwable cause) {
        super(cause);
    }

    public FileStorageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
