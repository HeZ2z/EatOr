package com.hezzz.controller;

import com.hezzz.constant.ModelConstant;
import com.hezzz.result.Result;
import com.hezzz.service.SAABaseService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@Schema(description = "Base APIs")
@RequestMapping("/api/v1")
public class SAABaseController {

    private final SAABaseService baseService;

    public SAABaseController(SAABaseService baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/dashscope/getModels")
    public Result<Set<Map<String, String>>> getDashScopeModels() {

        Set<Map<String, String>> dashScope = baseService.getDashScope();

        if (dashScope.isEmpty()) {
            return Result.failed(ModelConstant.NO_DASHSCOPE_MODELS_FOUND);
        }

        return Result.success(dashScope);
    }

    @GetMapping("/health")
    public Result<String> health() {

        return Result.success("EatOr is running......");
    }

}
