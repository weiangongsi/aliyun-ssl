import { defineStore } from 'pinia'
// type
import type { LoginReq, LoginResp } from '@/api/login/type/login'
// api
import { login } from '@/api/login/login'

const useUserStore = defineStore({
  id: 'user',
  state: (): LoginResp => ({
    token: localStorage.getItem('token') || ''
  }),
  actions: {
    async RESET_STATE() {
      this.$reset()
    },
    /**
     * 登录
     */
    login(loginData: LoginReq) {
      const { username, password } = loginData
      return new Promise((resolve, reject) => {
        login({
          username: username.trim(),
          password: password
        })
          .then(response => {
            const { token } = response.data
            localStorage.setItem('token', token)
            this.token = token
            resolve(token)
          })
          .catch(error => {
            reject(error)
          })
      })
    },

    /**
     *  退出
     */
    logout() {
      return new Promise(resolve => {
        localStorage.removeItem('token')
        this.RESET_STATE()
        resolve(undefined)
      })
    }

  }
})

export default useUserStore
