import request from '@/utils/request';
/**
 * 查询待办列表
 * @param query
 * @returns {*}
 */
export const pageByTaskWait = (query) => {
  return request({
    url: '/workflow/task/pageByTaskWait',
    method: 'get',
    params: query
  });
};

/**
 * 查询已办列表
 * @param query
 * @returns {*}
 */
export const pageByTaskFinish = (query) => {
  return request({
    url: '/workflow/task/pageByTaskFinish',
    method: 'get',
    params: query
  });
};

/**
 * 查询当前用户的抄送列表
 * @param query
 * @returns {*}
 */
export const pageByTaskCopy = (query) => {
  return request({
    url: '/workflow/task/pageByTaskCopy',
    method: 'get',
    params: query
  });
};

/**
 * 当前租户所有待办任务
 * @param query
 * @returns {*}
 */
export const pageByAllTaskWait = (query) => {
  return request({
    url: '/workflow/task/pageByAllTaskWait',
    method: 'get',
    params: query
  });
};

/**
 * 当前租户所有已办任务
 * @param query
 * @returns {*}
 */
export const pageByAllTaskFinish = (query) => {
  return request({
    url: '/workflow/task/pageByAllTaskFinish',
    method: 'get',
    params: query
  });
};

/**
 * 启动流程
 * @param data
 * @returns {*}
 */
export const startWorkFlow = (data) => {
  return request({
    url: '/workflow/task/startWorkFlow',
    method: 'post',
    data: data
  });
};

/**
 * 办理流程
 * @param data
 * @returns {*}
 */
export const completeTask = (data) => {
  return request({
    url: '/workflow/task/completeTask',
    method: 'post',
    data: data
  });
};

/**
 * 任务驳回
 * @param data
 * @returns {*}
 */
export const backProcess = (data) => {
  return request({
    url: '/workflow/task/backProcess',
    method: 'post',
    data: data
  });
};

/**
 * 获取当前任务
 * @param taskId
 * @returns
 */
export const getTask = (taskId) => {
  return request({
    url: '/workflow/task/getTask/' + taskId,
    method: 'get'
  });
};

/**
 * 修改任务办理人
 * @param taskIdList
 * @param userId
 * @returns
 */
export const updateAssignee = (taskIdList) => {
  return request({
    url: `/workflow/task/updateAssignee/${userId}`,
    method: 'put',
    data: taskIdList
  });
};

/**
 * 终止任务
 * @returns
 */
export const terminationTask = (data) => {
  return request({
    url: `/workflow/task/terminationTask`,
    method: 'post',
    data: data
  });
};

/**
 * 获取可驳回得任务节点
 * @returns
 */
export const getBackTaskNode = (taskId) => {
  return request({
    url: `/workflow/task/getBackTaskNode/${taskId}/${nodeCode}`,
    method: 'get'
  });
};

/**
 * 任务操作 操作类型，委派 delegateTask、转办 transferTask、加签 addSignature、减签 reductionSignature
 * @returns
 */
export const taskOperation = (data, operation) => {
  return request({
    url: `/workflow/task/taskOperation/${operation}`,
    method: 'post',
    data: data
  });
};

/**
 * 获取当前任务办理人
 * @param taskId 任务id
 * @returns
 */
export const currentTaskAllUser = (taskId) => {
  return request({
    url: `/workflow/task/currentTaskAllUser/${taskId}`,
    method: 'get'
  });
};

/**
 * 获取下一节点写
 * @param data参数
 * @returns
 */
export const getNextNodeList = (data) => {
  return request({
    url: '/workflow/task/getNextNodeList',
    method: 'post',
    data: data
  });
};

/**
 * 催办任务
 * @param data参数
 * @returns
 */
export const urgeTask = (data) => {
  return request({
    url: '/workflow/task/urgeTask',
    method: 'post',
    data: data
  });
};
