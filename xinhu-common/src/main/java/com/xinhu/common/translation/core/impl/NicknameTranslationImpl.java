package com.xinhu.common.translation.core.impl;

import com.xinhu.common.core.service.UserService;
import com.xinhu.common.translation.annotation.TranslationType;
import com.xinhu.common.translation.constant.TransConstant;
import com.xinhu.common.translation.core.TranslationInterface;
import lombok.AllArgsConstructor;

/**
 * 用户名称翻译实现
 *
 * @author may
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.USER_ID_TO_NICKNAME)
public class NicknameTranslationImpl implements TranslationInterface<String> {

    private final UserService userService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof Long) {
            Long id = (Long) key;
            return userService.selectNicknameByIds(id.toString());
        } else if (key instanceof String) {
            String ids = (String) key;
            return userService.selectNicknameByIds(ids);
        }
        return null;
    }
}
