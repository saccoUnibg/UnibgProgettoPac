package com.unibg.UnibgProject.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;


@JsonSerialize
@Getter
@Setter
public class ApiResponse {

    private String code;
    private String description;

    private Object object;

    public ApiResponse(){
    }

    public ApiResponse(ApiResponseCodes apiResponseCodes){
        this.code = apiResponseCodes.getCode();
        this.description = apiResponseCodes.getDescription();
    }
    public void setObject(Object object, ApiResponseCodes apiResponseCodes) {
        this.code = apiResponseCodes.getCode();
        this.description = apiResponseCodes.getDescription();
        setObject(object);
    }
}
