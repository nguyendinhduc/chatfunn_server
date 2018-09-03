package com.ducnd.chattfunn.common.exception;

import com.ducnd.chattfunn.model.ObjectError;
import org.springframework.http.HttpStatus;

public class ExceptionResponse  extends Exception{
    private HttpStatus status;
    private ObjectError errorObject;

    public ExceptionResponse(ObjectError errorObject, HttpStatus status) {
        super(errorObject.getMessage());
        this.errorObject = errorObject;
        this.status = status;
    }
    public ExceptionResponse(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ObjectError getErrorObject() {
        return errorObject;
    }
}
