package by.reengineering.agency.models;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
* InlineResponse400
*/@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TMultipleErrorResponse.class, name = "TMultipleErrorResponse")
})
public interface InlineResponse400 {

}
