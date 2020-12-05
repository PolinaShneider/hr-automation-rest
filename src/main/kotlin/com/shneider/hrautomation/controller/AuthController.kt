package com.shneider.hrautomation.controller

import com.shneider.hrautomation.data.role.ERole
import com.shneider.hrautomation.data.role.Role
import com.shneider.hrautomation.data.role.RoleRepository
import com.shneider.hrautomation.data.user.User
import com.shneider.hrautomation.data.user.UserRepository
import com.shneider.hrautomation.request.LoginRequest
import com.shneider.hrautomation.request.SignupRequest
import com.shneider.hrautomation.response.JwtResponse
import com.shneider.hrautomation.response.MessageResponse
import com.shneider.hrautomation.security.jwt.JwtUtils
import com.shneider.hrautomation.security.services.UserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    internal var authenticationManager: AuthenticationManager? = null

    @Autowired
    internal var userRepository: UserRepository? = null

    @Autowired
    internal var roleRepository: RoleRepository? = null

    @Autowired
    internal var encoder: PasswordEncoder? = null

    @Autowired
    internal var jwtUtils: JwtUtils? = null

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<*> {

        val authentication = authenticationManager!!.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils!!.generateJwtToken(authentication)

        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities
                .map{ it.authority }
                .toList()

        return ResponseEntity.ok(JwtResponse(jwt,
                userDetails.id,
                userDetails.username,
                userDetails.email,
                roles))
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signUpRequest: SignupRequest): ResponseEntity<*> {
        if (userRepository!!.existsByUsername(signUpRequest.username!!)!!) {
            return ResponseEntity
                    .badRequest()
                    .body(MessageResponse("Error: Username is already taken!"))
        }

        if (userRepository!!.existsByEmail(signUpRequest.email!!)!!) {
            return ResponseEntity
                    .badRequest()
                    .body(MessageResponse("Error: Email is already in use!"))
        }

        // Create new user's account
        val user = User(signUpRequest.username!!,
                signUpRequest.email!!,
                encoder!!.encode(signUpRequest.password))

        val strRoles = signUpRequest.roles
        val roles = HashSet<Role>()

        println(strRoles)


        if (strRoles == null) {
            val userRole = roleRepository!!.findByName(ERole.ROLE_USER)
                    .orElseThrow { RuntimeException("Error: Role is not found.") }
            roles.add(userRole)
        } else {
            strRoles.forEach { role ->
                when (role) {
                    "admin" -> {
                        val adminRole = roleRepository!!.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow { RuntimeException("Error: Role is not found.") }
                        roles.add(adminRole)
                    }
                    "mod" -> {
                        val modRole = roleRepository!!.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow { RuntimeException("Error: Role is not found.") }
                        roles.add(modRole)
                    }
                    else -> {
                        val userRole = roleRepository!!.findByName(ERole.ROLE_USER)
                                .orElseThrow { RuntimeException("Error: Role is not found.") }
                        roles.add(userRole)
                    }
                }
            }
        }

        user.roles = roles
        userRepository!!.save(user)

        return ResponseEntity.ok(MessageResponse("User registered successfully!"))
    }
}
