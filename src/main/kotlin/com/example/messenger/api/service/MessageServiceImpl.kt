package com.example.messenger.api.service

import com.example.messenger.api.exception.MessageEmptyException
import com.example.messenger.api.exception.MessageRecipientInvalidException
import com.example.messenger.api.model.Conversation
import com.example.messenger.api.model.Message
import com.example.messenger.api.model.User
import com.example.messenger.api.repository.MessageRepository
import com.example.messenger.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    val messageRepository: MessageRepository,
    val userRepository: UserRepository,
    val conversationService: ConversationService
): MessageService {

    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        if (messageText.isEmpty()) throw MessageEmptyException()

        val recipient = userRepository.findById(recipientId).orElseThrow {
            MessageRecipientInvalidException("The recipient id '$recipientId' is invalid.")
        }

        val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {
            conversationService.getConversation(sender, recipient)!!
        } else {
            conversationService.createConversation(sender, recipient)
        }
        val message = Message(sender, recipient, messageText, conversation)
        return messageRepository.save(message)
    }
}