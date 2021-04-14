package com.example.messenger.api.component

import com.example.messenger.api.helper.`object`.ConversationListVO
import com.example.messenger.api.helper.`object`.ConversationVO
import com.example.messenger.api.model.Conversation
import com.example.messenger.api.service.ConversationService
import org.springframework.stereotype.Component

@Component
class ConversationAssembler(val conversationService: ConversationService, val messageAssembler: MessageAssembler) {

    fun toConversationVO(conversation: Conversation, userId: Long): ConversationVO {
        val conversationMessages = conversation.messages.map { messageAssembler.toMessageVO(it) }.toList()
        return ConversationVO(
            conversation.id,
            conversationService.nameSecondParty(conversation, userId),
            conversationMessages
        )
    }

    fun toConversationListVO(conversations: List<Conversation>, userId: Long): ConversationListVO {
        val conversationVOList = conversations.map { toConversationVO(it, userId) }
        return ConversationListVO(conversationVOList)
    }
}