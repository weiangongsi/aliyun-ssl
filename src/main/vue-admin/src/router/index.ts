import { createRouter, createWebHashHistory } from 'vue-router'

export const Layout = () => import('@/layout/index.vue')

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/login',
      component: () => import('@/views/login/index.vue'),
      name: 'Login',
    },
    {
      path: '/',
      component: Layout,
      redirect: '/dashboard',
      children: [{
        path: 'dashboard',
        component: () => import('@/views/dashboard/index.vue'),
        name: 'Dashboard',
        meta: { activeMenu: '/' }
      }]
    },
    {
      path: '/certificate',
      component: Layout,
      redirect: '/certificate/index',
      children: [{
        path: 'index',
        component: () => import('@/views/certificate/index.vue'),
        name: 'Certificate',
        meta: { activeMenu: '/certificate' }
      }]
    }
  ]
})

export default router
