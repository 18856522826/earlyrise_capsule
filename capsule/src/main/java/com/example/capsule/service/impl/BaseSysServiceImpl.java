package com.example.capsule.service.impl;

import com.example.capsule.service.BaseSysService;
import com.example.capsule.service.base.CapsuleService;
import model.Po.Capsule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redisson.RedisTemplate;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/28
 * @since 1.0
 */
@Service
public class BaseSysServiceImpl implements BaseSysService {
    @Autowired
    private CapsuleService capsuleService;

    @Autowired
    private RedisTemplate<String> redisTemplate;
    /**
     * 限制添加
     * @param capsule 入参信息
     */
    @Override
    public void limitAdd(Capsule capsule) {
        capsuleService.insertCapsule(capsule);
    }

    /**
     * 随机查询
     *
     * @param mainId 主用户id
     * @return 胶囊信息
     */
    @Override
    public Capsule randSelect(String mainId) {
        Capsule capsule= capsuleService.selectCapsule(mainId);
        capsule.setCapsuleIf(1);
        capsuleService.updateCapsule(capsule);
        return capsule;
    }
}
