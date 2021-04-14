package com.example.messenger.api.controller

import com.example.messenger.api.component.UserAssembler
import com.example.messenger.api.helper.`object`.UserListVO
import com.example.messenger.api.helper.`object`.UserVO
import com.example.messenger.api.model.User
import com.example.messenger.api.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService, val userAssembler: UserAssembler) {

    @PostMapping("/registrations")
    fun register(@Validated @RequestBody userDetails: User): ResponseEntity<UserVO> {
        val user = userService.attemptRegistration(userDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping("/{user_id}")
    fun get(@PathVariable("user_id") userId: Long): ResponseEntity<UserVO> {
        val user = userService.retrieveUserData(userId)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping("/details")
    fun details(request: HttpServletRequest): ResponseEntity<UserVO> {
        val user = userService.retrieveUserData(request.userPrincipal.name)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping
    fun index(request: HttpServletRequest): ResponseEntity<UserListVO> {
        val user = userService.retrieveUserData(request.userPrincipal.name)
        val users = userService.listUsers(user)
        return ResponseEntity.ok(userAssembler.toUserListVO(users))
    }

    @PutMapping
    fun update(@RequestBody updateDetails: User, request: HttpServletRequest): ResponseEntity<UserVO> {
        val currentUser = userService.retrieveUserData(request.userPrincipal.name)
        userService.updateUserStatus(currentUser, updateDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(currentUser))
    }
}