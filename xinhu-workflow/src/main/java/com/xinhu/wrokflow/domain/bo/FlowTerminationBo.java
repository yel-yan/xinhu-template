package com.xinhu.wrokflow.domain.bo;

import lombok.Data;
import com.xinhu.common.core.validate.AddGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 终止任务请求对象
 *
 * @author may
 */
@Data
public class FlowTerminationBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @NotNull(message = "任务id为空", groups = AddGroup.class)
    private Long taskId;

    /**
     * 审批意见
     */
    private String comment;
}
