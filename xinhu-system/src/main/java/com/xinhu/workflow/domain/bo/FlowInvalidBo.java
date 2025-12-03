package com.xinhu.workflow.domain.bo;

import com.xinhu.common.core.validate.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 作废请求对象
 *
 * @author may
 */
@Data
public class FlowInvalidBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流程实例id
     */
    @NotNull(message = "流程实例id为空", groups = AddGroup.class)
    private Long id;

    /**
     * 审批意见
     */
    private String comment;
}
