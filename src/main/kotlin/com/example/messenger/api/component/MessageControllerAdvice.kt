package com.example.messenger.api.component

import com.example.messenger.api.constant.ErrorResponse
import com.example.messenger.api.constant.ResponseConstants
import com.example.messenger.api.exception.MessageEmptyException
import com.example.messenger.api.exception.MessageRecipientInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MessageControllerAdvice {

    @ExceptionHandler(MessageEmptyException::class)
    fun messageEmpty(messageEmptyException: MessageEmptyException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(ResponseConstants.MESSAGE_EMPTY.value, messageEmptyException.message)
        return ResponseEntity.unprocessableEntity().body(response)
    }

    @ExceptionHandler(MessageRecipientInvalidException::class)
    fun messageRecipientInvalid(messageRecipientInvalidException: MessageRecipientInvalidException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(ResponseConstants.MESSAGE_RECIPIENT_INVALID.value, messageRecipientInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(response)
    }
}