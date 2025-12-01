package com.xinhu.wrokflow.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.xinhu.common.annotation.Log;
import com.xinhu.common.annotation.RepeatSubmit;
import com.xinhu.common.core.controller.BaseController;
import com.xinhu.common.core.domain.PageQuery;
import com.xinhu.common.core.page.TableDataInfo;
import com.xinhu.common.enums.BusinessType;
import com.xinhu.wrokflow.common.ConditionalOnEnable;
import com.xinhu.wrokflow.domain.bo.FlowSpelBo;
import com.xinhu.wrokflow.domain.vo.FlowSpelVo;
import com.xinhu.wrokflow.service.IFlwSpelService;
import lombok.RequiredArgsConstructor;
import com.xinhu.common.core.domain.R;
import com.xinhu.common.core.validate.AddGroup;
import com.xinhu.common.core.validate.EditGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 流程spel达式定义
 *
 * @author Michelle.Chung
 * @date 2025-07-04
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/spel")
public class FlwSpelController extends BaseController {

    private final IFlwSpelService flwSpelService;

    /**
     * 查询流程spel达式定义列表
     */
    @SaCheckPermission("workflow:spel:list")
    @GetMapping("/list")
    public TableDataInfo<FlowSpelVo> list(FlowSpelBo bo, PageQuery pageQuery) {
        return flwSpelService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取流程spel达式定义详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:spel:query")
    @GetMapping("/{id}")
    public R<FlowSpelVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(flwSpelService.queryById(id));
    }

    /**
     * 新增流程spel达式定义
     */
    @SaCheckPermission("workflow:spel:add")
    @Log(title = "流程spel达式定义", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody FlowSpelBo bo) {
        return toAjax(flwSpelService.insertByBo(bo));
    }

    /**
     * 修改流程spel达式定义
     */
    @SaCheckPermission("workflow:spel:edit")
    @Log(title = "流程spel达式定义", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody FlowSpelBo bo) {
        return toAjax(flwSpelService.updateByBo(bo));
    }

    /**
     * 删除流程spel达式定义
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:spel:remove")
    @Log(title = "流程spel达式定义", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(flwSpelService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
