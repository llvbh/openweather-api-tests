package org.openweather.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationRequestModel {

    @JsonProperty("external_id")
    private String externalId;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer altitude;
}
