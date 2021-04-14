package com.example.messenger.api.component

import com.example.messenger.api.helper.`object`.MessageVO
import com.example.messenger.api.model.Message
import org.springframework.stereotype.Component

@Component
class MessageAssembler {
    fun toMessageVO(message: Message): MessageVO {
        return MessageVO(
            message.id,
            message.sender?.id,
            message.recipient?.id,
            message.conversation?.id,
            message.body,
            message.createdAt.toString()
        )
    }
}