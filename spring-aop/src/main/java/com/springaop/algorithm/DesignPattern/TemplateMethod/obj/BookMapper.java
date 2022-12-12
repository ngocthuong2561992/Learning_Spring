package com.springaop.algorithm.DesignPattern.TemplateMethod.obj;

import org.springframework.ui.ModelMap;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ModelMap info, int rowNum) {
        Book book = new Book();
        book.setPrice((Integer) info.get("price"));
        book.setTitle((String) info.get("title"));
        return book;
    }
}
