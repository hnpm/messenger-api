package com.example.messenger.api.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Conversation(
    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    var sender: User? = null,

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    var recipient: User? = null,

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    @DateTimeFormat
    val createdAt: Date = Date.from(Instant.now()),

    @OneToMany(mappedBy = "conversation", targetEntity = Message::class)
    var messages: Collection<Message>? = null
)