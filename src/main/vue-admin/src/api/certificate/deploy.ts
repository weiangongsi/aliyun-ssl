import request from '@/util/request'
import type { AxiosPromise } from 'axios'
import type {
  CertificateDeploy,
  CertificateDeployAddReq,
  CertificateDeployDeployReq,
  CertificateDeployDetailReq
} from '@/api/certificate/type/deploy'

export function getCertificateDeployDetail(data: CertificateDeployDetailReq): AxiosPromise<CertificateDeploy> {
  return request({
    url: 'certificate/deploy/detail',
    method: 'post',
    data
  })
}

export function addCertificateDeploy(data: CertificateDeployAddReq): AxiosPromise<null> {
  return request({
    url: 'certificate/deploy/add',
    method: 'post',
    data
  })
}

export function deployCertificateDeploy(data: CertificateDeployDeployReq): AxiosPromise<null> {
  return request({
    url: 'certificate/deploy/deploy',
    method: 'post',
    data
  })
}
