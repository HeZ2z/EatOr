package com.hezzz.service;

import com.hezzz.dto.ChatRequestDTO;
import reactor.core.publisher.Flux;

public interface SAAChatService {
    // 聊天接口，返回流式响应
    Flux<String> chat(ChatRequestDTO chatRequestDTO);

    // 深度思考聊天接口，返回流式响应
    Flux<String> deepThinkingChat(String chatId, String model, String prompt);
}
