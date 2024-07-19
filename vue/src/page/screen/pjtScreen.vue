<template>
  <is-header @move="gotoNewPage"/>
  <router-view/>
</template>

<script>
import api from '@/config/api';
import IsHeader from "@/page/screen/isHeader.vue";

export default {
  name: 'pjt-screen',
  components: {
    IsHeader
  },
  created() {
    api.get('/rest/v1/hi').then(res => {
      console.log(res);
    });
  },
  computed: {
    currPath() {
      return this.$router.currentRoute.path;
    }
  },
  methods: {
    gotoNewPage(url) {
      this.$router.push(url);
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
