package com.example.login.service.base;

import model.Po.MainAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/13
 * @since 1.0
 */
public interface MainAccountService {
    /**
     * 获得主账号
     * @param id 参数
     * @return 主账号
     */
    MainAccount getAcc(String id);

    /**
     * 添加主账号
     */
    void insertAcc();

    /**
     * 修改主账号
     */
    void updateAcc();
}
