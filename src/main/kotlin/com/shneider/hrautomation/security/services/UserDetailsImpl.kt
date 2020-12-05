package com.shneider.hrautomation.security.services

import com.fasterxml.jackson.annotation.JsonIgnore
import com.shneider.hrautomation.data.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val id: String, private val username: String, val email: String, @field:JsonIgnore
private val password: String,
                      private val authorities: Collection<GrantedAuthority>) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(o: Any?): Boolean {
        if (this === o)
            return true
        if (o == null || javaClass != o.javaClass)
            return false
        val user = o as UserDetailsImpl?
        return id == user!!.id
    }

    companion object {
        private const val serialVersionUID = 1L

        fun build(user: User): UserDetailsImpl {
            val authorities = user.roles
                    .map { role -> SimpleGrantedAuthority(role.name!!.name) }
                    .toList()

            return UserDetailsImpl(
                    user.id!!,
                    user.username!!,
                    user.email!!,
                    user.password!!,
                    authorities
            )
        }
    }
}
