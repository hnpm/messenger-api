package com.example.messenger.api.controller

import com.example.messenger.api.component.ConversationAssembler
import com.example.messenger.api.helper.`object`.ConversationListVO
import com.example.messenger.api.helper.`object`.ConversationVO
import com.example.messenger.api.service.ConversationService
import com.example.messenger.api.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/conversations")
class ConversationController(
    val conversationService: ConversationService,
    val conversationAssembler: ConversationAssembler,
    val userService: UserService
) {

    @GetMapping
    fun list(request: HttpServletRequest): ResponseEntity<ConversationListVO> {
        val user = userService.retrieveUserData(request.userPrincipal.name)
        val conversations = conversationService.listUserConversations(user.id)
        return ResponseEntity.ok(conversationAssembler.toConversationListVO(conversations, user.id))
    }

    @GetMapping("/{conversation_id}")
    fun show(
        @PathVariable("conversation_id") conversationId: Long,
        request: HttpServletRequest
    ): ResponseEntity<ConversationVO> {
        val user = userService.retrieveUserData(request.userPrincipal.name)
        val conversationThread = conversationService.retrieveThread(conversationId)
        return ResponseEntity.ok(conversationAssembler.toConversationVO(conversationThread, user.id))
    }

}