package org.example.interviewking.api.auth.infra.oauth.google;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.example.interviewking.api.auth.dto.OAuthTokenInfo;
import org.example.interviewking.api.auth.dto.OAuthUserInfo;
import org.example.interviewking.api.auth.infra.oauth.exception.OAuthClientException;
import org.example.interviewking.api.auth.model.OAuthProvider;
import org.example.interviewking.api.auth.port.out.OAuthClient;
import org.example.interviewking.api.common.model.ResponseCode;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class GoogleOAuthClient implements OAuthClient {

    private static final String ERROR_MESSAGE_FORMAT = "Google 토큰 요청 실패 - 상태코드: %s, 본문: %s";

    private final GoogleOAuthProperties oAuthProperties;
    private final RestClient restClient;

    public GoogleOAuthClient(GoogleOAuthProperties oAuthProperties, RestClient restClient) {
        this.oAuthProperties = oAuthProperties;
        this.restClient = restClient;
    }

    @Override
    public boolean supports(OAuthProvider provider) {
        return OAuthProvider.GOOGLE.equals(provider);
    }

    @Override
    public OAuthTokenInfo requestAccessToken(String code) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", oAuthProperties.getCredentials().clientId());
        body.add("client_secret", oAuthProperties.getCredentials().clientSecret());
        body.add("redirect_uri", oAuthProperties.getCredentials().redirectUri());
        body.add("grant_type", "authorization_code");

        try {
            GoogleTokenInfo tokenInfo = restClient.post()
                .uri(oAuthProperties.getProvider().uri().token())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throwFromResponse(ResponseCode.BAD_REQUEST_ERROR, response);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throwFromResponse(ResponseCode.INTERNAL_SERVER_ERROR, response);
                })
                .body(GoogleTokenInfo.class);

            if (tokenInfo == null) {
                throw new OAuthClientException(ResponseCode.BAD_GATEWAY_ERROR, "Google 액세스 토큰 응답이 비어 있습니다.");
            }
            return tokenInfo;

        } catch (RestClientException e) {
            throw new OAuthClientException(ResponseCode.INTERNAL_SERVER_ERROR, String.valueOf(e.getMessage()));
        }
    }

    @Override
    public OAuthUserInfo requestUserInfo(String accessToken) {
        try {
            GoogleUserInfo userInfo = restClient.get()
                .uri(oAuthProperties.getProvider().uri().userInfo())
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throwFromResponse(ResponseCode.BAD_REQUEST_ERROR, response);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throwFromResponse(ResponseCode.BAD_GATEWAY_ERROR, response);
                })
                .body(GoogleUserInfo.class);

            if (userInfo == null) {
                throw new OAuthClientException(ResponseCode.BAD_GATEWAY_ERROR, "Google 사용자 정보 응답이 비어 있습니다.");
            }
            return userInfo;

        } catch (RestClientException e) {
            throw new OAuthClientException(ResponseCode.INTERNAL_SERVER_ERROR, String.valueOf(e.getMessage())
            );
        }
    }

    private void throwFromResponse(ResponseCode code, ClientHttpResponse response) throws IOException {
        String errorBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
        throw new OAuthClientException(
            code,
            String.format(ERROR_MESSAGE_FORMAT, response.getStatusCode(), errorBody)
        );
    }
}
