package com.example.messenger.api.service

import com.example.messenger.api.model.Message
import com.example.messenger.api.model.User

interface MessageService {
    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}