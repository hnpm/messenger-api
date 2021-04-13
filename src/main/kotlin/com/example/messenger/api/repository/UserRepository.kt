package com.example.messenger.api.repository

import com.example.messenger.api.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}