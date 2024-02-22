import request from '@/util/request'
import type { AxiosPromise } from 'axios'
import type { LoginReq, LoginResp } from '@/api/login/type/login'

export function login(data: LoginReq): AxiosPromise<LoginResp> {
  return request({
    url: 'login',
    method: 'post',
    data
  })
}
