package com.cyb.tms.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler({ NoHandlerFoundException.class})
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
	    //Locale locale = LocaleContextHolder.getLocale();
	   // String errorMessage = messageSource.getMessage("error.bad.url", null, locale);

	   // String errorURL = req.getRequestURL().toString();

		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
	    return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
    @ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotAcceptable(HttpServletRequest req, HttpMediaTypeNotAcceptableException e) {
    	ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(HttpStatus.NOT_ACCEPTABLE.toString());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupported(HttpServletRequest req, HttpMediaTypeNotSupportedException e) {
    	ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
		error.setMessage(e.getLocalizedMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<ErrorResponse> methodNotAllowed(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
    	ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		error.setMessage(e.getLocalizedMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler({ TypeMismatchException.class })
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(HttpServletRequest req, TypeMismatchException e) {
    	ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getLocalizedMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AccessDeniedException.class })
    protected ResponseEntity<Object> handleUserNameNotFound(AccessDeniedException ufe, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	Throwable mostSpecificCause = ufe.fillInStackTrace();
        ErrorMessage errorMessage;
        if (mostSpecificCause != null) {
            String exceptionName = mostSpecificCause.getClass().getName();
            String message = mostSpecificCause.getMessage();
            errorMessage = new ErrorMessage(exceptionName, message);
        } else {
            errorMessage = new ErrorMessage(ufe.getMessage());
        }
        return new ResponseEntity<Object>(errorMessage, headers, HttpStatus.UNAUTHORIZED);
    	
    	
    }
	
    
    @ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> sqlExceptionHandler(ConstraintViolationException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setMessage(ex.getLocalizedMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
	}
    
    @ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
