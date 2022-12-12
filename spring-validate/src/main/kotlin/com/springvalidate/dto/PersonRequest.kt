package com.springvalidate.dto

import com.springvalidate.config.customValidate.CapitalizedConstraint
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.*


class PersonRequest {
    @CapitalizedConstraint(message = "Chữ đầu tiên phải được in hoa!")
    @NotNull(message = "Tên bị null!")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message = "Tên chứa kí tự đặc biệt!")
    @Length(min = 3, max = 200, message = "Tên phải từ 3 đến 200 kí tự!")
    var name: String? = null

    @Min(value = 0, message = "Tuổi không được bé hơn 0!")
    @Max(value = 200, message = "Tuổi không được lớn hơn 200!")
    var age: Long? = null

    @DecimalMin(value = "0.0", inclusive = false, message = "Chiều cao phải là số dương!")
    @DecimalMax(value = "300.5", inclusive = true, message = "Chiều cao không được cao quá 300.5 cm!")
    @Digits(integer = 3, fraction = 2, message = "Chiều cao không khớp định dạng tối đa 3 số phần nguyên và 2 số phần thập phân!")
    var height: Double? = null

    @Size(min = 2, max = 100, message = "Phải có ít nhất 2 sở thích!")
    var hobbies: List<String>? = null

    @NotNull(message = "Nghề nghiệp null")
    @Valid
    var career: Career? = null
}

data class Career (
    @NotNull(message = "Title null")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message = "Title chứa kí tự đặc biệt!")
    @Length(min = 5, max = 200, message = "Title phải từ 5 đến 50 kí tự!")
    var title: String? = null,

    @DecimalMin(value = "0", inclusive = false, message = "salary phải là số dương!")
    @DecimalMax(value = "10000", inclusive = true, message = "salary không được cao quá 10000 usd!")
    var salary: Int? = null
)