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
public class ResizeImageException extends RuntimeException {

    public ResizeImageException() {
    }

    public ResizeImageException(String message) {
        super(message);
    }

    public ResizeImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResizeImageException(Throwable cause) {
        super(cause);
    }

    public ResizeImageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
