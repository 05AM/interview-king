package org.example.interviewking.api.common.exception;

import org.example.interviewking.api.common.model.ResponseCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(ResponseCode.NOT_FOUND_ERROR, message);
    }
}
