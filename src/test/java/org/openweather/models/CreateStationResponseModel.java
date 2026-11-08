package org.openweather.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateStationResponseModel {

    @JsonProperty("ID")
    private String id;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("external_id")
    private String externalId;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer altitude;
    private Integer rank;
    @JsonProperty("source_type")
    private Integer sourceType;
}
