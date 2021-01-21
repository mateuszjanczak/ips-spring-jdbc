package com.mateuszjanczak.ips.web.rest;

import com.mateuszjanczak.ips.domain.dto.ErrorDto;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(DataAccessResourceFailureException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorDto handleDataAccessResourceFailureException(DataAccessResourceFailureException e) {
        System.err.println(e.getMessage());
        return new ErrorDto("The connection to the database has failed. Try again later.");
    }
}
