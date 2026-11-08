package org.openweather.models;

import lombok.Data;

@Data
public class UnauthorizedResponseModel {

    private Integer cod;
    private String message;
}
