package com.xinhu.wrokflow.domain.bo;

import com.xinhu.common.core.domain.BaseEntity;
import com.xinhu.wrokflow.domain.FlowCategory;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.xinhu.common.core.validate.AddGroup;
import com.xinhu.common.core.validate.EditGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 流程分类业务对象 wf_category
 *
 * @author may
 * @date 2023-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = FlowCategory.class, reverseConvertGenerate = false)
public class FlowCategoryBo extends BaseEntity {

    /**
     * 流程分类ID
     */
    @NotNull(message = "流程分类ID不能为空", groups = { EditGroup.class })
    private Long categoryId;

    /**
     * 父流程分类id
     */
    @NotNull(message = "父流程分类id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long parentId;

    /**
     * 流程分类名称
     */
    @NotBlank(message = "流程分类名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String categoryName;

    /**
     * 显示顺序
     */
    private Long orderNum;

}
