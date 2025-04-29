package com.ai.springAiGenerate;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt) {
        return chatModel.call(prompt);
    }

    public String getResponseOptions(String prompt) {
        try {
            ChatResponse response = chatModel.call(
                    new Prompt(
                            prompt,
                            OpenAiChatOptions.builder()
                                    .model("gpt-4")
                                    .temperature(0.4)
                                    .build()));
            var output = response.getResult().getOutput();
            System.out.println("Output: " + output);
            return output.toString(); // geçici olarak
        } catch (Exception e) {
            e.printStackTrace();
            return "Bir hata oluştu: " + e.getMessage();
        }
    }
}