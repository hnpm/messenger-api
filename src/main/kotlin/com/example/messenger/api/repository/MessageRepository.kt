package com.example.messenger.api.repository

import com.example.messenger.api.model.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long> {
    fun findByConversationId(conversationId: Long): List<Message>
}