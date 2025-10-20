package com.hezzz.controller;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.hezzz.dto.ChatRequestDTO;
import com.hezzz.service.SAABaseService;
import com.hezzz.service.SAAChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yuluo
 * @author <a href="mailto:yuluo08290126@gmail.com">yuluo</a>
 */

@RestController
@Tag(name = "Chat APIs")
@RequestMapping("/api/v1")
public class SAAChatController {

    private final SAAChatService chatService;

    private final SAABaseService baseService;

    public SAAChatController(SAAChatService chatService, SAABaseService baseService) {
        this.chatService = chatService;
        this.baseService = baseService;
    }

    /**
     * Send the specified parameters to get the model response.
     * 1. When the send prompt is empty, an error message is returned.
     * 2. When sending a model, it is allowed to be empty, and when the parameter has a value and
     * is in the model configuration list, the corresponding model is called. If there is no return error.
     * If the model parameter is empty, set the default model. qwen-plus
     * 3. The chatId chat memory, passed by the front-end, is of type Object and cannot be repeated
     */
    @PostMapping("/chat")
    @Operation(summary = "DashScope Flux Chat")
    public Flux<String> chat(
            HttpServletResponse response,
            @Validated @RequestBody ChatRequestDTO chatRequestDTO
    ) {
        response.setCharacterEncoding("UTF-8");
        return chatService.chat(chatRequestDTO);
    }

    @PostMapping("/deep-thinking/chat")
    public Flux<String> deepThinkingChat(
            HttpServletResponse response,
            @Validated @RequestBody String prompt,
            @RequestHeader(value = "model", required = false) String model,
            @RequestHeader(value = "chatId", required = false, defaultValue = "spring-ai-alibaba-playground-deepthink-chat") String chatId
    ) {

        Set<Map<String, String>> dashScope = baseService.getDashScope();
        List<String> modelName = dashScope.stream()
                .flatMap(map -> map.keySet().stream().map(map::get))
                .distinct()
                .toList();

        if (StringUtils.hasText(model)) {
            if (!modelName.contains(model)) {
                return Flux.just("Input model not support.");
            }
        } else {
            model = DashScopeApi.ChatModel.QWEN_PLUS.getValue();
        }

        response.setCharacterEncoding("UTF-8");
        return chatService.deepThinkingChat(chatId, model, prompt);
    }

}
