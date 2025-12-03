package com.xinhu.workflow.domain.bo;

import com.xinhu.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 流程实例请求对象
 *
 * @author may
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlowInstanceBo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流程定义名称
     */
    private String flowName;

    /**
     * 流程定义编码
     */
    private String flowCode;

    /**
     * 任务发起人
     */
    private String startUserId;

    /**
     * 业务id
     */
    private String businessId;

    /**
     * 流程分类id
     */
    private String category;

    /**
     * 任务名称
     */
    private String nodeName;

    /**
     * 申请人Ids
     */
    private List<Long> createByIds;

}
