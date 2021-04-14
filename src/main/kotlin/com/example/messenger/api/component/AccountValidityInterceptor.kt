package com.example.messenger.api.component

import com.example.messenger.api.exception.UserDeactivatedException
import com.example.messenger.api.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AccountValidityInterceptor(val userRepository: UserRepository): HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val principal: Principal? = request.userPrincipal
        if (principal != null) {
            val user = userRepository.findByUsername(principal.name)
            if (user != null && user.accountStatus == "deactivated") {
                throw UserDeactivatedException("The account of this user has been deactivated.")
            }
        }
        return super.preHandle(request, response, handler)
    }
}