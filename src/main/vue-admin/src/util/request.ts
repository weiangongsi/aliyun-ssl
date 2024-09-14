import type { AxiosResponse, InternalAxiosRequestConfig } from 'axios'
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'
// 创建 axios 实例
const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 10000,
  headers: { 'Content-Type': 'application/json;charset=utf-8' }
})

// 请求拦截器
service.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.token = token
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse) => {
    const { status, code, message } = response.data
    if (status === 1) {
      return response.data
    } else if (code === 401) {
      // token 过期
      localStorage.clear() // 清除浏览器全部缓存
      ElMessageBox.confirm('当前页面已失效，请重新登录', '提示', {
        showClose: false,
        showCancelButton: false,
        closeOnClickModal: false,
        closeOnPressEscape: false,
      }).then(() => {
        window.location.href = '/'
      })
      return Promise.reject(new Error(message || 'Error'))
    } else {
      // 响应数据为二进制流处理(Excel导出)
      if (response.data instanceof ArrayBuffer) {
        return response
      }
      ElMessage({
        message: message || '系统出错',
        type: 'error'
      })
      return Promise.reject(new Error(message || 'Error'))
    }
  },
  error => {
    console.log(error)
    const { message } = error
    ElMessage({
      message: message || '系统出错',
      type: 'error'
    })
    return Promise.reject(new Error(message || 'Error'))
  }
)

// 导出 axios 实例
export default service
