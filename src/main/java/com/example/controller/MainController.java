package com.example.controller;

import com.example.entity.Card;
import com.example.entity.User;
import com.example.service.CardService;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(value = "Users cards controller")
public class MainController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private CardService cardServiceImpl;

    @ApiOperation(value = "Main page")
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", "Main page");
        return "mainPage";
    }

    @ApiOperation(value = "Get all users")
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("message", "All users");
        model.addAttribute("users", userServiceImpl.findAllUsers());

        return "users";
    }

    @ApiOperation(value = "New user page")
    @GetMapping("/newUser")
    public String newUser(Model model){
        model.addAttribute("message", "Create new user");
        model.addAttribute("user", new User());

        return "addUser";
    }

    @ApiOperation(value = "Add new user")
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user){
        userServiceImpl.updateUser(user);

        return "redirect:/users";
    }

    @ApiOperation(value = "Remove user")
    @RequestMapping("/removeUser/{userId}")
    public String removeUser(@PathVariable("userId")long id){
        userServiceImpl.removeUserById(id);

        return "redirect:/users";
    }

    @ApiOperation(value = "Update user page")
    @GetMapping("/updateUser/{userId}")
    public String updateUser(Model model, @PathVariable("userId")long id){
        User user = userServiceImpl.findUserById(id);
        model.addAttribute("message", "Update user");
        model.addAttribute("user", user);
        model.addAttribute("cards", user.getCards());
        model.addAttribute("allCards", cardServiceImpl.findAll());

        return "updateUser";
    }

    @ApiOperation(value = "Update user data")
    @PostMapping("/updateUser")
    public String updateUserData(@ModelAttribute User user){
        userServiceImpl.updateUserData(user);

        return "redirect:/users";
    }

    @ApiOperation(value = "Create card page")
    @GetMapping("/createCard")
    public String newCard(Model model){
        model.addAttribute("message", "Create new card");
        model.addAttribute("card", new Card());

        return "createCard";
    }

    @ApiOperation(value = "Create new card")
    @PostMapping("/createCard")
    public String addCard(@ModelAttribute Card card){
        cardServiceImpl.updateCard(card);

        return "redirect:/users";
    }

    @ApiOperation(value = "Update card data")
    @GetMapping("/updateCard/{cardId}/{userId}")
    public String updateCard(Model model, @PathVariable("cardId")long cardId, @PathVariable("userId")long userId){
        model.addAttribute("message", "Update card");
        model.addAttribute("card", cardServiceImpl.findCardById(cardId));
        model.addAttribute("userId", userId);

        return "updateCard";
    }

    @ApiOperation(value = "Remove users card")
    @RequestMapping("/removeCard/{cardId}/{userId}")
    public String removeCard(@PathVariable("cardId")long cardId, @PathVariable("userId")long userId){
        userServiceImpl.removeCard(userId, cardId);

        return "redirect:/updateUser/" + userId;
    }

    @ApiOperation(value = "Add new card for user")
    @GetMapping("/addCardToUser/{cardId}/{userId}")
    public String addCardToUser(@PathVariable("cardId")long cardId, @PathVariable("userId")long userId){
        userServiceImpl.addCardToUser(userId, cardId);

        return "redirect:/updateUser/" + userId;
    }
}
