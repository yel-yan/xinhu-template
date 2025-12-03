import request from '@/utils/request';
/**
 * 获取流程定义列表
 * @param query 流程实例id
 * @returns
 */
export const listDefinition = (query)=> {
  return request({
    url: `/workflow/definition/list`,
    method: 'get',
    params: query
  });
};

/**
 * 查询未发布的流程定义列表
 * @param query 流程实例id
 * @returns
 */
export const unPublishList = (query) => {
  return request({
    url: `/workflow/definition/unPublishList`,
    method: 'get',
    params: query
  });
};

/**
 * 通过流程定义id获取xml
 * @param definitionId 流程定义id
 * @returns
 */
export const definitionXml = (definitionId) => {
  return request({
    url: `/workflow/definition/definitionXml/${definitionId}`,
    method: 'get'
  });
};

/**
 * 删除流程定义
 * @param id 流程定义id
 * @returns
 */
export const deleteDefinition = (id) => {
  return request({
    url: `/workflow/definition/${id}`,
    method: 'delete'
  });
};

/**
 * 挂起/激活
 * @param definitionId 流程定义id
 * @param activityStatus 状态
 * @returns
 */
export const active = (definitionId, activityStatus) => {
  return request({
    url: `/workflow/definition/active/${definitionId}`,
    method: 'put',
    params: {
      active: activityStatus
    }
  });
};

/**
 * 通过zip或xml部署流程定义
 * @returns
 */
export function importDef(data) {
  return request({
    url: '/workflow/definition/importDef',
    method: 'post',
    data: data,
    headers: {
      repeatSubmit: false
    }
  });
}

/**
 * 发布流程定义
 * @param id 流程定义id
 * @returns
 */
export const publish = (id) => {
  return request({
    url: `/workflow/definition/publish/${id}`,
    method: 'put'
  });
};

/**
 * 取消发布流程定义
 * @param id 流程定义id
 * @returns
 */
export const unPublish = (id) => {
  return request({
    url: `/workflow/definition/unPublish/${id}`,
    method: 'put'
  });
};

/**
 * 获取流程定义xml字符串
 * @param id 流程定义id
 * @returns
 */
export const xmlString = (id) => {
  return request({
    url: `/workflow/definition/xmlString/${id}`,
    method: 'get'
  });
};

/**
 * 新增
 * @param data 参数
 * @returns
 */
export const add = (data) => {
  return request({
    url: `/workflow/definition`,
    method: 'post',
    data: data
  });
};

/**
 * 修改
 * @param data 参数
 * @returns
 */
export const edit = (data) => {
  return request({
    url: `/workflow/definition`,
    method: 'put',
    data: data
  });
};

/**
 * 查询详情
 * @param id 参数
 * @returns
 */
export const getInfo = (id) => {
  return request({
    url: `/workflow/definition/${id}`,
    method: 'get'
  });
};

/**
 * 复制流程定义
 * @param id 流程定义id
 * @returns
 */
export const copy = (id) => {
  return request({
    url: `/workflow/definition/copy/${id}`,
    method: 'post'
  });
};
