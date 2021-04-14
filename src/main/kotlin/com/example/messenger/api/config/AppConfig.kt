package com.example.messenger.api.config

import com.example.messenger.api.component.AccountValidityInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig(val accountValidityInterceptor: AccountValidityInterceptor): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(accountValidityInterceptor)
        super.addInterceptors(registry)
    }
}