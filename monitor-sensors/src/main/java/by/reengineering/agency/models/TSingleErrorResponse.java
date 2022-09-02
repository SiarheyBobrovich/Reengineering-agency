package by.reengineering.agency.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Error. contains a description
 */
public class TSingleErrorResponse {
    @JsonProperty("logref")
    private String logref = null;

    @JsonProperty("message")
    private String message = null;

    public TSingleErrorResponse logref(String logref) {
        this.logref = logref;
        return this;
    }

    /**
     * error type
     *
     * @return logref
     **/
    @Schema(example = "error", required = true, description = "error type")
    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public TSingleErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * error message
     *
     * @return message
     **/
    @Schema(example = "The request contains invalid data. Change the request and send it again", required = true, description = "error message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
