package com.manulife.manulife._enum;

import lombok.Getter;

@Getter
public enum StatusCodeEnum {

    OK("200", "Success"),

    INTERNAL_ERROR("500", "We're sorry but something went wrong"),
    ERROR("503", "We're sorry but something went wrong"),

    BAD_REQUEST("400", "Please check input parameter"),
    UNAUTHORIZED("401", "Unauthorized access"),
    FORBIDDEN("403", "You don't have permission to access this resource"),
    NOT_FOUND("404", "The record was not found"),

    CREATED("200", "The record succesfully created"),
    UPDATED("200", "The record succesfully updated"),
    DELETED("200", "The record succesfully deleted"),
    ;

    private String code;

    private String description;

    private StatusCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static StatusCodeEnum fetchEnum(String code) {
        StatusCodeEnum target = null;
        if (code != null && !code.isEmpty()) {
            for (StatusCodeEnum statusCode : StatusCodeEnum.values()) {
                if (statusCode.getCode().equals(code)) {
                    target = statusCode;
                    break;
                }
            }
        }
        return target;
    }
}
