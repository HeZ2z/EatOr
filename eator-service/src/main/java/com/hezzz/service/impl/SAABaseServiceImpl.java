package com.hezzz.service.impl;

import com.hezzz.constant.ModelConstant;
import com.hezzz.exception.SAAAppException;
import com.hezzz.service.SAABaseService;
import com.hezzz.utils.ModelsUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Service
public class SAABaseServiceImpl implements SAABaseService {
    @Override
    public Set<Map<String, String>> getDashScope() {
        Set<Map<String, String>> resultSet;

        try {
            resultSet = ModelsUtils.getDashScopeModels();
        } catch (IOException e) {
            throw new SAAAppException(ModelConstant.GET_DASHSCOPE_MODELS_FAILED + e.getMessage());
        }

        return Set.of();
    }
}
