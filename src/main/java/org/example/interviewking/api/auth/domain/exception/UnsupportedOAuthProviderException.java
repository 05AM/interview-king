package org.example.interviewking.api.auth.domain.exception;

import org.example.interviewking.api.common.exception.BusinessException;
import org.example.interviewking.api.common.model.ResponseCode;

public class UnsupportedOAuthProviderException extends BusinessException {

    public UnsupportedOAuthProviderException() {
        super(ResponseCode.BAD_REQUEST_ERROR, "지원하지 않는 OAuth 공급자 입니다.");
    }
}
