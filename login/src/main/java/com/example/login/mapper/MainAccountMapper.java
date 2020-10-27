package com.example.login.mapper;

import model.Po.MainAccount;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/13
 * @since 1.0
 */
@Mapper
public interface MainAccountMapper {
    /**
     * 获得主账号信息
     * @return 主账号信息
     * @param id 参数
     */
     MainAccount getAcc(String id);

    /**
     * 添加主账号信息
     */
    void insertAcc();

    /**
     * 修改主账号信息
     */
     void updateAcc();

}
