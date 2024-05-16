package com.unibg.UnibgProject.utils;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class ApiResponse {

    private String code;
    private String description;

    private Object object;
    private String message;
    public ApiResponse(){
    }

    public ApiResponse(ApiResponseCodes apiResponseCodes){
        this.code = apiResponseCodes.getCode();
        this.description = apiResponseCodes.getDescription();
    }
    public void setObject(Object object, ApiResponseCodes apiResponseCodes) {
        this.code = apiResponseCodes.getCode();
        this.description = apiResponseCodes.getDescription();
        this.object = object;
    }

}
