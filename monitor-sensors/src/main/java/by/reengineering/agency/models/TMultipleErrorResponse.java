package by.reengineering.agency.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Error. Contains error descriptions with links to entity fields
 */
@Schema(description = "Error. Contains error descriptions with links to entity fields")
public class TMultipleErrorResponse implements InlineResponse400 {
    @JsonProperty("logref")
    private String logref;

    @JsonProperty("errors")
    private List<TMultipleErrorResponseErrors> errors = new ArrayList<>();

    public TMultipleErrorResponse logref(String logref) {
        this.logref = logref;
        return this;
    }

    /**
     * error type
     *
     * @return logref
     **/
    @Schema(required = true, description = "error type")
    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public TMultipleErrorResponse errors(List<TMultipleErrorResponseErrors> errors) {
        this.errors = errors;
        return this;
    }

    public TMultipleErrorResponse addErrorsItem(TMultipleErrorResponseErrors errorsItem) {
        this.errors.add(errorsItem);
        return this;
    }

    /**
     * field name
     *
     * @return errors
     **/
    @Schema(required = true, description = "field name")
    public List<TMultipleErrorResponseErrors> getErrors() {
        return errors;
    }

    public void setErrors(List<TMultipleErrorResponseErrors> errors) {
        this.errors = errors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TMultipleErrorResponse tMultipleErrorResponse = (TMultipleErrorResponse) o;
        return Objects.equals(this.logref, tMultipleErrorResponse.logref) &&
                Objects.equals(this.errors, tMultipleErrorResponse.errors);
    }
}
