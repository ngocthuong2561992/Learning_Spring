package com.springvalidate.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.springvalidate.dto.PersonRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min


@Validated
@RestController
@RequestMapping(value = ["/api"])
class ApiController {

//    https://fullstackcode.dev/2022/05/10/complete-guide-to-spring-boot-validation/

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    @PostMapping("/createPerson")
    fun createPerson(@Valid @RequestBody request: PersonRequest) {

    }

    @PostMapping("/dept/{empId}/{depthId}")
    fun updateEmpDepartment(
        @PathVariable("empId") @Min(10, message = "empId >= 10") @Max(100, message = "empId <= 100")
        emp_id: Int?,

        @PathVariable("depthId") @Min(1001, message = "depthId >= 1001")
        depthId: Int?
    ) {

    }

//    @PostMapping("/createPersonBinding")
//    fun createPerson(@Valid @RequestBody request: PersonRequest, bindingResult: BindingResult): Any? {
//        if(bindingResult.hasErrors()) {
//            return bindingResult.getFieldErrors().associateBy({it -> (it as FieldError).field}, {it.defaultMessage})
//        }
//        return null
//    }

}