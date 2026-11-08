package org.openweather.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StationResponseModel {

    private String id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("external_id")
    private String externalId;
    private String name;
    private Double longitude;
    private Double latitude;
    private Integer altitude;
    private Integer rank;
}
