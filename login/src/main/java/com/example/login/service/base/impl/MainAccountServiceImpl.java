package com.example.login.service.base.impl;

import com.example.login.mapper.MainAccountMapper;
import com.example.login.service.base.MainAccountService;
import model.Po.MainAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/13
 * @since 1.0
 */
@Service
public class MainAccountServiceImpl implements MainAccountService {
    @Autowired
    private MainAccountMapper mainAccountMapper;


    /**
     * 获得主账号
     *
     * @return 主账号
     */
    @Override
    public MainAccount getAcc(String id) {
        return mainAccountMapper.getAcc(id);
    }

    /**
     * 添加主账号
     */
    @Override
    public void insertAcc() {

    }

    /**
     * 修改主账号
     */
    @Override
    public void updateAcc() {

    }
}
