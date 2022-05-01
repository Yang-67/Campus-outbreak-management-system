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
      path:'/Home',
      name:'Home',
      component:() => import('../components/Home.vue')
    }
  ]
})

export default Router
