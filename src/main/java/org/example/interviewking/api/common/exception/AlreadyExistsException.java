package org.example.interviewking.api.common.exception;

import org.example.interviewking.api.common.model.ResponseCode;

public class AlreadyExistsException extends BusinessException {

    public AlreadyExistsException(String message) {
        super(ResponseCode.CONFLICT, message);
    }
}
