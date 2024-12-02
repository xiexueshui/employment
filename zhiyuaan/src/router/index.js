import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'hash',
  routes: [

    {
      path: '/',
      name: 'index',
      component: () => import('../views/login.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login.vue')
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('../views/index.vue')
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('../views/user.vue')
    },
    {
      path: '/manager',
      name: 'manager',
      component: () => import('../views/manage/index.vue'),
      children: [
        {
          path: '/userManager',
          name: 'userManager',
          component: () => import('../views/manage/userManager.vue')
        },
        {
          path: '/employManager',
          name: 'employManager',
          component: () => import('../views/manage/employManager.vue')
        }
      ]
    }

  ]
})
