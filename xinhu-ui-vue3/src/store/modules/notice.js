/*
 * @Description:
 * @Version: 1.0
 * @Autor: yel
 * @Date: 2025-12-05 09:57:22
 * @LastEditors: yel
 * @LastEditTime: 2025-12-05 10:08:49
 */
import { defineStore } from 'pinia';
import { reactive } from 'vue';

const useNoticeStore = defineStore('notice', () => {
  const state = reactive({
    notices: []
  });

  const addNotice = (notice) => {
    state.notices.push(notice);
  };

  const removeNotice = (notice) => {
    state.notices.splice(state.notices.indexOf(notice), 1);
  };

  //实现全部已读
  const readAll = () => {
    state.notices.forEach((item) => {
      item.read = true;
    });
  };

  const clearNotice = () => {
    state.notices = [];
  };
  return {
    state,
    addNotice,
    removeNotice,
    readAll,
    clearNotice
  };
});

export default useNoticeStore;
