/*
 * @Description:
 * @Version: 1.0
 * @Autor: yel
 * @Date: 2025-12-01 15:56:33
 * @LastEditors: yel
 * @LastEditTime: 2025-12-03 22:09:32
 */
import request from '@/utils/request';

/**
 * 查询请假列表
 * @param query
 * @returns {*}
 */

export const listLeave = (query) => {
  return request({
    url: '/workflow/leave/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询请假详细
 * @param id
 */
export const getLeave = (id) => {
  return request({
    url: '/workflow/leave/' + id,
    method: 'get'
  });
};

/**
 * 新增请假
 * @param data
 */
export const addLeave = (data) => {
  return request({
    url: '/workflow/leave',
    method: 'post',
    data: data
  });
};

/**
 * 提交请假并发起流程
 * @param data
 */
export const submitAndFlowStart = (data) => {
  return request({
    url: '/workflow/leave/submitAndFlowStart',
    method: 'post',
    data: data
  });
};

/**
 * 修改请假
 * @param data
 */
export const updateLeave = (data) => {
  return request({
    url: '/workflow/leave',
    method: 'put',
    data: data
  });
};

/**
 * 删除请假
 * @param id
 */
export const delLeave = (id) => {
  return request({
    url: '/workflow/leave/' + id,
    method: 'delete'
  });
};
