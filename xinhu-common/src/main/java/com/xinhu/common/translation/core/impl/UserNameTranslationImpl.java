package com.xinhu.common.translation.core.impl;

import com.xinhu.common.core.service.UserService;
import com.xinhu.common.translation.annotation.TranslationType;
import com.xinhu.common.translation.constant.TransConstant;
import com.xinhu.common.translation.core.TranslationInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户名翻译实现
 *
 * @author Lion Li
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.USER_ID_TO_NAME)
@Slf4j
@Service
public class UserNameTranslationImpl implements TranslationInterface<String> {

    private final UserService userService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof Long) {
            Long id = (Long) key;
            return userService.selectUserNameById(id);
        }
        return null;
    }
}
