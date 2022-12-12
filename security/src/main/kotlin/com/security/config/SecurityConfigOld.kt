//package com.security.config
//
//import com.security.config.jwt.JwtAuthenticationFilter
//import com.security.config.jwt.user.UserService
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.config.BeanIds
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.builders.WebSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//class SecurityConfigOld {
//    private val logger = LoggerFactory.getLogger(SecurityConfigOld::class.java)
//
//    @Value("\${spring.security.debug}")
//    var securityDebug = false
//
//    @Autowired
//    var userService: UserService? = null
//
//    @Bean
//    fun jwtAuthenticationFilter(): JwtAuthenticationFilter? {
//        return JwtAuthenticationFilter()
//    }
//
//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Throws(java.lang.Exception::class)
//    fun authenticationManagerBean(): AuthenticationManager? {
//        // Get AuthenticationManager Bean
//        return authenticationManagerBean()
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder? {
//        return BCryptPasswordEncoder()
//    }
//
//    @Throws(java.lang.Exception::class)
//    protected fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService<UserDetailsService?>(userService) // Cung cáp userservice cho spring security
//            .passwordEncoder(passwordEncoder()) // cung cấp password encoder
//    }
//
//    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http.cors()
//            .and()
//            .csrf().disable()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .httpBasic()
//            .and()
//            .authorizeRequests()
//            .antMatchers("/api/login").permitAll()
//            .anyRequest().authenticated()
////            .antMatchers("/api/*")
////                .hasRole("admin")
////            .anyRequest()
////            .permitAll()
//
//        // Thêm một lớp Filter kiểm tra jwt
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
//        return http.build()
//    }
//
//    @Bean
//    fun webSecurityCustomizer(): WebSecurityCustomizer {
//        return WebSecurityCustomizer { web: WebSecurity ->
//            web.debug(securityDebug)
//                .ignoring()
//                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico")
//        }
//    }
//}
