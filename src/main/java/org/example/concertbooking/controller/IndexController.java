package org.example.concertbooking.controller;

import org.example.concertbooking.model.dto.UserDTO;
import org.example.concertbooking.model.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class IndexController {
    final private UserRepository userRepository;

    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    String index(Model model) throws Exception {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @PostMapping("/user")
    String addUser(@RequestBody UserDTO userDTO) throws Exception {
        userRepository.save(userDTO);
        return "redirect:/";
    }
}
