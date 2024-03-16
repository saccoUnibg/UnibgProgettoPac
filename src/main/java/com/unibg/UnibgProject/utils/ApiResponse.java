package com.unibg.UnibgProject.utils;

public enum ApiResponse{

    SUCCESS("200","Success"),
    ERROR("999","Error: ");

    private String code;
    private String description;

    private ApiResponse(String code, String description) {

    }


}

