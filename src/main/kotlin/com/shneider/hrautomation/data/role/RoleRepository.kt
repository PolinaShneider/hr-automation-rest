package com.shneider.hrautomation.data.role

import org.springframework.data.mongodb.repository.MongoRepository

import java.util.Optional

interface RoleRepository : MongoRepository<Role, String> {
    fun findByName(name: ERole): Optional<Role>
}
