import router from '@/router'
import useStore from '@/stores'

// 白名单路由
const whiteList = ['/login']

router.beforeEach(async (to, from, next) => {
  const { user } = useStore()
  const hasToken = user.token
  if (hasToken) {
    // 登录成功，跳转到首页
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      next()
    }
  } else {
    // 未登录可以访问白名单页面(登录页面)
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next(`/login?redirect=${to.path}`)
    }
  }
})

router.afterEach(() => {
})
