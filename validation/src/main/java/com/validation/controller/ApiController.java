package com.validation.controller;


import com.validation.dto.PersonRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private ApiService apiService;

    @Autowired
    Validator validator;

    @PostMapping("/validateAuto")
    public void validateAuto(@Valid @RequestBody PersonRequest request) {

    }

    @PostMapping("/validateByHand")
    public void validateByHand(@RequestBody PersonRequest request) {
        Set<ConstraintViolation<PersonRequest>> violations = validator.validate(request);
        for (ConstraintViolation<PersonRequest> v : violations) {
            System.out.println(v.getPropertyPath() + ": " + v.getMessage());
        }
    }

    @PostMapping("/dept/{empId}/{depthId}")
    public void updateEmpDepartment(
        @PathVariable("empId") @Min(value = 10, message = "empId >= 10") @Max(value = 100, message = "empId <= 100")
        Integer emp_id,

        @PathVariable("depthId") @Min(value = 1001, message = "depthId >= 1001")
        Integer depthId
    ) {

    }

    @PostMapping("/validateBinding")
    public <T> T validateBinding(@Valid @RequestBody PersonRequest request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorDetails = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
            return (T) errorDetails;
        }
        return null;
    }

}
