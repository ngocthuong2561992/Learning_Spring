package com.springaop.algorithm.DesignPattern.TemplateMethod.obj;

import org.springframework.ui.ModelMap;

@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(ModelMap info, int rowNum);
}
