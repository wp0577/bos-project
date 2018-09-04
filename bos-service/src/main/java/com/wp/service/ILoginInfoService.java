package com.wp.service;

import com.wp.domain.LoginInfo;

public interface ILoginInfoService {
    void save(LoginInfo u);
    LoginInfo getLoginByIp(String ip);
}
