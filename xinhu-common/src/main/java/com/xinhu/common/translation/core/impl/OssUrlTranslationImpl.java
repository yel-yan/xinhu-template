package com.xinhu.common.translation.core.impl;

import com.xinhu.common.core.service.OssService;
import com.xinhu.common.translation.annotation.TranslationType;
import com.xinhu.common.translation.constant.TransConstant;
import com.xinhu.common.translation.core.TranslationInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * OSS翻译实现
 *
 * @author Lion Li
 */
@Slf4j
@RequiredArgsConstructor
//@Service
@TranslationType(type = TransConstant.OSS_ID_TO_URL)
public class OssUrlTranslationImpl implements TranslationInterface<String> {

    private final OssService ossService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof String) {
            String ids = ( String) key;
            return ossService.selectUrlByIds(ids);
        } else if (key instanceof Long) {
            Long id = (Long) key;
            return ossService.selectUrlByIds(id.toString());
        }
        return null;
    }
}
