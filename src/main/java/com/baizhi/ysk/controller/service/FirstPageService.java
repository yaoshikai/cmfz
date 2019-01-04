package com.baizhi.ysk.controller.service;

public interface FirstPageService {
    Object queryAll(Integer uid, String type, String subType);

    Object queryOneAlbum(Integer id, Integer uid);

    Object queryMember(Integer uid);
}
