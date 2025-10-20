package com.hezzz.entity.dashscope;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DashScope 作用域模型列表")
public class DashScopeModels implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<DashScopeModel> dashScope;

}
