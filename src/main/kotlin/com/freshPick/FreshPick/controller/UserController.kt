package com.freshPick.FreshPick.controller

import com.freshPick.FreshPick.model.User
import com.freshPick.FreshPick.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    var userRepo: UserRepository
) {


    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val savedUser = userRepo.save(user)
        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }


    @GetMapping("/test")
    fun logTest(authUser: Authentication): Long{
        return 1L
    }

}