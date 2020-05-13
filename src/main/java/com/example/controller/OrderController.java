package com.example.controller;

import com.example.entity.Order;
import com.example.service.BookService;
import com.example.service.OrderService;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(value = "Users orders controller")
public class OrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @ApiOperation(value = "show user orders")
    @GetMapping("/showOrders/{userId}")
    public String showUserOrders(Model model, @PathVariable("userId") long userId) {
        model.addAttribute("message", "User orders");
        model.addAttribute("orders", userService.findUserById(userId).getOrders());
        model.addAttribute("userId", userId);

        return "userOrders";
    }

    @ApiOperation(value = "remove book form order")
    @GetMapping("/removeBookFromOrder/{orderId}/{bookId}/{userId}")
    public String removeBookFromOrder(@PathVariable("orderId") long orderId, @PathVariable("bookId") long bookId, @PathVariable("userId") long userId) {
        orderService.removeBookFromOrder(orderId, bookId);

        return "redirect:/showOrders/" + userId;
    }

    @ApiOperation(value = "remove book form creating order")
    @PutMapping("/removeBookFromCreatingOrder/{orderId}/{bookId}/{userId}")
    public String removeBookFromCreatingOrder(Model model, @PathVariable("orderId") long orderId, @PathVariable("bookId") long bookId, @PathVariable("userId") long userId) {
        orderService.removeBookFromOrder(orderId, bookId);

        model.addAttribute("userId", userId);
        model.addAttribute("order", orderService.findOrderById(orderId));
        model.addAttribute("books", bookService.findAll());

        return "createOrder";
    }

    @ApiOperation(value = "Remove order")
    @GetMapping("/removeOrder/{orderId}/{userId}")
    public String removeOrder(@PathVariable("orderId") long orderId, @PathVariable("userId") long userId) {
        orderService.removeOrder(orderId);

        return "redirect:/showOrders/" + userId;
    }

    @ApiOperation(value = "Create new order page")
    @GetMapping("/createOrder/{userId}")
    public String createOrder(Model model, @PathVariable("userId") long userId) {
        Order order = orderService.createOrder(new Order());
        model.addAttribute("userId", userId);
        model.addAttribute("order", order);
        model.addAttribute("books", bookService.findAll());

        return "createOrder";
    }

    @ApiOperation(value = "Create new order")
    @PostMapping("/createOrder/{userId}")
    public String createOrderData(@ModelAttribute Order order, @PathVariable("userId") long userId) {
        orderService.saveOrder(order, userId);

        return "redirect:/updateUser/" + userId;
    }

    @ApiOperation(value = "Add book to order")
    @PostMapping("/addBookToOrder/{orderId}/{userId}")
    public String addBookToOrder(Model model,
                                 @PathVariable("orderId") long orderId,
                                 @PathVariable("userId") long userId,
                                 @RequestParam(value = "booksForOrder", required = false) long[] books) {

        for (long id : books) {
            orderService.addBookToOrder(orderId, id);
        }
        model.addAttribute("userId", userId);
        model.addAttribute("order", orderService.findOrderById(orderId));
        model.addAttribute("books", bookService.findAll());

        return "createOrder";
    }
}
