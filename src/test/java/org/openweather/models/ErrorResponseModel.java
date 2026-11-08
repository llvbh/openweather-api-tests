package org.openweather.models;

import lombok.Data;

@Data
public class ErrorResponseModel {

    private Integer code;
    private String message;
}
