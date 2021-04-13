package com.example.messenger.api.model

import com.example.messenger.api.listener.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "`user`")
@EntityListeners(UserListener::class)
class User(
    @Column(unique = true)
    @Size(min = 2)
    var username: String = "",

    @Size(min = 11)
    @Pattern(regexp = """^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$""")
    var phoneNumber: String = "",

    @Size(min = 60, max = 60)
    var password: String = "",

    var status: String = "",

    @Pattern(regexp = """\A(activated|deactivated)\z""")
    var accountStatus: String = "activated",

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now()),

    @OneToMany(mappedBy = "sender", targetEntity = Message::class)
    var sentMessages: Collection<Message>? = null,

    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    var receivedMessages: Collection<Message>? = null
)

