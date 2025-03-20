package org.example.mytype.controller;

import org.example.mytype.model.dto.AIRequest;
import org.example.mytype.model.dto.ChatResponse;
import org.example.mytype.model.dto.ImageResponse;
import org.example.mytype.model.dto.ReasoningChatResponse;
import org.example.mytype.model.repository.AIRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class IndexController {
    final private Logger logger = Logger.getLogger(IndexController.class.getName());
    final private AIRepository aiRepository;

    public IndexController(AIRepository aiRepository) {
        this.aiRepository = aiRepository;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/result")
    String result(Model model) throws Exception {
        String[] names = {"NodeJS", "Java", "React", "Spring Boot"};
        for (int i = 1; i <= 4; i++) {
            ReasoningChatResponse reasoning = aiRepository.useModel("deepseek", new AIRequest("Think of the freshest and creative and funny nickname for {%s} in your opinion (However, there is no law that says the word itself must be included), and return only one result without that process. If it's not Korean, Please write it in Korean pronunciation using korean language. Check again to see if only the results are returned without explanation.".formatted(names[i-1])), ReasoningChatResponse.class);
            String name = reasoning.say();
            model.addAttribute("name%d".formatted(i), name);
            String description = aiRepository.useModel("llama", new AIRequest("Write the description that matches {%s} in a serious but crazy tone and in Korean Language in 200 words or less. Only Use Korean Language! If there are other languages, please translate them. Chinese or Chinese characters and Japanese are not Korean. Only Use Korean Language!".formatted(name)), ChatResponse.class).answer();
            model.addAttribute("description%d".formatted(i), description);
            ImageResponse imageResponse = aiRepository.useModel("flux",
                    new AIRequest("Create a fantasy character that matches {%s} and focus only on that character to create an image. only character please.".formatted(name)), ImageResponse.class);
            model.addAttribute("image%d".formatted(i), imageResponse.result());
        }
        return "result";
    }
}