package org.example.interviewking.api.auth.dto;

import org.example.interviewking.api.auth.model.OAuthProvider;

public interface OAuthUserInfo {

    OAuthProvider getProvider();

    String id();

    String email();

    String name();
}
