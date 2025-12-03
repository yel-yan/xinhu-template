package com.xinhu.workflow.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinhu.common.core.mapper.BaseMapperPlus;
import org.dromara.warm.flow.orm.entity.FlowInstance;
import org.apache.ibatis.annotations.Param;


/**
 * 实例信息Mapper接口
 *
 * @author may
 * @date 2024-03-02
 */
public interface FlwInstanceMapper extends BaseMapperPlus<FlwInstanceMapper, FlowInstance, FlowInstance> {

    /**
     * 流程实例信息
     *
     * @param page         分页
     * @param queryWrapper 条件
     * @return 结果
     */
    Page<FlowInstance> selectInstanceList(@Param("page") Page<FlowInstance> page, @Param(Constants.WRAPPER) Wrapper<FlowInstance> queryWrapper);
}
