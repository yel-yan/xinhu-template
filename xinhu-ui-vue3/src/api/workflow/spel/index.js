import request from '@/utils/request';
/**
 * 查询流程spel表达式定义列表
 * @param query
 * @returns {*}
 */

export const listSpel = (query) => {
  return request({
    url: '/workflow/spel/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询流程spel表达式定义详细
 * @param id
 */
export const getSpel = (id) => {
  return request({
    url: '/workflow/spel/' + id,
    method: 'get'
  });
};

/**
 * 新增流程spel表达式定义
 * @param data
 */
export const addSpel = (data) => {
  return request({
    url: '/workflow/spel',
    method: 'post',
    data: data
  });
};

/**
 * 修改流程spel表达式定义
 * @param data
 */
export const updateSpel = (data) => {
  return request({
    url: '/workflow/spel',
    method: 'put',
    data: data
  });
};

/**
 * 删除流程spel表达式定义
 * @param id
 */
export const delSpel = (id) => {
  return request({
    url: '/workflow/spel/' + id,
    method: 'delete'
  });
};
