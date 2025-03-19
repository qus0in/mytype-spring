package org.example.concertbooking.controller;

import org.example.concertbooking.model.dto.UserDTO;
import org.example.concertbooking.model.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    // form을 사용한 POST 요청은 ModelAttribute
    String addUser(@ModelAttribute UserDTO userDTO) throws Exception {
        System.out.println("도달하고 있니?");
        userRepository.save(new UserDTO(
                UUID.randomUUID().toString(),
                userDTO.name(),
                userDTO.email(),
                userDTO.phone()
        ));
        return "redirect:/";
    }
}
