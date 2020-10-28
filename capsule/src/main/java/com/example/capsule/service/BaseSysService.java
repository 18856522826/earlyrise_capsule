package com.example.capsule.service;

import model.Po.Capsule;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/28
 * @since 1.0
 */
public interface BaseSysService {
    /**
     * 限制添加
     * @param capsule 入参信息
     */
    void  limitAdd(Capsule capsule);

    /**
     * 随机查询
     * @param mainId 主用户id
     * @return 胶囊信息
     */
    Capsule randSelect(String mainId);
}
