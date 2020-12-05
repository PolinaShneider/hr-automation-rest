package com.shneider.hrautomation.data.user

import org.springframework.data.mongodb.repository.MongoRepository

import java.util.Optional

interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): Optional<User>

    fun existsByUsername(username: String): Boolean?

    fun existsByEmail(email: String): Boolean?
}
