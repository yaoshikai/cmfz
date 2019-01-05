package com.baizhi.ysk.service;

import com.aliyuncs.exceptions.ClientException;

public interface IdentifyService {
    void obtain(String phone) throws ClientException;

    Object check(String phone, String code);
}
