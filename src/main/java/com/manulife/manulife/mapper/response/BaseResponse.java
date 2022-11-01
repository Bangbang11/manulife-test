package com.manulife.manulife.mapper.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manulife.manulife._enum.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> {
    private String statusCode;
    private String message;
    private List<BaseErrorMessage> errorMessages;
    private T data;

    public BaseResponse() {
        this.errorMessages = new ArrayList<>();
    }

    public void setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }

    public void setStatusCodeByEnum(StatusCodeEnum responseCodeEnum) {
        this.statusCode = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getDescription();
    }
}
