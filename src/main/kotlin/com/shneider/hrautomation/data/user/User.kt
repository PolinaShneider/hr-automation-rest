package com.shneider.hrautomation.data.user

import com.shneider.hrautomation.data.role.Role
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import java.util.HashSet

@Document(collection = "Users")
class User(username: String, email: String, password: String) {
    @Id
    var id: String? = null

    @NotBlank
    @Size(max = 20)
    var username: String? = username

    @NotBlank
    @Size(max = 50)
    @Email
    var email: String? = email

    @NotBlank
    @Size(max = 120)
    var password: String? = password

    @DBRef
    var roles: Set<Role> = HashSet()

}
