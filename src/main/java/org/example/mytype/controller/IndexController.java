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
    String index(Model model) throws Exception {
        String[] names = {"파이썬", "자바", "자바스크립트", "HTML"};
        for (int i = 1; i <= 4; i++) {
            ChatResponse chatResponse = aiRepository.useModel("llama", new AIRequest("%s와 어울리는 별명을 지어줘. 결과만 리턴해.".formatted(names[i-1])), ChatResponse.class);
            String name = chatResponse.answer();
            model.addAttribute("name%d".formatted(i), name);
            ReasoningChatResponse reasoningChatResponse = aiRepository.useModel("deepseek", new AIRequest("%s와 어울리는 설명을 200자 이내로 한글로 작성".formatted(name)), ReasoningChatResponse.class);
            String description = reasoningChatResponse.say();
            model.addAttribute("description%d".formatted(i), description);
            ImageResponse imageResponse = aiRepository.useModel("flux",
                    new AIRequest("%s와 어울리는 그림을 그려줘".formatted(name)), ImageResponse.class);
            model.addAttribute("image%d".formatted(i), imageResponse.result());
        }
        return "index";
    }
}