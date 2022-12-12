package com.swagger2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swagger2.entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "ApiController", description = "the test API with documentation annotations")
public class ApiController {

//    @Autowired
//    private ApiService apiService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("customObjectMapper")
    private ObjectMapper mapper;

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200", description = "Found the book",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }
            ),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) }
    )
    @GetMapping("/book/{id}")
    public Book findById(@PathVariable long id) throws FileNotFoundException {
        if(id == 101) {
            throw new FileNotFoundException();
        }
        return new Book();
    }

    @PostMapping("/book/saveBook")
    public void saveBook(@RequestBody Book dto) {

    }

    @GetMapping("/filter")
    public Page<Book> filterBooks(@ParameterObject Pageable pageable) {
        return null;
    }

    @PostMapping("/getUser")
    public ModelMap getUser() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        ModelMap result = new ModelMap();
        result.put("id", 1);
        result.put("name", "King");
//        int a = 1/0;
        return result;
    }

    @PostMapping("/getClient")
    public ModelMap getClient() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        ModelMap result = new ModelMap();
        result.put("clientCode", 1001001);
        result.put("clientName", "PMH");
        return result;
    }

    @PostMapping("/saveUser")
    public void saveUser() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
//        int a = 1/0;
    }

}
