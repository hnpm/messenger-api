package com.example.messenger.api.service

import com.example.messenger.api.exception.ConversationIdInvalidException
import com.example.messenger.api.model.Conversation
import com.example.messenger.api.model.User
import com.example.messenger.api.repository.ConversationRepository
import org.springframework.stereotype.Service

@Service
class ConversationServiceImpl(val repository: ConversationRepository) : ConversationService {

    override fun createConversation(userA: User, userB: User): Conversation {
        val conversation = Conversation(userA, userB)
        return repository.save(conversation)
    }

    override fun conversationExists(userA: User, userB: User): Boolean =
        (repository.findBySenderIdAndRecipientId(userA.id, userB.id) != null) ||
                (repository.findBySenderIdAndRecipientId(userB.id, userA.id) != null)

    override fun getConversation(userA: User, userB: User): Conversation? =
        repository.findBySenderIdAndRecipientId(userA.id, userB.id) ?: repository.findBySenderIdAndRecipientId(userB.id, userA.id)

    override fun retrieveThread(conversationId: Long): Conversation {
        val conversation = repository.findById(conversationId)
        return conversation.orElseThrow {
            ConversationIdInvalidException("Invalid conversation id '$conversationId'")
        }
    }

    override fun listUserConversations(userId: Long): List<Conversation> {
        val conversationList = ArrayList<Conversation>()
        conversationList.addAll(repository.findBySenderId(userId))
        conversationList.addAll(repository.findByRecipientId(userId))
        return conversationList
    }

    override fun nameSecondParty(conversation: Conversation, userId: Long): String {
        return if (conversation.sender?.id == userId) {
            conversation.recipient?.username as String
        } else {
            conversation.sender?.username as String
        }
    }
}