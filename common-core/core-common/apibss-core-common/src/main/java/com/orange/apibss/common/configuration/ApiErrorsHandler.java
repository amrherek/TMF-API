package com.orange.apibss.common.configuration;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orange.apibss.common.exceptions.ApiError;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadParametersException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.badrequest.NotFoundException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;

/**
 *
 * Manages errors from controllers
 *
 * @author xbbs3851
 *
 */
@ControllerAdvice
public class ApiErrorsHandler {

    /**
     * Logger
     */
    protected final static Logger logger = LoggerFactory.getLogger(ApiErrorsHandler.class);

    @Autowired
    private ApiExceptionTranslator apiExceptionTranslator;

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError badRequestErrorHandler(final BadRequestException exception) {
        logger.info(exception.getClass().getCanonicalName() + ": " + exception.getMessage());
        logger.debug("BadRequestException", exception);
        return apiExceptionTranslator.buildError(exception);
    }

    @ExceptionHandler({ TechnicalException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError technicalErrorHandler(final TechnicalException exception) {
        logger.error("TechnicalException", exception);
        return apiExceptionTranslator.buildError(exception);
    }

    @ExceptionHandler({ NotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiError notFoundErrorHandler(final NotFoundException exception) {
        logger.info(exception.getClass().getCanonicalName() + ": " + exception.getMessage());
        logger.debug("NotFoundException", exception);
        return apiExceptionTranslator.buildError(exception);
    }

    @ExceptionHandler({ ApiException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError apiErrorHandler(final ApiException exception) {
        logger.error("ApiException", exception);
        return apiExceptionTranslator.buildError(exception);
    }

    // ------- SPRING WEB exception --------

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError process(final MissingServletRequestParameterException ex) {
        final ApiException exception = new MissingParameterException(ex.getParameterName());
        return apiExceptionTranslator.buildError(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError process(final MethodArgumentNotValidException ex) {
        logger.info(ex.getClass().getCanonicalName() + ": " + ex.getMessage());
        logger.debug("MethodArgumentNotValidException", ex);
        // TODO : add unvalid fields to error details
        final ApiException exception = new BadParameterValueException(ex.getMessage());
        return apiExceptionTranslator.buildError(exception);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError process(final MethodArgumentTypeMismatchException ex) {
        logger.info(ex.getClass().getCanonicalName() + ": " + ex.getMessage());
        logger.debug("MethodArgumentTypeMismatchException", ex);
        String format = ex.getRequiredType().getSimpleName();
        if (ex.getRequiredType().isEnum()) {
            format = "one of " + format + " enum values: " + Arrays.toString(ex.getRequiredType().getEnumConstants());
        }
        final ApiException exception = new BadParameterFormatException(ex.getParameter().getParameterName(),
                ex.getValue().toString(), format, ex);
        return apiExceptionTranslator.buildError(exception);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError process(final IllegalStateException ex) {
        logger.info(ex.getClass().getCanonicalName() + ": " + ex.getMessage());
        logger.debug("IllegalStateException", ex);
        final ApiException exception = new BadParametersException(ex.getMessage());
        return apiExceptionTranslator.buildError(exception);
    }

    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError process(final UnsatisfiedServletRequestParameterException ex) {
        logger.info(ex.getClass().getCanonicalName() + ": " + ex.getMessage());
        logger.debug("UnsatisfiedServletRequestParameterException", ex);

        final List<String[]> params = ex.getParamConditionGroups();
        final StringBuilder message = new StringBuilder("One or many parameters required from combination : ");
        for (final String[] strings : params) {
            message.append(Arrays.toString(strings));
        }

        final ApiException exception = new BadParametersException(message.toString());
        return apiExceptionTranslator.buildError(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError process(final ConstraintViolationException exception) {
        logger.info(exception.getClass().getCanonicalName() + ": " + exception.getMessage());
        logger.debug("ConstraintViolationException", exception);
        final StringBuilder message = new StringBuilder("Failed to convert value : ");
        for (final ConstraintViolation<?> constrainViolation : exception.getConstraintViolations()) {
            message.append(constrainViolation.getInvalidValue() + " " + constrainViolation.getMessage());
        }
        final ApiException ex = new BadParameterFormatException(message.toString());
        return apiExceptionTranslator.buildError(ex);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError process(final BindException exception) {
        logger.info(exception.getClass().getCanonicalName() + ": " + exception.getMessage());
        logger.debug("BindException", exception);

        final StringBuilder message = new StringBuilder("");
        for (final ObjectError error : exception.getAllErrors()) {
            message.append(error.getDefaultMessage() + " " + error.getObjectName());
        }
        final ApiException ex = new BadRequestException(message.toString());
        return apiExceptionTranslator.buildError(ex);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError process(final HttpMessageConversionException exception) {
        logger.info(exception.getClass().getCanonicalName() + ": " + exception.getMessage());
        logger.debug("HttpMessageConversionException", exception);
        String message = exception.getMessage();
        if (exception.getCause() instanceof JsonMappingException) {
            message = "Malformed json: " + ((JsonMappingException) exception.getCause()).getOriginalMessage();
        }
        if (exception.getCause() instanceof JsonParseException) {
            message = "Malformed json: " + ((JsonParseException) exception.getCause()).getOriginalMessage();
        }
        final ApiException ex = new BadParameterFormatException(message.toString());
        return apiExceptionTranslator.buildError(ex);
    }
    @ExceptionHandler({ UndeclaredThrowableException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError process(final UndeclaredThrowableException exception) {
        // can be thrown by @TransactionalBscs if BscsTechnicalException is not
        // declared in method signature
        logger.error("UndeclaredThrowableException", exception);
        final Throwable t = exception.getUndeclaredThrowable();
        ApiException ex = null;
        if (t instanceof ApiException) {
            ex = (ApiException) t;
        } else {
            ex = new TechnicalException(exception.getMessage());
        }
        return apiExceptionTranslator.buildError(ex);
    }

    /*
     * Spring security exception, do not handle it
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApiError process(final AccessDeniedException exception) {
        logger.info(exception.getClass().getCanonicalName() + ": " + exception.getMessage());
        logger.debug("AccessDeniedException", exception);
        throw exception;
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError defaultErrorHandler(final Exception exception) {
        logger.error("Other Exception", exception);
        final ApiException ex = new TechnicalException(exception.getMessage());
        return apiExceptionTranslator.buildError(ex);
    }

}
