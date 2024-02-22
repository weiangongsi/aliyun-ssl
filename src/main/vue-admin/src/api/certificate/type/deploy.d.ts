export interface CertificateDeploy {
  domain: string;
  cronStatus: string;
  deployDate: string;
  deployStatus: string;
  deployError: string;
  nextDeployDate: string;
  host: string;
  port: number;
  username: string;
  password: string;
  path: string;
  shell: string;
}

export interface CertificateDeployDetailReq {
  domain: string,
}

export interface CertificateDeployDeployReq {
  domain: string,
}

export interface CertificateDeployAddReq {
  domain: string;
  cronStatus: string;
  host: string;
  port: number;
  username: string;
  password: string;
  path: string;
  shell: string;
}