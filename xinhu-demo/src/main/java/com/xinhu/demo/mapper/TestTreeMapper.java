package com.xinhu.demo.mapper;

import com.xinhu.common.annotation.DataColumn;
import com.xinhu.common.annotation.DataPermission;
import com.xinhu.common.core.mapper.BaseMapperPlus;
import com.xinhu.demo.domain.TestTree;
import com.xinhu.demo.domain.vo.TestTreeVo;

/**
 * 测试树表Mapper接口
 *
 * @author Lion Li
 * @date 2021-07-26
 */
@DataPermission({
    @DataColumn(key = "deptName", value = "dept_id"),
    @DataColumn(key = "userName", value = "user_id")
})
public interface TestTreeMapper extends BaseMapperPlus<TestTreeMapper, TestTree, TestTreeVo> {

}
