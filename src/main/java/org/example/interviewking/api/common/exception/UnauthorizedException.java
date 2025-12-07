package org.example.interviewking.api.common.exception;

import org.example.interviewking.api.common.model.ResponseCode;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(String message) {
        super(ResponseCode.UNAUTHORIZED_ERROR, message);
    }
}
