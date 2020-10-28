package com.example.capsule.service.base.impl;

import com.example.capsule.mapper.CapsuleMapper;
import com.example.capsule.service.base.CapsuleService;
import model.Po.Capsule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/28
 * @since 1.0
 */
@Service
public class CapsuleServiceImpl implements CapsuleService {
    @Resource
    private CapsuleMapper capsuleMapper;
    /**
     * 插入capsule
     *
     * @param capsule 参数
     */
    @Override
    public void insertCapsule(Capsule capsule) {
        capsuleMapper.insert(capsule);
    }
}
