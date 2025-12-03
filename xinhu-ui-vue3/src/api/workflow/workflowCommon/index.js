/*
 * @Description:
 * @Version: 1.0
 * @Autor: yel
 * @Date: 2025-12-01 15:56:33
 * @LastEditors: yel
 * @LastEditTime: 2025-12-02 17:23:56
 */

export default {
  routerJump(routerJumpVo, proxy) {
    proxy.$tab.closePage(proxy.$route);
    proxy.$router.push({
      path: routerJumpVo.formPath,
      query: {
        id: routerJumpVo.businessId,
        type: routerJumpVo.type,
        taskId: routerJumpVo.taskId
      }
    });
  }
};
