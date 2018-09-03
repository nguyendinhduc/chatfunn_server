package com.ducnd.chattfunn.controller;

import com.ducnd.chattfunn.common.MessageResponses;
import com.ducnd.chattfunn.common.exception.ExceptionResponse;
import com.ducnd.chattfunn.model.ObjectError;
import org.joda.time.LocalDateTime;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ExceptionController implements MessageResponses {
    //    IllegalStateException
    @ExceptionHandler(ExceptionResponse.class)
    public final ResponseEntity handleUserNotFoundException(HttpServletRequest req, ExceptionResponse ex) {
        ex.getErrorObject().setTime(LocalDateTime.now());
        ex.getErrorObject().setPath(req.getServletPath());

        return new ResponseEntity<>(ex.getErrorObject(), ex.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public final ResponseEntity handleNumberFormatException(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {
        ObjectError objectError = new ObjectError(ObjectError.ERROR_PARAM, FORMAT_VALUE_INVALID);
        objectError.setTime(LocalDateTime.now());
        objectError.setPath(req.getServletPath());
        return new ResponseEntity<>(objectError, HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException manve) {
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            System.out.println(fieldError.getField());
            ObjectError objectError = new ObjectError(ObjectError.ERROR_PARAM, fieldError.getField() + " is required");
            objectError.setTime(LocalDateTime.now());
            objectError.setPath(request.getServletPath());
            return new ResponseEntity<>(objectError, HttpStatus.CONFLICT);
        }
        ObjectError objectError = new ObjectError(ObjectError.ERROR_PARAM, "Error param");
        objectError.setTime(LocalDateTime.now());
        objectError.setPath(request.getServletPath());

        return new ResponseEntity<>(objectError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public final ResponseEntity handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
        ObjectError objectError = new ObjectError(ObjectError.ERROR_PARAM, ex.getMessage());
        objectError.setTime(LocalDateTime.now());
        objectError.setPath(request.getServletPath());

        return new ResponseEntity<>(objectError, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ObjectError> exceptionHandler(HttpServletRequest req, Exception ex) {
        ObjectError error = new ObjectError(ObjectError.ERROR_PARAM, "The request could not be understood by the server due to malformed syntax.");
        error.setTime(LocalDateTime.now());
        error.setPath(req.getServletPath());
        ex.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ObjectError> httpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException ex) {
        ObjectError error = new ObjectError(ObjectError.ERROR_PARAM, ex.getMessage() + " (Input data invalid)");
        error.setTime(LocalDateTime.now());
        error.setPath(req.getServletPath());
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ObjectError> httpDataIntegrityViolationException(HttpServletRequest req, DataIntegrityViolationException ex) {
        ObjectError error = new ObjectError(ObjectError.ERROR_PARAM, ex.getMessage());
        error.setTime(LocalDateTime.now());
        error.setPath(req.getServletPath());
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
