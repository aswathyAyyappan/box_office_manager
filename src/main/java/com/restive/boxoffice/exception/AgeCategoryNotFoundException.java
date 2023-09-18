package com.restive.boxoffice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AgeCategoryNotFoundException extends Throwable {
    private String errorMessage;
}
