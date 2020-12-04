package com.vladan.achievementapi.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String code;
    private List<String> errors;

    public ApiError(HttpStatus status, Exception ex, List<String> errors) {
        this.status = status;
        this.code = ex.getClass().getSimpleName();
        this.errors = errors;
    }

    public ApiError(HttpStatus status, Exception ex, String error) {
        this.status = status;
        this.code = ex.getClass().getSimpleName();
        this.errors = Collections.singletonList(error);
    }
}
