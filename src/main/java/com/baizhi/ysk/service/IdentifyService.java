package com.baizhi.ysk.service;

import com.aliyuncs.exceptions.ClientException;
import com.baizhi.ysk.dto.IdentifyDto;

public interface IdentifyService {
    void obtain(String phone) throws ClientException;

    IdentifyDto check(String phone, String code);
}
