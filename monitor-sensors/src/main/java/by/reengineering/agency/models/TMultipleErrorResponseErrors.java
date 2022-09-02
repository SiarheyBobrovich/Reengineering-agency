package by.reengineering.agency.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TMultipleErrorResponseErrors
 */
public class TMultipleErrorResponseErrors {
    @JsonProperty("message")
    private String message = null;

    @JsonProperty("field")
    private String field = null;

    public TMultipleErrorResponseErrors message(String message) {
        this.message = message;
        return this;
    }

    /**
     * error message
     *
     * @return message
     **/
    @Schema(example = "must not be empty", description = "error message")

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TMultipleErrorResponseErrors field(String field) {
        this.field = field;
        return this;
    }

    /**
     * field name
     *
     * @return field
     **/
    @Schema(example = "model", description = "field name")
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
