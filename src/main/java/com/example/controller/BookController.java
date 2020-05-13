package com.example.controller;

import com.example.entity.Book;
import com.example.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Api(value = "Users orders controller")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "creating book page")
    @GetMapping("/createBook")
    public String createBookPage(Model model){
        model.addAttribute("message", "Create new book");
        model.addAttribute("book", new Book());

        return "createBook";
    }

    @ApiOperation(value = "create new book")
    @PostMapping("/createBook")
    public String createBook(@ModelAttribute Book book){
        bookService.saveBook(book);

        return "redirect:/users";
    }
}
