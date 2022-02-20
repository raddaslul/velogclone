package com.sparta.velogclone.handler;

import com.sparta.velogclone.handler.ex.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(EmailNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("U001", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordContainsEmailException.class)
    public ResponseEntity<ErrorResponse> handlePasswordContainsEmailException(PasswordContainsEmailException e) {
        return new ResponseEntity<>(new ErrorResponse("U002", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e) {
        return new ResponseEntity<>(new ErrorResponse("U003", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotCollectException.class)
    public ResponseEntity<ErrorResponse> handlePasswordNotCollectException(PasswordNotCollectException e) {
        return new ResponseEntity<>(new ErrorResponse("U004", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginUserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoginUserNotFoundException(LoginUserNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("U005", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("P001", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalPostUpdateUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalPostUpdateUserException(IllegalPostUpdateUserException e) {
        return new ResponseEntity<>(new ErrorResponse("P002", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalPostDeleteUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalPostDeleteUserException(IllegalPostDeleteUserException e) {
        return new ResponseEntity<>(new ErrorResponse("P003", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleImageNotFoundException(ImageNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("I001", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotFoundException(CommentNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("C001", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalCommentUpdateUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalCommentUpdateUserException(IllegalCommentUpdateUserException e) {
        return new ResponseEntity<>(new ErrorResponse("C002", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalCommentDeleteUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalCommentDeleteUserException(IllegalCommentDeleteUserException e) {
        return new ResponseEntity<>(new ErrorResponse("C003", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
                , HttpStatus.BAD_REQUEST);
    }

}