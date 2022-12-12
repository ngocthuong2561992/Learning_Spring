package com.security.controller

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.security.repository.RentalNewRepository
import com.security.config.jwt.JwtUtils
import com.security.config.jwt.payload.LoginRequest
import com.security.config.jwt.payload.LoginResponse
import com.security.config.jwt.service.UserDetailsImpl
import com.security.dto.ParamInfo
import com.security.entity.RentalNewEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.util.stream.Collectors

@RestController
@RequestMapping(value = ["/api"])
class ApiController {
//    @Autowired
//    lateinit var exportService: ExportService

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): LoginResponse? {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val response = jwtUtils.generateJwtToken(authentication)
        val userDetails: UserDetailsImpl = authentication.principal as UserDetailsImpl
        val roles: List<String> = userDetails.getAuthorities().stream()
            .map { item -> item.getAuthority() }
            .collect(Collectors.toList())
        return response
    }

    @Autowired
    lateinit var rentalNewRepository : RentalNewRepository

    @GetMapping(value = ["/admin"])
    fun admin(@RequestParam("status") status: String) {
//        if (status == "success") {
//            logger.info("success")
//        } else {
//            println(1 / 0)
//        }
        var entity = RentalNewEntity(
            rentalDate = LocalDateTime.now(),
            inventoryId = 1,
            customerId = 1,
            staffId = 1
        )
//        var entity = rentalNewRepository.findById(16052).get()
//        entity.customerId = 2
        rentalNewRepository.save(entity)
    }

    @GetMapping(value = ["/user"])
    fun user(@RequestParam("status") status: String) {
//        if (status == "success") {
//            logger.info("success")
//        } else {
//            println(1 / 0)
//        }
        var entity = rentalNewRepository.findById(2).get()
        entity.customerId = 2
        rentalNewRepository.save(entity)
    }

    @Autowired
    @Qualifier("customRestTemplate")
    lateinit var restTemplate: RestTemplate

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            var response = ParamInfo(name = "admin", age = 10)
//            println(ObjectMapper().writeValueAsString(response))
        }
    }


    @PostMapping(value = ["/rest"])
    fun rest(@RequestBody params: String): RestResponse {
        var response = RestResponse()
        try {
            var paramInfo = mapper.readValue(params, ParamInfo::class.java)
            if(paramInfo.age!! > 10) {
                throw Exception("too big")
            }
            response.status = 0
        }catch (e: Exception) {
            response.status = 1
            response.messageError = e.message!!
        }
        return response
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class RestResponse (
        var status: Int? = null,
        var messageError: String? = null
    )

}