package com.example.messenger.api.component

import com.example.messenger.api.constant.ErrorResponse
import com.example.messenger.api.constant.ResponseConstants
import com.example.messenger.api.exception.InvalidUserIdException
import com.example.messenger.api.exception.UserStatusEmptyException
import com.example.messenger.api.exception.UsernameUnavailableException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UserControllerAdvice {

    @ExceptionHandler(UsernameUnavailableException::class)
    fun usernameUnavailable(usernameUnavailableException: UsernameUnavailableException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(ResponseConstants.USERNAME_UNAVAILABLE.value, usernameUnavailableException.message)
        return ResponseEntity.unprocessableEntity().body(response)
    }

    @ExceptionHandler(InvalidUserIdException::class)
    fun invalidId(invalidUserIdException: InvalidUserIdException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(ResponseConstants.INVALID_USER_ID.value, invalidUserIdException.message)
        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(UserStatusEmptyException::class)
    fun statusEmpty(userStatusEmptyException: UserStatusEmptyException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(ResponseConstants.EMPTY_STATUS.value, userStatusEmptyException.message)
        return ResponseEntity.unprocessableEntity().body(response)
    }
}