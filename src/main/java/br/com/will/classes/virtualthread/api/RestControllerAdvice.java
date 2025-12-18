package br.com.will.classes.virtualthread.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
        logger.error("IllegalStateException", e);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .header("content-type", "application/json")
                .body(String.format("""
                        {
                            "error": "Unprocessable Entity",
                            "message": "%s"
                        }""", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Exception", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("content-type", "application/json")
                .body("""
                                    {
                                        "error": "Internal Server Error",
                                        "message": "Internal server error occurred. Try again later."
                        }""");
    }
}