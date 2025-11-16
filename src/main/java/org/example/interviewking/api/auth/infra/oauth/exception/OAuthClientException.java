package org.example.interviewking.api.auth.infra.oauth.exception;

import org.example.interviewking.api.common.exception.BusinessException;
import org.example.interviewking.api.common.model.ResponseCode;

public class OAuthClientException extends BusinessException {

    public OAuthClientException(ResponseCode code, String message) {
        super(code, message);
    }
}
