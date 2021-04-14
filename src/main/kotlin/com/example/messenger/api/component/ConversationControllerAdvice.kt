package com.example.messenger.api.component

import com.example.messenger.api.constant.ErrorResponse
import com.example.messenger.api.exception.ConversationIdInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvice {

    @ExceptionHandler(ConversationIdInvalidException::class)
    fun conversationIdInvalid(conversationIdInvalidException: ConversationIdInvalidException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse("", conversationIdInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(response)
    }
}