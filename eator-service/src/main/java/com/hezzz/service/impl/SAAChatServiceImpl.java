package com.hezzz.service.impl;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.hezzz.constant.ModelConstant;
import com.hezzz.dto.ChatRequestDTO;
import com.hezzz.service.SAABaseService;
import com.hezzz.service.SAAChatService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SAAChatServiceImpl implements SAAChatService {

    private final SAABaseService baseService;

    public SAAChatServiceImpl(SAABaseService baseService) {
        this.baseService = baseService;
    }


    @Override
    public Flux<String> chat(ChatRequestDTO chatRequestDTO) {

        String prompt = chatRequestDTO.getPrompt();
        String model = chatRequestDTO.getModel();
        String chatId = chatRequestDTO.getChatId();


        // 从 DashScope 获取支持的模型列表
        Set<Map<String, String>> dashScope = baseService.getDashScope();
        // 提取模型名称
        List<String> modelName = dashScope.stream()
                .flatMap(map -> map.values().stream())
                .distinct()
                .toList();
        // 验证并设置模型
        if (StringUtils.hasText(model)) {
            if (!modelName.contains(model)) {
                return Flux.just(ModelConstant.INPUT_MODEL_NOT_FOUND);
            }
        } else {
            model = DashScopeApi.ChatModel.QWEN_PLUS.getValue();
        }

        //TODO: 加到数据库,以及原有的Chat

        return null;
    }

    @Override
    public Flux<String> deepThinkingChat(String chatId, String model, String prompt) {
        return null;
    }
}
