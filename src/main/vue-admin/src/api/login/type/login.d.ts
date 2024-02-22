export interface LoginReq {
  username: string;
  password: string;
}

export interface LoginResp {
  token: string;
}