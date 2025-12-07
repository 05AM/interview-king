package org.example.interviewking.api.common.exception;

import org.example.interviewking.api.common.model.ResponseCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ResponseCode code;

    public BusinessException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResponseCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
