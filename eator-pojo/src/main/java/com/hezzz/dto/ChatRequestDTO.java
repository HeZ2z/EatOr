package com.hezzz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "聊天数据 DTO")
public class ChatRequestDTO {

    @NotBlank(message = "Prompt cannot be blank")
    @Schema(description = "提示语")
    String prompt;

    @Schema(description = "模型名称")
    private String model;

    @Schema(description = "聊天 ID")
    private String chatId;

}
