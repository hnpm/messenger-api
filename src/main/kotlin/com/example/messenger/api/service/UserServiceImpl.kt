package com.example.messenger.api.service

import com.example.messenger.api.exception.InvalidUserIdException
import com.example.messenger.api.exception.UserStatusEmptyException
import com.example.messenger.api.exception.UsernameUnavailableException
import com.example.messenger.api.model.User
import com.example.messenger.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val repository: UserRepository): UserService {

    override fun attemptRegistration(userDetails: User): User {
        if (usernameExists(userDetails.username)) {
            throw UsernameUnavailableException("The username '${userDetails.username}' is unavailable.")
        }
        val user = User()
        user.username = userDetails.username
        user.phoneNumber = userDetails.phoneNumber
        user.password = userDetails.password
        repository.save(user)
        obscurePassword(user)
        return user
    }

    override fun listUsers(currentUser: User): List<User> = repository.findAll().filter { it != currentUser }.toList()

    override fun retrieveUserData(username: String): User {
        val user = repository.findByUsername(username)
        obscurePassword(user)
        return user!!
    }

    override fun retrieveUserData(id: Long): User {
        val user = repository.findById(id).orElseThrow {
            InvalidUserIdException("A user with an id of '$id' does not exist.")
        }
        obscurePassword(user)
        return user
    }

    override fun usernameExists(username: String): Boolean {
        return repository.findByUsername(username) != null
    }

    override fun updateUserStatus(currentUser: User, updateDetails: User): User {
        if (updateDetails.status.isEmpty()) {
            throw UserStatusEmptyException()
        }
        currentUser.status = updateDetails.status
        return repository.save(currentUser)
    }

    private fun obscurePassword(user: User?) {
        user?.password = "XXX XXX XXX"
    }


}