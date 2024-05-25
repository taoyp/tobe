import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import router from './config/router';
import App from './App.vue'

const app = createApp(App);
app.use(ElementPlus);
app.use(router);
app.mount('#app');
