<template>
  <div class="top-nav">
    <ul class="nav clearfix" style="padding: 0; margin: 0; list-style: none;">
      <li class="active1">
        原创
        <div class="list-nav">
          <ul style="padding: 0; margin: 0; list-style: none;">
            <li>类型1</li>
            <li>类型2</li>
            <li>类型3</li>
            <li>类型4</li>
            <li>类型5</li>
            <li>类型6</li>
            <li>类型7</li>
          </ul>
        </div>
      </li>
      <li @click="moveMenu('/notice1')">Notice</li>
      <li @click="moveMenu('/history')">History</li>
      <li @click="moveMenu('/admin')">admin</li>
      <li>出版</li>
      <li>会员</li>
      <li>App</li>
    </ul>

    <div class="log">
      登录
    </div>

    <div class="reg">
      注册
    </div>
  </div>
</template>


<script>
export default {
  name: 'is-header',
  methods: {
    moveMenu(url) {
      this.$emit("move", url)
    }
  }
}
</script>

<style scoped>
.clearfix::after{
  content: "";
  clear: both;
  display: block;
}

.top-nav{
  border: 1px solid gainsboro;
  height: 50px;
  position:fixed;
  top: 0;
  width: 100%;
  z-index: 1;
  background-color: #fff;
}

.top-nav .nav{
  position: absolute;
  left: 488px;
  top: 15px;
}

.top-nav .nav .active1:hover .list-nav{
  display: block;
}

.top-nav .nav .list-nav{
  position: absolute;
  top: 30px;
  left: -570px;
  width: 1800px;
  height: 60px;
  background-color:#fff;
  line-height: 60px;
  font-size: small;
  padding-left: 500px;
  display: none;
}

.top-nav .nav li{
  float: left;
  margin: 0 18px;
  cursor: pointer;
}

.top-nav .log{
  position: absolute;
  right: 140px;
  top: 10px;
  font-size: small;
  padding: 4px 10px;
  border: 1px solid gainsboro;
  border-style: outset;
  border-radius: 4px;
}

.top-nav .reg{
  position: absolute;
  right: 80px;
  top: 10px;
  font-size: small;
  padding: 4px 10px;
  border: 1px solid gainsboro;
  border-style: outset;
  border-radius: 4px;
}
</style>