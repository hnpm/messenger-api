package com.example.messenger.api.component

import com.example.messenger.api.helper.`object`.UserListVO
import com.example.messenger.api.helper.`object`.UserVO
import com.example.messenger.api.model.User
import org.springframework.stereotype.Component

@Component
class UserAssembler {

    fun toUserVO(user: User): UserVO {
        return UserVO(user.id, user.username, user.phoneNumber, user.status, user.createdAt.toString())
    }

    fun toUserListVO(users: List<User>): UserListVO {
        val userVOList = users.map { toUserVO(it) }
        return UserListVO(userVOList)
    }
}