package com.security.config.jwt.service

import com.security.config.jwt.user.UserEntity
import com.security.config.jwt.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: UserEntity = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User Not Found with username: $username")

        val authorities: List<GrantedAuthority> = user.roles!!.stream()
            .map { role -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toList())
        return UserDetailsImpl(
            user.userId!!,
            user.username!!,
            user.password!!,
            authorities
        )
    }
}