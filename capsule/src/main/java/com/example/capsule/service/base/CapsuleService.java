package com.example.capsule.service.base;

import model.Po.Capsule;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/28
 * @since 1.0
 */
public interface CapsuleService {
    /**
     * 插入capsule
     * @param capsule 参数
     */
    void insertCapsule(Capsule capsule);

    /**
     * 修改capsule
     * @param capsule 参数
     */
    void updateCapsule(Capsule capsule);

    /**
     * 随机查询未读的胶囊
     * @param mainId 参数
     * @return 胶囊信息
     */
    Capsule selectCapsule(String mainId);
}
