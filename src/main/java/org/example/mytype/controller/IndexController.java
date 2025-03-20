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
        String namePrompt = "세계적으로 유명한 사람 64명을 랜덤으로 만들고 그 이름 중에 네가 가장 마음에 드는 거 하나 골라줘~ 제발 이름만 하나 남겨. %s".formatted(UUID.randomUUID());
        for (int i = 1; i <= 4; i++) {
            ChatResponse chatResponse = aiRepository.useModel("llama", new AIRequest(namePrompt), ChatResponse.class);
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