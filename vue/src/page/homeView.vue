<template>
  <div>
    <h1>Home Page</h1>
    <nav>
      <!-- 这里放置子菜单链接 -->
      <router-link to="/homeView/notice">公知事项</router-link>
      <router-link to="/homeView/faq">系统问询</router-link>
    </nav>

    <!-- 子路由匹配到的组件将渲染在这里 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'homeView'
}
</script>