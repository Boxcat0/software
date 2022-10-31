package com.example.demo.account

import org.springframework.security.core.context.SecurityContextHolder

fun getCustomUserDetails(): CustomUserDetails? {
    val principal = SecurityContextHolder.getContext().authentication?.principal
    return if (principal is CustomUserDetails) principal else null
}