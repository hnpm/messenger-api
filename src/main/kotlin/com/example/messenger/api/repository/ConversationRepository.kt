package com.example.messenger.api.repository

import com.example.messenger.api.model.Conversation
import org.springframework.data.repository.CrudRepository

interface ConversationRepository : CrudRepository<Conversation, Long> {
    fun findBySenderId(id: Long): List<Conversation>
    fun findByRecipientId(id: Long): List<Conversation>
    fun findBySenderIdAndRecipientId(senderId: Long, recipientId: Long): Conversation?
}