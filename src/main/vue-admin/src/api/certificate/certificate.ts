import request from '@/util/request'
import type { AxiosPromise } from 'axios'
import type { CertificateAddReq, CancelCertificateReq, Certificate } from '@/api/certificate/type/certificate'

export function getCertificateList(): AxiosPromise<Certificate[]> {
  return request({
    url: '/certificate/list',
    method: 'post'
  })
}

export function addCertificate(data: CertificateAddReq): AxiosPromise<null> {
  return request({
    url: '/certificate/add',
    method: 'post',
    data
  })
}

export function cancelCertificate(data: CancelCertificateReq): AxiosPromise<null> {
  return request({
    url: '/certificate/cancel',
    method: 'post',
    data
  })
}

export function syncCertificate(): AxiosPromise<null> {
  return request({
    url: '/certificate/sync',
    method: 'post'
  })
}