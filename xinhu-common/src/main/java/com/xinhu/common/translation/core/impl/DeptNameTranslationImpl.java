package com.xinhu.common.translation.core.impl;

import com.xinhu.common.core.service.DeptService;
import com.xinhu.common.translation.annotation.TranslationType;
import com.xinhu.common.translation.constant.TransConstant;
import com.xinhu.common.translation.core.TranslationInterface;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 部门翻译实现
 *
 * @author Lion Li
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.DEPT_ID_TO_NAME)
@Slf4j
@Service
public class DeptNameTranslationImpl implements TranslationInterface<String> {

    private final DeptService deptService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof String) {
            String ids = (String) key;
            return deptService.selectDeptNameByIds(ids);
        } else if (key instanceof Long) {
            Long id = (Long) key;
            return deptService.selectDeptNameByIds(id.toString());
        }
        return null;
    }
}
