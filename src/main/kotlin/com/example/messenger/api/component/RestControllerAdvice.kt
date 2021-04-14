package com.example.messenger.api.component

import com.example.messenger.api.constant.ErrorResponse
import com.example.messenger.api.constant.ResponseConstants
import com.example.messenger.api.exception.UserDeactivatedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(UserDeactivatedException::class)
    fun userDeactivated(userDeactivatedException: UserDeactivatedException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.value, userDeactivatedException.message)
        return ResponseEntity(response, HttpStatus.UNAUTHORIZED)
    }
}