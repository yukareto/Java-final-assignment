package com.yureto.supergt.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 例外をハンドリングするクラス。
 * アプリケーション内で発生する例外を捕捉し、適切なレスポンスを返します。
 */
@ControllerAdvice
public class SuperGtExceptionHandler {

    /**
     * SuperGtNotFoundExceptionをハンドリングします。
     * 指定されたリソースが見つからなかった場合に404エラーを返します。
     *
     * @param e       SuperGtNotFoundException例外
     * @param request 現在のHTTPリクエスト情報
     * @return HTTP 404エラーのレスポンス
     */
    @ExceptionHandler(value = SuperGtNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSuperGtNotFoundException(
            SuperGtNotFoundException e, HttpServletRequest request) {
        // レスポンスのボディを作成
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * バリデーションエラーをハンドリングします。
     * リクエストパラメータやボディの検証に失敗した場合に400エラーを返します。
     *
     * @param e       MethodArgumentNotValidException例外
     * @param request 現在のHTTPリクエスト情報
     * @return HTTP 400エラーのレスポンス
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();

        // レスポンスのボディを作成
        Map<String, String> body = new LinkedHashMap<>();
        body.put("timestamp", ZonedDateTime.now().toString());
        body.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", "Validation error");
        body.put("path", request.getRequestURI());

        // フィールドごとのエラーメッセージを追加
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            body.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * SuperGtAlreadyExistsExceptionをハンドリングします。
     * 指定されたリソースが既に存在する場合に409エラーを返します。
     *
     * @param e       SuperGtAlreadyExistsException例外
     * @param request 現在のHTTPリクエスト情報
     * @return HTTP 409エラーのレスポンス
     */
    @ExceptionHandler(value = SuperGtAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleSuperGtAlreadyExistsException(
            SuperGtAlreadyExistsException e, HttpServletRequest request) {
        // レスポンスのボディを作成
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.CONFLICT.value()),
                "error", HttpStatus.CONFLICT.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
