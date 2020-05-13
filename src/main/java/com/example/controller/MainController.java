package com.example.controller;

import com.example.entity.Card;
import com.example.entity.User;
import com.example.service.CardService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private CardService cardServiceImpl;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", "Main page");
        return "mainPage";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("message", "All users");
        model.addAttribute("users", userServiceImpl.findAllUsers());

        return "users";
    }

    @GetMapping("/newUser")
    public String newUser(Model model){
        model.addAttribute("message", "Create new user");
        model.addAttribute("user", new User());

        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user){
        userServiceImpl.updateUser(user);

        return "redirect:/users";
    }

    @RequestMapping("/removeUser/{userId}")
    public String removeUser(@PathVariable("userId")long id){
        userServiceImpl.removeUserById(id);

        return "redirect:/users";
    }

    @GetMapping("/updateUser/{userId}")
    public String updateUser(Model model, @PathVariable("userId")long id){
        User user = userServiceImpl.findUserById(id);
        model.addAttribute("message", "Update user");
        model.addAttribute("user", user);
        model.addAttribute("cards", user.getCards());

        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUserData(@ModelAttribute User user){
        userServiceImpl.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/createCard")
    public String newCard(Model model){
        model.addAttribute("message", "Create new card");
        model.addAttribute("card", new Card());

        return "createCard";
    }

    @PostMapping("/createCard")
    public String addCard(@ModelAttribute Card card){
        cardServiceImpl.updateCard(card);

        return "redirect:/users";
    }

    @GetMapping("/updateCard/{cardId}/{userId}")
    public String updateCard(Model model, @PathVariable("cardId")long cardId, @PathVariable("userId")long userId){
        model.addAttribute("message", "Update card");
        model.addAttribute("card", cardServiceImpl.findCardById(cardId));
        model.addAttribute("userId", userId);

        return "updateCard";
    }

    @RequestMapping("/removeCard/{cardId}/{userId}")
    public String removeCard(@PathVariable("cardId")long id, @PathVariable("userId")long userId){
        User user = userServiceImpl.findUserById(userId);
        user.setCards(user.getCards().stream().filter(f -> f.getId() != id).collect(Collectors.toSet()));
        userServiceImpl.updateUser(user);
        cardServiceImpl.removeCardById(id);

        return "redirect:/updateUser/" + userId;
    }

    @GetMapping("/addCardToUser/{cardId}/{userId}")
    public String addCardToUser(@PathVariable("cardId")long cardId, @PathVariable("userId")long userId){
        User user = userServiceImpl.findUserById(userId);
        Card card = cardServiceImpl.findCardById(cardId);
        user.getCards().add(card);
        userServiceImpl.updateUser(user);

        return "redirect:/updateUser/" + userId;
    }
}
