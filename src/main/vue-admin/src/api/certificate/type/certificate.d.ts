export interface Certificate {
  instanceId: string,
  name: string,
  domain: string,
  sans: string,
  orderId: number,
  certificateId: number,
  status: string,
  startDate: string,
  endDate: string,
  cronStatus: string
  deployDate: string
  deployStatus: string
  deployError: string
  nextDeployDate: string
}
export interface CertificateAddReq {
  domain: string,
}
export interface CancelCertificateReq {
  instanceId: string,
}