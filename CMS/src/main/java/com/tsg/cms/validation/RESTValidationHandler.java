/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.validation;

import java.util.List;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@ControllerAdvice
public class RESTValidationHandler {

    //I turned this off temporary because it is throwing this error message for 
    //any duplicate field. I will turn it back on when we apply server-side validation to everything
    
//    @ExceptionHandler(DuplicateKeyException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ValidationErrorContainer processDuplicateKeyException(DuplicateKeyException e) {
//
//        ValidationErrorContainer errors = new ValidationErrorContainer();
//
//        errors.addValidationError("errorMessage", "That category already exists!");
//
//        return errors;
//
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorContainer processMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        ValidationErrorContainer errors = new ValidationErrorContainer();

        for (FieldError currentError : fieldErrors) {

            errors.addValidationError(currentError.getField(), currentError.getDefaultMessage());

        }

        return errors;

    }
}
