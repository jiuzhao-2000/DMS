<template>
  <router-view></router-view>
</template>

<script>
/* 
解决el-table报错问题
*/
const debounce = (fn, delay) => {
  let timer = null;
  return function () {
    let context = this;
    let args = arguments;
    clearTimeout(timer);
    timer = setTimeout(function () {
      fn.apply(context, args);
    }, delay);
  }
}
const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
  constructor(callback) {
    callback = debounce(callback, 16);
    super(callback);
  }
}
/* 
结束
*/
export default {
  name: 'App',
}
</script>

<style>
*{
    margin: 0;
    padding: 0;
}
</style>
