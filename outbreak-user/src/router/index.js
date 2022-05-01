import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const Router = new VueRouter({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import('../views/login.vue')
    },
    {
      path: '/Home',
      name: 'Home',
      component: () => import('../components/Home.vue'),
      children: [
        { path: '/inform', component: () => import('../views/inform.vue') },
        { path: '/healthInfo', component: () => import('../views/healthInfo.vue') },
        { path: '/healthList', component: () => import('../views/healthList.vue') },
        { path: '/leaveInfo', component: () => import('../views/leaveInfo.vue') },
        { path: '/leaveList', component: () => import('../views/leaveList.vue') },
        { path: '/news', component: () => import('../views/news.vue') },
        { path: '/createInform', component: () => import('../views/createInform.vue') },
        { path: '/userList', component: () => import('../views/userList.vue') },
        { path: '/shealthList', component: () => import('../views/shealthList.vue') },
        { path: '/shealthDay', component: () => import('../views/shealthDay.vue') },
        { path: '/sleaveState', component: () => import('../views/sleaveState.vue') },
        { path: '/sleaveList', component: () => import('../views/sleaveList.vue') },
      ]
    }
  ]
})

export default Router
