<template>
  <div id="app">
    <nav>
      <!-- 这里可以放置导航链接，使用<router-link>组件 -->
      <router-link to="/">Home</router-link>
      <!-- 其他导航链接... -->
    </nav>

    <!-- 路由匹配到的组件将渲染在这里 -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
/* 你的样式 */
</style>