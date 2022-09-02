package by.reengineering.agency.controllers.handler;

import by.reengineering.agency.dao.repository.exceptions.NotFoundException;
import by.reengineering.agency.models.TMultipleErrorResponse;
import by.reengineering.agency.models.TMultipleErrorResponseErrors;
import by.reengineering.agency.models.TSingleErrorResponse;
import by.reengineering.agency.services.exception.InvalidUserNameOrPasswordException;
import by.reengineering.agency.services.exception.ValidationException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
    private static final Log log = LogFactory.getLog(GlobalHandler.class);
    private static final String ERROR = "error";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TSingleErrorResponse handle(RuntimeException exception) {
        log.error(exception.getMessage());
        return new TSingleErrorResponse().logref(ERROR)
                .message("Internal Server Error. The server was unable to process the request correctly");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TSingleErrorResponse handle(HttpMessageNotReadableException exception) {
        return new TSingleErrorResponse().logref(ERROR)
                .message("The bad request. Change the request and send it again");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TSingleErrorResponse handle(InvalidUserNameOrPasswordException exception) {
        return new TSingleErrorResponse().logref(ERROR)
                .message(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TSingleErrorResponse handle(NotFoundException exception) {
        return new TSingleErrorResponse().logref(ERROR)
                .message(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TMultipleErrorResponse handle(ValidationException exception) {
        final TMultipleErrorResponse errorResponse = new TMultipleErrorResponse();
        errorResponse.logref("structured_error");

        exception.getErrors().forEach(
                x -> errorResponse.addErrorsItem(new TMultipleErrorResponseErrors()
                        .field(x.getKey())
                        .message(x.getValue())
                )
        );

        return errorResponse;
    }
}
