import { createRouter, createWebHistory} from "vue-router";

import initTobe from "@/page/initTobe.vue";
import home from "@/page/home.vue"

const routes = [
    {
        path: '/init',
        name: 'init',
        component: initTobe
    },
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'home',
        component: home
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;