package com.shneider.hrautomation.data.role

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Roles")
class Role(name: ERole) {
    @Id
    var id: String? = null

    var name: ERole? = name

}
