package org.example.interviewking.api.common.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.example.interviewking.api.common.model.ErrorResponse;
import org.example.interviewking.api.common.model.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Valid / @Validated 실패
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.warn("Validation failed: {}", e.getMessage());

        List<ErrorResponse.FieldErrorDetail> errors = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> new ErrorResponse.FieldErrorDetail(err.getField(), err.getDefaultMessage()))
            .collect(Collectors.toList());

        return ResponseEntity
            .badRequest()
            .body(ErrorResponse.of(ResponseCode.VALIDATION_ERROR, errors));
    }

    /**
     * Binding 실패 (쿼리 파라미터, Form-data)
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.warn("Bind failed: {}", e.getMessage());

        List<ErrorResponse.FieldErrorDetail> errors = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> new ErrorResponse.FieldErrorDetail(err.getField(), err.getDefaultMessage()))
            .collect(Collectors.toList());

        return ResponseEntity
            .badRequest()
            .body(ErrorResponse.of(ResponseCode.BIND_ERROR, errors));
    }

    /**
     * JSON 파싱 실패
     */
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
        org.springframework.http.converter.HttpMessageNotReadableException e) {

        log.warn("JSON parse error: {}", e.getMessage());

        return ResponseEntity
            .badRequest()
            .body(ErrorResponse.of(ResponseCode.JSON_PARSE_ERROR));
    }

    /**
     * 잘못된 요청
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("IllegalArgument: {}", e.getMessage());

        return ResponseEntity
            .badRequest()
            .body(ErrorResponse.of(ResponseCode.BAD_REQUEST_ERROR));
    }

    /**
     * 비즈니스 예외
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.warn("BusinessException: {}", e.getMessage());

        ResponseCode code = e.getCode();
        return ResponseEntity
            .status(code.getHttpStatus())
            .body(ErrorResponse.of(code));
    }

    /**
     * 서버 내부 예외
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unhandled exception", e);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse.of(ResponseCode.INTERNAL_SERVER_ERROR));
    }
}
