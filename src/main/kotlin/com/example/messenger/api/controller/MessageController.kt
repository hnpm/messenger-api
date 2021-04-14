package com.example.messenger.api.controller

import com.example.messenger.api.component.MessageAssembler
import com.example.messenger.api.helper.`object`.MessageVO
import com.example.messenger.api.service.MessageService
import com.example.messenger.api.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/messages")
class MessageController(
    val messageService: MessageService,
    val messageAssembler: MessageAssembler,
    val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody messageDetails: MessageRequest, request: HttpServletRequest): ResponseEntity<MessageVO> {
        val sender = userService.retrieveUserData(request.userPrincipal.name)
        val message = messageService.sendMessage(sender, messageDetails.recipientId, messageDetails.message)
        return ResponseEntity.ok(messageAssembler.toMessageVO(message))
    }

    data class MessageRequest(val recipientId: Long, val message: String)
}