import { createRouter, createWebHistory} from "vue-router";

import initTobe from "@/page/initTobe.vue";
import home from "@/page/home.vue";
import homeView from "@/page/homeView.vue";
import notice from "@/page/noticeView.vue";
import historyView from "@/page/historyView.vue";
import adminView from "@/page/adminView.vue";
import faq from "@/page/faqView.vue";
import elPs from "@/page/elPs.vue";

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
        component: home
    },
    {
        path: '/elps',
        component: elPs
    },
    { path: '/homeView', name: 'homeView', component: homeView,
        children: [ // 添加子路由
            {
                path: 'notice', // 公知事项的子路由路径
                component: notice,
            },
            {
                path: 'faq', // 系统问询的子路由路径
                component: faq
            }
        ]
    },
    { path: '/notice1', name: 'notice', component: notice },
    { path: '/admin', name: 'admin', component: adminView },
    { path: '/history', name: 'history', component: historyView },
    // { path: '/faq', name: 'faq', component: faq }
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;