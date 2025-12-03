package com.xinhu.workflow.domain.bo;

import com.xinhu.common.core.validate.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 撤销任务请求对象
 *
 * @author may
 */
@Data
public class FlowCancelBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @NotBlank(message = "业务ID不能为空", groups = AddGroup.class)
    private String businessId;

    /**
     * 办理意见
     */
    private String message;
}
