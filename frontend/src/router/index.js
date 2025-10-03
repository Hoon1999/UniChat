// router/index.js

import { createRouter, createWebHistory } from "vue-router"

const router = createRouter({
    history: createWebHistory(""),
    routes: [
        {
            name: "login",
            path: "/",
            component: () => import("../views/Login.vue")
        },
        {
            name: "chatting",
            path: "/chatting",
            component: () => import("../views/Chatting.vue")
        }
    ],
});

export default router;